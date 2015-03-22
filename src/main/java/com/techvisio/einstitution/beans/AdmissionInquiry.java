package com.techvisio.einstitution.beans;

import java.util.Date;

public class AdmissionInquiry {
	
	private Long enquiryId;
	private String fileNo;
	private String name;
	private String fatherName;
	private Date dob;
	private String contactNo;
	private String applicationStatus;
	private Date dueDate;
	private Date createdDate;
	private String createBy;
	private Date updatedDate;
	private String updatedBy;
	private Long courseId;
	private Long branchId;
	private boolean followupRequired;
	private String remarks;
	public Long getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public boolean isFollowupRequired() {
		return followupRequired;
	}
	public void setFollowupRequired(boolean followupRequired) {
		this.followupRequired = followupRequired;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "AdmissionInquiry [enquiryId=" + enquiryId + ", fileNo="
				+ fileNo + ", name=" + name + ", fatherName=" + fatherName
				+ ", dob=" + dob + ", contactNo=" + contactNo
				+ ", applicationStatus=" + applicationStatus + ", dueDate="
				+ dueDate + ", createdDate=" + createdDate + ", createBy="
				+ createBy + ", updatedDate=" + updatedDate + ", updatedBy="
				+ updatedBy + ", courseId=" + courseId + ", branchId="
				+ branchId + ", followupRequired=" + followupRequired
				+ ", remarks=" + remarks + "]";
	}
	

}
