package com.techvisio.einstitution.beans;

import java.util.Date;

public class AdmissionInquiry {
	
	private Long enquiryId;
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
	private Long intrestedCourseId;
	private Long intrestedBranchId;
	private boolean followupRequired;
	
	public Long getEnquiryId() {
		return enquiryId;
	}
	
	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
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
	
	
	public Long getIntrestedCourseId() {
		return intrestedCourseId;
	}
	
	public void setIntrestedCourseId(Long i) {
		this.intrestedCourseId = i;
	}
	
	
	public Long getIntrestedBranchId() {
		return intrestedBranchId;
	}
	
	public void setIntrestedBranchId(Long intrestedBranchId) {
		this.intrestedBranchId = intrestedBranchId;
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

	public boolean isFollowupRequired() {
		return followupRequired;
	}

	public void setFollowupRequired(boolean followupRequired) {
		this.followupRequired = followupRequired;
	}

	@Override
	public String toString() {
		return "AdmissionInquiry [enquiryId=" + enquiryId + ", name=" + name
				+ ", fatherName=" + fatherName + ", dob=" + dob
				+ ", contactNo=" + contactNo + ", applicationStatus="
				+ applicationStatus + ", dueDate=" + dueDate + ", createdDate="
				+ createdDate + ", createBy=" + createBy + ", updatedDate="
				+ updatedDate + ", updatedBy=" + updatedBy
				+ ", intrestedCourseId=" + intrestedCourseId
				+ ", intrestedBranchId=" + intrestedBranchId
				+ ", followupRequired=" + followupRequired + "]";
	}

}
