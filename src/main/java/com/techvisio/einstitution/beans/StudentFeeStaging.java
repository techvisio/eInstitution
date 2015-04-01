package com.techvisio.einstitution.beans;

import java.util.Date;

public class StudentFeeStaging {
	
	
	private Long fileNo;
	private String academicYear;
	private Integer semester;
	private Double amount;
	private String createdBy;
	private String updatedBy;
	private boolean Approved;
	private boolean feeGenerated;
	private Date createdDate;
	private Date modifiedDate;
    private FeeDiscountHead discountHead;

	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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


	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "StudentFeeStaging [fileNo=" + fileNo + ", academicYear="
				+ academicYear + ", semester=" + semester + ", amount=" + amount + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", Approved=" + Approved
				+ ", feeGenerated=" + feeGenerated + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	public FeeDiscountHead getDiscountHead() {
		return discountHead;
	}
	public void setDiscountHead(FeeDiscountHead discountHead) {
		this.discountHead = discountHead;
	}
	
}

