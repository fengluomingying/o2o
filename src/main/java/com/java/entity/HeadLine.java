package com.java.entity;

import java.util.Date;

public class HeadLine {
	private Long lineId;
	private String lineName;
	private String lineLink;
	private String lineImg;
	private Integer priority;
	private Integer enableStatus;
	private Date createTime;
	private Date updateTime;

	public HeadLine() {
	}

	public HeadLine(Long lineId, String lineName, String lineLink, String lineImg, Integer priority, Integer enableStatus, Date createTime, Date updateTime) {
		this.lineId = lineId;
		this.lineName = lineName;
		this.lineLink = lineLink;
		this.lineImg = lineImg;
		this.priority = priority;
		this.enableStatus = enableStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineLink() {
		return lineLink;
	}

	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}

	public String getLineImg() {
		return lineImg;
	}

	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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

	@Override
	public String toString() {
		return "HeadLine{" +
				"lineId=" + lineId +
				", lineName='" + lineName + '\'' +
				", lineLink='" + lineLink + '\'' +
				", lineImg='" + lineImg + '\'' +
				", priority=" + priority +
				", enableStatus=" + enableStatus +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
