package com.java.service.ServiceImpl;

import com.java.dao.HeadLineDao;
import com.java.entity.HeadLine;
import com.java.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.selectHeadLine(headLineCondition);
    }
}
