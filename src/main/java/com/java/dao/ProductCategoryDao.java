package com.java.dao;

import com.java.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    public List<ProductCategory> selectProductCategoryList(Long shopId);

    public int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    public int deleteProductCategory(@Param("productCategoryId")Long productCategoryId, @Param("shopId") Long shopId);

    public int updateProductCategoryToNull(long productCategoryId);
}
