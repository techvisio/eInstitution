package com.techvisio.einstitution.beans;

import java.util.Date;

public class StudentFeeStaging {
	
	
	private Long fileNo;
	private FeeDiscountHead discountHead;
	private Double amount;
	private boolean approved;
	private boolean feeGenerated;
	private boolean reoccuring;
	private boolean conditional;
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
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isFeeGenerated() {
		return feeGenerated;
	}
	public void setFeeGenerated(boolean feeGenerated) {
		this.feeGenerated = feeGenerated;
	}
	public boolean isReoccuring() {
		return reoccuring;
	}
	public void setReoccuring(boolean isReoccuring) {
		this.reoccuring = isReoccuring;
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
		return conditional;
	}
	public void setConditional(boolean isConditional) {
		this.conditional = isConditional;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discountHead == null) ? 0 : discountHead.hashCode());
		result = prime * result + ((fileNo == null) ? 0 : fileNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentFeeStaging other = (StudentFeeStaging) obj;
		if (discountHead == null) {
			if (other.discountHead != null)
				return false;
		} else if (!discountHead.equals(other.discountHead))
			return false;
		if (fileNo == null) {
			if (other.fileNo != null)
				return false;
		} else if (!fileNo.equals(other.fileNo))
			return false;
		return true;
	}

	
	
}

