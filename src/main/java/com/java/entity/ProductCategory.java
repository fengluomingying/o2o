package com.java.entity;

import java.util.Date;

public class ProductCategory {
	private Long productCategoryId;
	private Shop shop;
	private String productCategoryName;
	private String productCategoryDesc;
	private Integer priority;
	private Date createTime;
	private Date updateTime;

	public ProductCategory() {
	}

	public ProductCategory(Long productCategoryId, Shop shop, String productCategoryName, String productCategoryDesc, Integer priority, Date createTime, Date updateTime) {
		this.productCategoryId = productCategoryId;
		this.shop = shop;
		this.productCategoryName = productCategoryName;
		this.productCategoryDesc = productCategoryDesc;
		this.priority = priority;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getProductCategoryDesc() {
		return productCategoryDesc;
	}

	public void setProductCategoryDesc(String productCategoryDesc) {
		this.productCategoryDesc = productCategoryDesc;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ProductCategory{" +
				"productCategoryId=" + productCategoryId +
				", shop=" + shop +
				", productCategoryName='" + productCategoryName + '\'' +
				", productCategoryDesc='" + productCategoryDesc + '\'' +
				", priority=" + priority +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
