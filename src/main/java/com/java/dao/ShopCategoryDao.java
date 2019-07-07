package com.java.dao;

import com.java.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> selectShopCategoryList(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
