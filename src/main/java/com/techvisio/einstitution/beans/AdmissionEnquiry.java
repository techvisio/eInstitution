package com.techvisio.einstitution.beans;

import java.util.Date;

public class AdmissionEnquiry {

	private Long enquiryId;
	private Long fileNo;
	private String registrationNo;
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
	private Course course;
	private Branch branch;
	private boolean followupRequired;
	private String remarks;
	private String emailId;
	private boolean lateral;
	private String gender;
	private Long consultantId;
	private String referredBy;
	private String admissionMode;
	private Long categoryId;
	
	
	public Long getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	@Override
	public String toString() {
		return "AdmissionInquiry [enquiryId=" + enquiryId + ", fileNo="
				+ fileNo + ", name=" + name + ", fatherName=" + fatherName
				+ ", dob=" + dob + ", contactNo=" + contactNo
				+ ", applicationStatus=" + applicationStatus + ", dueDate="
				+ dueDate + ", createdDate=" + createdDate + ", createBy="
				+ createBy + ", updatedDate=" + updatedDate + ", updatedBy="
				+ updatedBy + ", followupRequired=" + followupRequired
				+ ", remarks=" + remarks + ", emailId=" + emailId
				+ ", Lateral=" + lateral + ", gender=" + gender + "]";
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isLateral() {
		return lateral;
	}
	public void setLateral(boolean lateral) {
		this.lateral = lateral;
	}
	
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getAdmissionMode() {
		return admissionMode;
	}
	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	

}
