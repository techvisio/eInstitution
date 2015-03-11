package com.techvisio.einstitution.beans;

import java.util.Date;

public class FeeTransaction {
	private Integer semester;
	private Long componentId;
	private String head;
	private String user;
	private Date createdDate;
	private String remark;
	private String mode;
	private String fileNo;
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}
	public Long getComponentId() {
		return componentId;
	}
	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "FeeTransaction [semester=" + semester + ", ComponentId="
				+ componentId + ", user=" + user + ", createdDate="
				+ createdDate + ", remark=" + remark + ", Mode=" + mode
				+ ", fileNo=" + fileNo + "]";
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	
}