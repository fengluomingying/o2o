package com.java.DaoTest;

import com.java.BaseTest;
import com.java.dao.AreaDao;
import com.java.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void test(){
        List<Area> areas = areaDao.selectAreas();
        System.out.println(areas);
        assertEquals(2, areas.size());
    }
}
