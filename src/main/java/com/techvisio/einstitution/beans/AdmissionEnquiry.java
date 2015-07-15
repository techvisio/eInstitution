package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "admission_Enquiry") 
public class AdmissionEnquiry extends BasicEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Enquiry_Id")
	private Long enquiryId;
	
	@Column(name = "File_No")
	private Long fileNo;
	
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
	
	@ManyToOne
	@JoinColumn(name="Course_Id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="Branch_Id")
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
	
	@ManyToOne
	@JoinColumn(name="Consultant_Id")
	private Consultant consultant;
	
	@Column(name = "Referred_By")
	private String referredBy;
	
	@Column(name = "Admission_Mode")
	private String admissionMode;
	
	@ManyToOne
    @JoinColumn(name="Category_Id")
	private CasteCategory category;
	
	
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
	
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	public Consultant getConsultant() {
		return consultant;
	}
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
	public CasteCategory getCategory() {
		return category;
	}
	public void setCategory(CasteCategory category) {
		this.category = category;
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
