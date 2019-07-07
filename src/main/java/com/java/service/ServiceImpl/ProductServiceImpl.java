package com.java.service.ServiceImpl;

import com.java.dao.ProductDao;
import com.java.dao.ProductImgDao;
import com.java.dto.ImageHolder;
import com.java.dto.ProductExecution;
import com.java.entity.Product;
import com.java.entity.ProductImg;
import com.java.enums.ProductStateEnum;
import com.java.exception.ProductExecuteException;
import com.java.service.ProductService;
import com.java.util.ImgUtil;
import com.java.util.PageCalculator;
import com.java.util.PathUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    //1.处理缩略图，获取缩略图相对路径并赋值给product
    //2.往tb_product写入商品信息，获取productId
    //3.结合productId批量处理商品详情图
    //4.将商品详情图列表批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            product.setEnableStatus(1);
            if(imageHolder != null){
                addProductImage(product, imageHolder);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum <= 0){
                    throw new ProductExecuteException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductExecuteException("创建商品失败" + e.getMessage());
            }

            if(imageHolderList != null && imageHolderList.size() > 0){
                addProductImageList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.selectProductById(productId);
    }

    @Override
    @Transactional
    /**
     * 1.若缩略图参数有值，则处理缩略图
     * 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
     * 2.若商品详情图列表参数有值，将tb_product_img下面的该商品原先的商品详情图记录删除
     * 更新tb_product的信息
     */
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) {
        //空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //给商品设置上默认属性
            product.setUpdateTime(new Date());
            //若商品缩略图不为空并且原有缩略图也不为空，则删除原有缩略图并添加
            if(thumbnail != null){
                //先获取原有商品的信息
                Product tempProduct = productDao.selectProductById(product.getProductId());
                if(tempProduct.getImgAddr() != null){
                    ImgUtil.deleteFileOrDir(tempProduct.getImgAddr());
                }
                addProductImage(product, thumbnail);
            }
            //如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if(productImgList != null && productImgList.size() > 0){
                deleteProductImgList(product.getProductId());
                addProductImageList(product, productImgList);
            }
            try {
                //更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if(effectedNum <= 0){
                    throw new ProductExecuteException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            }catch (Exception e){
                throw new ProductExecuteException("更新商品信息失败：" + e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //页码转换成数据库的行码，并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.selectProductList(productCondition, rowIndex, pageSize);
        //返回查询出的商品总数
        int count = productDao.selectProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }


    private void addProductImage(Product product, ImageHolder imageHolder){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String imageAddr = ImgUtil.generateThumbnail(imageHolder, dest);
        product.setImgAddr(imageAddr);
    }

    private void addProductImageList(Product product, List<ImageHolder> imageHolderList){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder imageHolder : imageHolderList){
            String imageAddr = ImgUtil.generateThumbnail(imageHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imageAddr);
            productImg.setProduct(product);
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }

        if(imageHolderList.size() > 0){
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum <= 0){
                    throw new ProductExecuteException("创建商品详情图失败");
                }
            }catch (Exception e){
                throw new ProductExecuteException("创建商品详情图失败" + e.getMessage());
            }
        }
    }

    //删除某个商品下的所有详情图
    private void deleteProductImgList(long productId){
        //根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.selectProductImgList(productId);
        for (ProductImg productImg : productImgList){
            ImgUtil.deleteFileOrDir(productImg.getImgAddr());
        }
        //删除数据库中的原有图片信息
        productImgDao.deleteProductImgByProductId(productId);
    }
}
