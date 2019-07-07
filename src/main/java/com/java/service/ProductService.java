package com.java.service;

import com.java.dto.ImageHolder;
import com.java.dto.ProductExecution;
import com.java.entity.Product;

import java.util.List;

public interface ProductService {

    public ProductExecution addProduct(Product product, ImageHolder imageHolder,
                                       List<ImageHolder> imageHolderList);

    public Product getProductById(Long productId);

    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
                                          List<ImageHolder> productImgList);

    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

}
