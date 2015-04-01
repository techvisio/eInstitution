package com.techvisio.einstitution.beans;

import java.util.Date;

public class FeeTransaction {
	private Integer semester;
	private FeeDiscountHead feeDiscountHead;
	private String user;
	private Date createdDate;
	private String remark;
	private String mode;
	private Long fileNo;
	private Double amount;
	
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
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

	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "FeeTransaction [semester=" + semester + ", user=" + user + ", createdDate=" + createdDate
				+ ", remark=" + remark + ", mode=" + mode + ", fileNo="
				+ fileNo + "]";
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public FeeDiscountHead getFeeDiscountHead() {
		return feeDiscountHead;
	}
	public void setFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		this.feeDiscountHead = feeDiscountHead;
	}
	
	
}