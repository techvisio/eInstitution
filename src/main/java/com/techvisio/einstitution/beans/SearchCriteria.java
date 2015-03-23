package com.techvisio.einstitution.beans;

public class SearchCriteria {

	private String fileNo;
	private String emailId;
	private String mobileNo;
	private String enrollNo;
	private String uniEnrollNo;

	// Specific to Inquiry.
	private Long inquryNo;
	private Long courseId;
	private String name;
	private Long branchId;

	@Override
	public String toString() {
		return "AdmissionIdentifier [fileNo=" + fileNo + ", emailId=" + emailId
				+ ", mobileNo=" + mobileNo + ", enrollNo=" + enrollNo
				+ ", uniEnrollNo=" + uniEnrollNo + "]";
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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

	public Long getInquryNo() {
		return inquryNo;
	}

	public void setInquryNo(Long inquryNo) {
		this.inquryNo = inquryNo;
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
}
