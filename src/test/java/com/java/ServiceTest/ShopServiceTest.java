package com.java.ServiceTest;

import com.java.BaseTest;
import com.java.dto.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Area;
import com.java.entity.PersonInfo;
import com.java.entity.Shop;
import com.java.entity.ShopCategory;
import com.java.enums.ShopStateEnum;
import com.java.exception.ShopExecuteException;
import com.java.service.ShopService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    @Ignore
    public void testShopService() throws FileNotFoundException {
        Shop shop = new Shop();
        ImageHolder imageHolder = new ImageHolder();
        PersonInfo user = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        user.setUserId(1l);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1l);
        shop.setAdvice("test2");
        shop.setArea(area);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setPersonInfo(user);
        shop.setPriority(1);
        shop.setShopAddr("testaddr2");
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("testdesc2");
        shop.setShopName("testname2");
        shop.setShopPhone("1234567");

        File file = new File("C:\\Users\\zfm\\Pictures\\tupian.jpg");
        InputStream is = new FileInputStream(file);
        imageHolder.setImage(is);;
        imageHolder.setImageName(file.getName());
        shopService.addShop(shop, imageHolder);
    }

    @Test
    @Ignore
    public void testModifyShop() throws FileNotFoundException, ShopExecuteException {
        ImageHolder imageHolder = new ImageHolder();
        Shop shop = new Shop();
        shop.setShopId(7l);
        shop.setShopName("更改后的名字");
        File file = new File("E:\\图片\\e95da4014a90f603710b284b3a12b31bb151ed66.jpg");
        InputStream is = new FileInputStream(file);
        imageHolder.setImageName(file.getName());
        imageHolder.setImage(is);
        ShopExecution shopExecution = shopService.modifyShop(shop,imageHolder);
        System.out.println(shopExecution.getShop());
    }

    @Test
    public void testGetShopList(){
        Shop shop = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1l);
        shop.setShopCategory(shopCategory);
        ShopExecution se = shopService.getShopList(shop, 2, 2);
        List<Shop> shopList = se.getShopList();
        System.out.println(shopList);
        System.out.println(se.getCount());
    }
}
