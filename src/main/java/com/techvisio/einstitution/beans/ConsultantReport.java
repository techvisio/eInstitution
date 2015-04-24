package com.techvisio.einstitution.beans;

import java.util.Date;

import com.techvisio.einstitution.util.DynamicProperties;

public class ConsultantReport {

	private String consultantName;
//	private String
	// TODO need to get a bean having student details (can be made by extending or new)
	private Long consultantId;
	private String primaryContactNo;
	private String secondaryContactNo;
	private String emailId;
	private Long fileNo;
	private String firstName;
	private String lastName;
	private String fatherName;
	private Date dob;
	private boolean feePaid;
	private String gender;
	private String selfMobileNo;
	private String course;
	private String description;
	private String branchName;
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	
	public String getPrimaryContactNo() {
		return primaryContactNo;
	}
	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}
	public String getSecondaryContactNo() {
		return secondaryContactNo;
	}
	public void setSecondaryContactNo(String secondaryContactNo) {
		this.secondaryContactNo = secondaryContactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public boolean isFeePaid() {
		return feePaid;
	}
	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSelfMobileNo() {
		return selfMobileNo;
	}
	public void setSelfMobileNo(String selfMobileNo) {
		this.selfMobileNo = selfMobileNo;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public String toString() {
		return "ConsultantReport [consultantName=" + consultantName
				+ ", consultantId=" + consultantId + ", primaryContactNo="
				+ primaryContactNo + ", secondaryContactNo="
				+ secondaryContactNo + ", emailId=" + emailId + ", fileNo="
				+ fileNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", fatherName=" + fatherName + ", dob=" + dob
				+ ", feePaid=" + feePaid + ", gender=" + gender
				+ ", selfMobileNo=" + selfMobileNo + ", course=" + course
				+ ", description=" + description + ", branchName=" + branchName
				+ "]";
	}
	
	
	

}
