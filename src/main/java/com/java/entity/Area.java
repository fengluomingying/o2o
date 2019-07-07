package com.java.entity;

import java.util.Date;

public class Area {
    private Integer areaId;
    private String areaName;
    private Integer priority; //权重
    private Date creatTime;
    private Date updateTime;

    public Area() {
    }

    public Area(Integer areaId, String areaName, Integer priority, Date creatTime, Date updateTime) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.priority = priority;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", priority=" + priority +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

