package com.java.dao;

import com.java.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    public List<HeadLine>  selectHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
