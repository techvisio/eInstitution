package com.techvisio.einstitution.beans;

import java.util.Date;

public class FeeTransaction {
	private FeeDiscountHead feeDiscountHead=new FeeDiscountHead();
	private String user;
	private Date createdDate;
	private String remark;
	private String mode;
	private Long fileNo;
	private Double amount;
	private Long batchId;
	private Long sessionId;
	
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
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "FeeTransaction [feeDiscountHead=" + feeDiscountHead + ", user="
				+ user + ", createdDate=" + createdDate + ", remark=" + remark
				+ ", mode=" + mode + ", fileNo=" + fileNo + ", amount="
				+ amount + ", batchId=" + batchId + ", sessionId=" + sessionId
				+ "]";
	}
	
	
}