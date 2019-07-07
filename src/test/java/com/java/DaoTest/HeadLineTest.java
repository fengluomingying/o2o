package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.HeadLineDao;
import com.java.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineTest extends BaseTest {

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testSelectHeadLine(){
        List<HeadLine> headLines = headLineDao.selectHeadLine(new HeadLine());
        System.out.println(headLines);
    }
}
