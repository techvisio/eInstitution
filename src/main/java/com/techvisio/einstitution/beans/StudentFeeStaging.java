package com.techvisio.einstitution.beans;

import java.util.Date;

public class StudentFeeStaging {
	
	
	private String fileNo;
	private String academicYear;
	private Integer semester;
	private Long feeHeadId;
	private Double amount;
	private String createdBy;
	private String updatedBy;
	private boolean Approved;
	private boolean feeGenerated;
	private Date createdDate;
	private Date modifiedDate;


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
	public Long getFeeHeadId() {
		return feeHeadId;
	}
	public void setFeeHeadId(Long feeHeadId) {
		this.feeHeadId = feeHeadId;
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

	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "StudentFeeStaging [fileNo=" + fileNo + ", academicYear="
				+ academicYear + ", semester=" + semester + ", feeHeadId="
				+ feeHeadId + ", amount=" + amount + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", Approved=" + Approved
				+ ", feeGenerated=" + feeGenerated + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
}

