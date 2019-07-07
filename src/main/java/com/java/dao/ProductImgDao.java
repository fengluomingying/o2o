package com.java.dao;

import com.java.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    public int batchInsertProductImg(List<ProductImg> productImgs);

    public int deleteProductImgByProductId(Long productId);

    public List<ProductImg> selectProductImgList(Long productId);
}
