package com.java.service;

import com.java.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
