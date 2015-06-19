package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "admissioninquiry") 
public class AdmissionEnquiry extends BasicEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Enquiry_Id")
	private Long enquiryId;
	
	@Column(name = "File_No")
	private Long fileNo;
	
	
	private String registrationNo;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Father_Name")
	private String fatherName;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "Contact_No")
	private String contactNo;
	
	@Column(name = "Application_Status")
	private String applicationStatus;
	
	@Column(name = "Due_Date")
	private Date dueDate;
	
	@Column(name = "Created_On")
	private Date createdDate;
	
	@Column(name = "Created_By")
	private String createBy;
	
	@Column(name = "Updated_On")
	private Date updatedDate;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	
	private Course course;
	
	
	private Branch branch;
	
	@Column(name = "FollowUp_Required")
	private boolean followupRequired;
	
	@Column(name = "Remarks")
	private String remarks;
	
	@Column(name = "Email_Id")
	private String emailId;
	
	@Column(name = "Lateral")
	private boolean lateral;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Consultant_Id")
	private Long consultantId;
	
	@Column(name = "Referred_By")
	private String referredBy;
	
	@Column(name = "Admission_Mode")
	private String admissionMode;
	
	@Column(name = "Category_Id")
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
