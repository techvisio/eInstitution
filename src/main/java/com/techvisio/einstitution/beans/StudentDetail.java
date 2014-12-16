package com.techvisio.einstitution.beans;

import java.util.Date;
import java.util.List;


public class StudentDetail {

	private String fileNo;
	private String enrollNo;
	private String uniEnrollNo;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String gender;
	private Date dob;
	private String bloodGroup;
	private String fatherOccupation;
	private int fixedLineNo;
	private int selfMobileNo;
	private int parentMobileNo;
	private int gaurdianMobileNo;
	private String hostel;
	private String transportation;
	private int academicYear;
	private int semester;
	private int branchId;
	private int categoryId;
	private String managementApproval;
	private String feePaid;
	private List <StudentAcademicDetail> academicDtl;
	private List <QualificationSubjectDtl> qualificationSubDtl;
	private List <AdmissionDiscountDtl> DiscountDtl;
	
	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
	
	public String getEnrollNo() {
		return enrollNo;
	}
	
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	
	
	public String getUniEnrollNo() {
		return uniEnrollNo;
	}
	
	public void setUniEnrollNo(String uniEnrollNo) {
		this.uniEnrollNo = uniEnrollNo;
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
	
	
	public String getMotherName() {
		return motherName;
	}
	
	public void setMotherName(String motherName) {
		this.motherName = motherName;
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
	
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	
	
	public int getFixedLineNo() {
		return fixedLineNo;
	}
	
	public void setFixedLineNo(int fixedLineNo) {
		this.fixedLineNo = fixedLineNo;
	}
	
	
	public int getSelfMobileNo() {
		return selfMobileNo;
	}
	
	public void setSelfMobileNo(int selfMobileNo) {
		this.selfMobileNo = selfMobileNo;
	}
	
	
	public int getParentMobileNo() {
		return parentMobileNo;
	}
	
	public void setParentMobileNo(int parentMobileNo) {
		this.parentMobileNo = parentMobileNo;
	}
	
	
	public int getGaurdianMobileNo() {
		return gaurdianMobileNo;
	}
	
	public void setGaurdianMobileNo(int gaurdianMobileNo) {
		this.gaurdianMobileNo = gaurdianMobileNo;
	}
	
	
	public String getHostel() {
		return hostel;
	}
	
	public void setHostel(String hostel) {
		this.hostel = hostel;
	}
	
	
	public String getTransportation() {
		return transportation;
	}
	
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	
	
	public int getAcademicYear() {
		return academicYear;
	}
	
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
	public int getBranchId() {
		return branchId;
	}
	
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public String getManagementApproval() {
		return managementApproval;
	}
	
	public void setManagementApproval(String managementApproval) {
		this.managementApproval = managementApproval;
	}
	
	
	public String getFeePaid() {
		return feePaid;
	}
	
	
	public void setFeePaid(String feePaid) {
		this.feePaid = feePaid;
	}

	
	
	public List <StudentAcademicDetail> getAcademicDtl() {
		return academicDtl;
	}

	
	public void setAcademicDtl(List <StudentAcademicDetail> academicDtl) {
		this.academicDtl = academicDtl;
	}

	
	
	public List <QualificationSubjectDtl> getQualificationSubDtl() {
		return qualificationSubDtl;
	}

	
	public void setQualificationSubDtl(List <QualificationSubjectDtl> qualificationSubDtl) {
		this.qualificationSubDtl = qualificationSubDtl;
	}

	
	
	public List <AdmissionDiscountDtl> getDiscountDtl() {
		return DiscountDtl;
	}

	
	public void setDiscountDtl(List <AdmissionDiscountDtl> discountDtl) {
		DiscountDtl = discountDtl;
	}
}
