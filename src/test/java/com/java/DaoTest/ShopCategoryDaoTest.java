package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.ShopCategoryDao;
import com.java.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void shopCategoryDaoTest(){
//        List<ShopCategory> shopCategoryList = shopCategoryDao.selectShopCategoryList(new ShopCategory());
//        System.out.println(shopCategoryList.get(0));

        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1l);
        shopCategory.setParent(parent);
        List<ShopCategory> shopCategoryList = shopCategoryDao.selectShopCategoryList(null);
        System.out.println(shopCategoryList.size());
    }
}
