package com.techvisio.einstitution.beans;

import java.util.Date;

public class FeeAdmissionBean {

	private Double pendingFee;
	private String fileNo;
	private String firstName;
	private String lastName;
	private String enrollmentNo;
	private String fatherName;
	private String gender;
	private Date dob;
	private String course;
	private String branch;
	private String semester;
	private String academicYear;
	private String instructionForFee;
	private Date feeInstructionDate;
	public Double getPendingFee() {
		return pendingFee;
	}
	public void setPendingFee(Double pendingFee) {
		this.pendingFee = pendingFee;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
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
	public String getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getInstructionForFee() {
		return instructionForFee;
	}
	public void setInstructionForFee(String instructionForFee) {
		this.instructionForFee = instructionForFee;
	}
	public Date getFeeInstructionDate() {
		return feeInstructionDate;
	}
	public void setFeeInstructionDate(Date feeInstructionDate) {
		this.feeInstructionDate = feeInstructionDate;
	}
	@Override
	public String toString() {
		return "FeeAdmissionBean [pendingFee=" + pendingFee + ", fileNo="
				+ fileNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", enrollmentNo=" + enrollmentNo + ", fatherName="
				+ fatherName + ", gender=" + gender + ", dob=" + dob
				+ ", course=" + course + ", branch=" + branch + ", semester="
				+ semester + ", academicYear=" + academicYear
				+ ", instructionForFee=" + instructionForFee
				+ ", feeInstructionDate=" + feeInstructionDate + "]";
	}
	
    
	
	}
