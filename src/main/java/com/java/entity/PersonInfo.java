package com.java.entity;

import java.util.Date;

public class PersonInfo {
    private Long userId;
    private String userName;
    private String profileImg; //用户头像地址
    private String email;
    private String gender;
    private Integer enableStatus;//是否允许登入商城的状态
    private Integer userType;//用户的类型 1:顾客 2:店家 3:超级管理员
    private Date createTime;
    private Date updateTime;

    public PersonInfo(Long userId, String userName, String profileImg, String email, String gender, Integer enableStatus, Integer userType, Date createTime, Date updateTime) {
        this.userId = userId;
        this.userName = userName;
        this.profileImg = profileImg;
        this.email = email;
        this.gender = gender;
        this.enableStatus = enableStatus;
        this.userType = userType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PersonInfo() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
