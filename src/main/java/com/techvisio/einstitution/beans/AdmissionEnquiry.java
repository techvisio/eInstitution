package com.techvisio.einstitution.beans;

import java.util.Date;

public class AdmissionEnquiry {
	
	private int enquiryId;
	private String name;
	private String fatherName;
	private Date dob;
	private int contactNo;
	private String applicationStatus;
	private Date dueDate;
	private Date createdDate;
	private String createBy;
	private Date updatedDate;
	private String updatedBy;
	private int intrestedCourseId;
	private int intrestedBranchId;
	private String followupRequired;
	
	public int getEnquiryId() {
		return enquiryId;
	}
	
	public void setEnquiryId(int enquiryId) {
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
	
	
	public int getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(int contactNo) {
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
	
	
	public int getIntrestedCourseId() {
		return intrestedCourseId;
	}
	
	public void setIntrestedCourseId(int intrestedCourseId) {
		this.intrestedCourseId = intrestedCourseId;
	}
	
	
	public int getIntrestedBranchId() {
		return intrestedBranchId;
	}
	
	public void setIntrestedBranchId(int intrestedBranchId) {
		this.intrestedBranchId = intrestedBranchId;
	}
	
	
	public String getFollowupRequired() {
		return followupRequired;
	}
	
	public void setFollowupRequired(String followupRequired) {
		this.followupRequired = followupRequired;
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

}
