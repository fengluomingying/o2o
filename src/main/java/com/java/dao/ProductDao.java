package com.java.dao;

import com.java.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    public int insertProduct(Product product);

    public Product selectProductById(Long productId);

    public int updateProduct(Product product);

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺ID，商品类别
     */
    List<Product> selectProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                    @Param("pageSize") int pageSize);

    /**
     * 查询对应的商品总数
     */
    int selectProductCount(@Param("productCondition") Product productCondition);
}
