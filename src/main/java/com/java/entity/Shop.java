package com.java.entity;

import java.util.Date;
import java.util.List;

public class Shop {

	private Long shopId;
	private Integer priority;
	private String shopImg;
	private String shopName;
	private String shopDesc;
	private String shopPhone;
	private String shopAddr;
	private String advice;
	private Date createTime;
	private Date updateTime;
	private Area area;
	private ShopCategory shopCategory;
	private PersonInfo personInfo;
	private Integer enableStatus;

	public Shop(Long shopId, Integer priority, String shopImg, String shopName, String shopDesc, String shopPhone, String shopAddr, String advice, Date createTime, Date updateTime, Area area, ShopCategory shopCategory, PersonInfo personInfo, Integer enableStatus) {
		this.shopId = shopId;
		this.priority = priority;
		this.shopImg = shopImg;
		this.shopName = shopName;
		this.shopDesc = shopDesc;
		this.shopPhone = shopPhone;
		this.shopAddr = shopAddr;
		this.advice = advice;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.area = area;
		this.shopCategory = shopCategory;
		this.personInfo = personInfo;
		this.enableStatus = enableStatus;
	}

	public Shop() {
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getShopImg() {
		return shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public ShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"shopId=" + shopId +
				", priority=" + priority +
				", shopImg='" + shopImg + '\'' +
				", shopName='" + shopName + '\'' +
				", shopDesc='" + shopDesc + '\'' +
				", shopPhone='" + shopPhone + '\'' +
				", shopAddr='" + shopAddr + '\'' +
				", advice='" + advice + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", area=" + area +
				", shopCategory=" + shopCategory +
				", personInfo=" + personInfo +
				", enableStatus=" + enableStatus +
				'}';
	}
}
