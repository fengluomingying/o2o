package com.java.service.ServiceImpl;

import com.java.dao.AreaDao;
import com.java.entity.Area;
import com.java.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.selectAreas();
    }
}
