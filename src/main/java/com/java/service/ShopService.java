package com.java.service;

import com.java.dto.ImageHolder;
import com.java.dto.ShopExecution;
import com.java.entity.Shop;
import com.java.exception.ShopExecuteException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, ImageHolder imageHolder);

    /*查询shop*/
    Shop getShopByShopId(Long shopId);

    /*更改shop*/
    ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopExecuteException;

    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
