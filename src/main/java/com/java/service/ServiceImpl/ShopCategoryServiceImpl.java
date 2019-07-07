package com.java.service.ServiceImpl;

import com.java.dao.ShopCategoryDao;
import com.java.entity.ShopCategory;
import com.java.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.selectShopCategoryList(shopCategory);
    }
}
