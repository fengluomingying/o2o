package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.ShopDao;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testShop(){
        Shop shop = new Shop();
        PersonInfo user = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        user.setUserId(1l);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1l);
        shop.setAdvice("test");
        shop.setArea(area);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setPersonInfo(user);
        shop.setPriority(1);
        shop.setShopAddr("testaddr");
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("testdesc");
        shop.setShopImg("testImg");
        shop.setShopName("testname");
        shop.setShopPhone("1234567");
        int activeNum = shopDao.insertShop(shop);
        System.out.println("activeNum = "+activeNum);
    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(4l);
        shop.setAdvice("advice update");
        shop.setUpdateTime(new Date());
        shop.setShopAddr("update addr");
        shop.setShopDesc("update addr");
        shop.setShopImg("update addr");
        int activeNum = shopDao.updateShop(shop);
        System.out.println("activeNum = "+activeNum);
    }

    @Test
    @Ignore
    public void testSelectShop(){
        Shop shop = shopDao.selectShop(4l);
        System.out.println(shop);
    }

    @Test
    public void shopListTest(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1l);
        shopCondition.setPersonInfo(owner);
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(3l);
        childCategory.setParent(parentCategory);
        shopCondition.setShopCategory(childCategory);

        List<Shop> shopList = shopDao.selectShopList(shopCondition,0,6);
        int count = shopDao.selectCountOfShop(shopCondition);

        System.out.println(shopList);
        System.out.println(count);

    }

}
