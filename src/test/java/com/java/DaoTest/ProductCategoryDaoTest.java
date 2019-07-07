package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.ProductCategoryDao;
import com.java.entity.ProductCategory;
import com.java.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
//    @Ignore
    public void testProductCategoryDao(){
        List<ProductCategory> productCategoryList = productCategoryDao.selectProductCategoryList(4l);
        System.out.println(productCategoryList);
    }

    @Test
    @Ignore
    public void testBatchInsertProductCategoryDao(){
        ProductCategory pc1 = new ProductCategory();
        pc1.setCreateTime(new Date());
        pc1.setPriority(12);
        pc1.setProductCategoryName("店铺商品种类4");
        Shop shop = new Shop();
        shop.setShopId(4l);
        pc1.setShop(shop);
        ProductCategory pc2 = new ProductCategory();
        pc2.setShop(shop);
        pc2.setProductCategoryName("店铺商品种类5");
        pc2.setPriority(14);
        pc2.setCreateTime(new Date());
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(pc1);
        productCategoryList.add(pc2);
        productCategoryDao.batchInsertProductCategory(productCategoryList);
    }

    @Test
    public void testDeleteProductCategory(){
        Long shopId = 5l;
        List<ProductCategory> productCategoryList = productCategoryDao.selectProductCategoryList(shopId);
        for (ProductCategory pc : productCategoryList){
            productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
        }
    }

    @Test
    public void testUpdateProductCategoryIdToNull(){
        int effectedNum = productCategoryDao.updateProductCategoryToNull(13l);
        assertEquals(2, effectedNum);
    }
}
