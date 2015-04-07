package com.techvisio.einstitution.beans;

import java.util.Date;

public class StudentFeeStaging {
	
	
	private Long fileNo;
	private FeeDiscountHead discountHead;
	private Double amount;
	private boolean Approved;
	private boolean feeGenerated;
	private boolean isReoccuring;
	private boolean isConditional;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String updatedBy;
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public FeeDiscountHead getDiscountHead() {
		return discountHead;
	}
	public void setDiscountHead(FeeDiscountHead discountHead) {
		this.discountHead = discountHead;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public boolean isApproved() {
		return Approved;
	}
	public void setApproved(boolean approved) {
		Approved = approved;
	}
	public boolean isFeeGenerated() {
		return feeGenerated;
	}
	public void setFeeGenerated(boolean feeGenerated) {
		this.feeGenerated = feeGenerated;
	}
	public boolean isReoccuring() {
		return isReoccuring;
	}
	public void setReoccuring(boolean isReoccuring) {
		this.isReoccuring = isReoccuring;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean isConditional() {
		return isConditional;
	}
	public void setConditional(boolean isConditional) {
		this.isConditional = isConditional;
	}

	
}

