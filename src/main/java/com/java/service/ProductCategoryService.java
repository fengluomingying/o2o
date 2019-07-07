package com.java.service;

import com.java.dto.ProductCategoryExecution;
import com.java.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategoryList(Long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList);

    ProductCategoryExecution removeProductCategory(Long productCategoryId, Long shopId);
}
