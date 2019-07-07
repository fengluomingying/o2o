package com.java.entity;

import java.util.Date;

public class WechatAuth {
    private Long wechatAuthId;
    //公众号
    private String openId;
    //用户Id
    private PersonInfo personInfo;

    private Date createTime;

    public WechatAuth(Long wechatAuthId, String openId, PersonInfo personInfo, Date createTime) {
        this.wechatAuthId = wechatAuthId;
        this.openId = openId;
        this.personInfo = personInfo;
        this.createTime = createTime;
    }

    public WechatAuth() {
    }

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
