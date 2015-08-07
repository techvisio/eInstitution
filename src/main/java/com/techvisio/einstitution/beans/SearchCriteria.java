package com.techvisio.einstitution.beans;

public class SearchCriteria {

	private String registrationNo;
	private Long fileNo;	
	private String firstName;
	private String emailId;
	private String mobileNo;
	private String enrollNo;
	private String uniEnrollNo;

	// Specific to Inquiry.
	private Long inquryId;
	private Long courseId;
	private String name;
	private Long branchId;
	private String status;

	//Specific to consultant 
	private String primaryContactNo;
	private String secondaryNo; 
	private Long consultantId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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


	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getInquryId() {
		return inquryId;
	}

	public void setInquryId(Long inquryId) {
		this.inquryId = inquryId;
	}


	public String getRegistrationNo() {
		return registrationNo;
	}


	public void setRegistrationNo(String registraitionNo) {
		this.registrationNo = registraitionNo;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getPrimaryContactNo() {
		return primaryContactNo;
	}


	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}


	public String getSecondaryNo() {
		return secondaryNo;
	}


	public void setSecondaryNo(String secondaryNo) {
		this.secondaryNo = secondaryNo;
	}


	public Long getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "SearchCriteria [registrationNo=" + registrationNo + ", fileNo="
				+ fileNo + ", firstName=" + firstName + ", emailId=" + emailId
				+ ", mobileNo=" + mobileNo + ", enrollNo=" + enrollNo
				+ ", uniEnrollNo=" + uniEnrollNo + ", inquryId=" + inquryId
				+ ", courseId=" + courseId + ", name=" + name + ", branchId="
				+ branchId + ", status=" + status + ", primaryContactNo="
				+ primaryContactNo + ", secondaryNo=" + secondaryNo
				+ ", consultantId=" + consultantId + "]";
	}

}
