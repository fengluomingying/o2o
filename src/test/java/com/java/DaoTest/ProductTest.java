package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.ProductDao;
import com.java.dao.ProductImgDao;
import com.java.entity.Product;
import com.java.entity.ProductCategory;
import com.java.entity.ProductImg;
import com.java.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void testInsertProduct(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(13l);
        Shop shop = new Shop();
        shop.setShopId(13l);
        Product product = new Product();
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setImgAddr("测试1");
        product.setNormalPrice("100");
        product.setPriority(10);
        product.setPromotionPrice("50");
        product.setProductDesc("test");
        product.setProductId(1l);
        product.setProductName("pingguo ");
        product.setProductCategory(productCategory);
        product.setShop(shop);
        product.setUpdateTime(new Date());

        productDao.insertProduct(product);
    }

    @Test
    public void testSelectProduct() {
        Long productId = 1l;
        Product product = new Product();
        product.setProductId(productId);
        ProductImg productImg1 = new ProductImg();
        productImg1.setProduct(product);
        productImg1.setCreateTime(new Date());
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);

        ProductImg productImg2 = new ProductImg();
        productImg2.setPriority(2);
        productImg2.setImgDesc("测试图片2");
        productImg2.setImgAddr("图片2");
        productImg2.setCreateTime(new Date());
        productImg2.setProduct(product);

        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectedNum);

        Product product1 = productDao.selectProductById(productId);
        System.out.println(product1);
    }

    @Test
//    @Ignore
    public void testUpdateProduct(){
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(4l);
        pc.setProductCategoryId(13l);
        product.setProductId(4l);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("第二个商品");
        productDao.updateProduct(product);
    }

    @Test
    public void testProductList(){
        Product productCondition = new Product();
        List<Product> productList = productDao.selectProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());

        int count = productDao.selectProductCount(productCondition);
        assertEquals(4, count);

        productCondition.setProductName("test");
        productList = productDao.selectProductList(productCondition,0,1);
        assertEquals(1, productList.size());

        count = productDao.selectProductCount(productCondition);
        assertEquals(1, count);
    }
}
