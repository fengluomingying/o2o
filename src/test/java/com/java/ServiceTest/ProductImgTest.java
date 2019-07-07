package com.java.ServiceTest;

import com.java.BaseTest;
import com.java.dto.ImageHolder;
import com.java.dto.ProductExecution;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import com.java.enums.ProductStateEnum;
import com.java.exception.ShopExecuteException;
import com.java.service.ProductService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductImgTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(13l);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(13l);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("test1");
        product.setProductDesc("test1desc");
        product.setEnableStatus(1);
        product.setPriority(10);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        File thumbnail = new File("F:\\JAVA_IDEA\\o2o\\图片素材\\5f475b123979b78953c6e740a9a0f4a2.jpg");
        InputStream is = new FileInputStream(thumbnail);
        ImageHolder imageHolder = new ImageHolder();
        imageHolder.setImageName(thumbnail.getName());
        imageHolder.setImage(is);

        File productImg1 = new File("F:\\JAVA_IDEA\\o2o\\图片素材\\18d8bc3eb13533fa9e4d2e17a3d3fd1f41345b0b.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("F:\\JAVA_IDEA\\o2o\\图片素材\\57ed0affa688f_1024.jpg");
        InputStream is2 = new FileInputStream(productImg2);

        List<ImageHolder> imageHolderList = new ArrayList<>();
        imageHolderList.add(new ImageHolder(productImg1.getName(), is1));
        imageHolderList.add(new ImageHolder(productImg2.getName(), is2));

        productService.addProduct(product, imageHolder, imageHolderList);
    }

      @Test
      public void testModifyProduct() throws ShopExecuteException, FileNotFoundException{
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop  = new Shop();
        product.setProductId(1l);
        shop.setShopId(13l);
        pc.setProductCategoryId(13l);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品");
        //创建缩略图文件流
        File thumbnailFile = new File("E:\\图片\\202604ybwgbrr7agwrb06o.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        //创建两个商品详情图文件流并将他们添加到详情图列表
        File productImg1 = new File("E:\\图片\\53c179bd8b94ff08892b0147923c4ce3.jpeg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("E:\\图片\\202604cpyb9yqqzbe6p6yp.jpg");
        InputStream is2 = new FileInputStream(productImg1);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));

        ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
        Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
      }
}
