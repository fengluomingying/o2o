package com.java.entity;

import java.util.Date;

public class ShopCategory {

	private Long shopCategoryId;
	private String shopCategoryName;
	private String shopCategoryDesc;
	private String shopCategoryImg;
	private Integer priority;
	private Date createTime;
	private Date updateTime;
	private ShopCategory parent;

	public ShopCategory() {
	}

	public ShopCategory(Long shopCategoryId, String shopCategoryName, String shopCategoryDesc, String shopCategoryImg, Integer priority, Date createTime, Date updateTime, ShopCategory parent) {
		this.shopCategoryId = shopCategoryId;
		this.shopCategoryName = shopCategoryName;
		this.shopCategoryDesc = shopCategoryDesc;
		this.shopCategoryImg = shopCategoryImg;
		this.priority = priority;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.parent = parent;
	}

	public Long getShopCategoryId() {
		return shopCategoryId;
	}

	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public String getShopCategoryName() {
		return shopCategoryName;
	}

	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}

	public String getShopCategoryDesc() {
		return shopCategoryDesc;
	}

	public void setShopCategoryDesc(String shopCategoryDesc) {
		this.shopCategoryDesc = shopCategoryDesc;
	}

	public String getShopCategoryImg() {
		return shopCategoryImg;
	}

	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getupdateTime() {
		return updateTime;
	}

	public void setupdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ShopCategory getParent() {
		return parent;
	}

	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ShopCategory{" +
				"shopCategoryId=" + shopCategoryId +
				", shopCategoryName='" + shopCategoryName + '\'' +
				", shopCategoryDesc='" + shopCategoryDesc + '\'' +
				", shopCategoryImg='" + shopCategoryImg + '\'' +
				", priority=" + priority +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", parent=" + parent +
				'}';
	}
}
