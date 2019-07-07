package com.java.dao;

import com.java.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    Shop selectShop(Long shopId);

    /**
     * 根据 店铺名（模糊），区域，店铺种类，店铺状态,user 查询
     * @param shopCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Shop> selectShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);

    int selectCountOfShop(@Param("shopCondition") Shop shopCondition);
}
