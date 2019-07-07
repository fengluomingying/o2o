package com.java.service.ServiceImpl;

import com.java.dao.ProductCategoryDao;
import com.java.dto.ProductCategoryExecution;
import com.java.entity.ProductCategory;
import com.java.enums.ProductCategoryStateEnum;
import com.java.exception.ProductCategoryExecuteException;
import com.java.service.ProductCategoryService;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.selectProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) {
        if(productCategoryList != null && productCategoryList.size() > 0 ){
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if(effectedNum <= 0){
                    throw new ProductCategoryExecuteException("店铺商品类别创建失败！");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryExecuteException("批量创建商品类别出错:" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution removeProductCategory(Long productCategoryId, Long shopId) {
        if(productCategoryId != null && shopId != null){
            try {
                int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
                if(effectedNum < 0){
                    throw new ProductCategoryExecuteException("商品类别删除失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryExecuteException("商品删除出错：" + e.getMessage());
            }
        }else {
            throw new ProductCategoryExecuteException("请输入productCategoryId及shopId");
        }

    }
}
