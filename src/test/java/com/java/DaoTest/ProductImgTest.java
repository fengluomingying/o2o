package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.ProductImgDao;
import com.java.entity.Product;
import com.java.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImgTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testProductImgDao(){
        List<ProductImg> productImgs = new ArrayList<>();
        ProductImg productImg1 = new ProductImg();
        ProductImg productImg2 = new ProductImg();

        Product product = new Product();
        product.setProductId(1l);
        productImg1.setCreateTime(new Date());
        productImg1.setImgAddr("test1");
        productImg1.setImgDesc("testdesc");
        productImg1.setPriority(10);
        productImg1.setProductImgId(1l);
        productImg1.setProduct(product);

        productImg2.setCreateTime(new Date());
        productImg2.setImgAddr("test2");
        productImg2.setImgDesc("testdesc");
        productImg2.setPriority(9);
        productImg2.setProductImgId(2l);
        productImg2.setProduct(product);

        productImgs.add(productImg1);
        productImgs.add(productImg2);

        productImgDao.batchInsertProductImg(productImgs);
    }

    @Test
    public void testDeleteProductImg(){
        productImgDao.deleteProductImgByProductId(1l);
    }

    @Test
    public void testSelectProductImg(){
        List<ProductImg> productImgList = productImgDao.selectProductImgList(3l);
        System.out.println(productImgList);
    }
}
