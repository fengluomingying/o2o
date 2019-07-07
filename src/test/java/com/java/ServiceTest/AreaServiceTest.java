package com.java.ServiceTest;

import com.java.BaseTest;
import com.java.entity.Area;
import com.java.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void test(){
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);
    }

}
