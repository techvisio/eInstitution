package com.techvisio.einstitution.beans;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.util.CommonUtil;

public class StudentDetail {

	private String fileNo;
	private String enrollNo;
	private String uniEnrollNo;
	private byte[] photo;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String gender;
	private Date dob;
	private String bloodGroup;
	private String fatherOccupation;
	private String fixedlineNo;
	private String selfMobileNo;
	private String parentMobileNo;
	private String gaurdianMobileNo;
	private String emailId;
	private String gaurdianEmailId;
	private boolean hostel;
	private boolean transportation;
	private String academicYear;
	private String semester;
	private boolean managementApproval;
	private boolean feePaid;
	private Long categoryId;
	private Long courseId;
	private Long branchId;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private String feeReceivedBy;
	private Date feeReceivedOn;
	private String documentReceivedBy;
	private Date documentReceivedOn;
	private String documentVerifiedBy;
	private Date documentVerifiedOn;
	private String managementApprovedBy;
	private Date managementApprovedOn;
	private String domicileState;
	private List<StudentAcademicDetail> academicDtl;
	private List<AdmissionDiscountDtl> DiscountDtl;
	private List<AddressDetail> addressDtl;
	private List<BranchPreference> branchPreference;

	public List<StudentAcademicDetail> getAcademicDtl() {
		return academicDtl;
	}

	public void setAcademicDtl(List<StudentAcademicDetail> academicDtl) {
		this.academicDtl = academicDtl;
	}

	public List<AdmissionDiscountDtl> getDiscountDtl() {
		return DiscountDtl;
	}

	public void setDiscountDtl(List<AdmissionDiscountDtl> discountDtl) {
		this.DiscountDtl = discountDtl;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
		CommonUtil.propogateIdentifiertoAdmission(this);

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	public String getFixedlineNo() {
		return fixedlineNo;
	}

	public void setFixedLineNo(String fixedlineNo) {
		this.fixedlineNo = fixedlineNo;
	}

	public String getSelfMobileNo() {
		return selfMobileNo;
	}

	public void setSelfMobile_No(String selfMobileNo) {
		this.selfMobileNo = selfMobileNo;
	}

	public String getParentMobileNo() {
		return parentMobileNo;
	}

	public void setParentMobileNo(String parentMobileNo) {
		this.parentMobileNo = parentMobileNo;
	}

	public String getGaurdianMobileNo() {
		return gaurdianMobileNo;
	}

	public void setGaurdianMobileNo(String gaurdianMobileNo) {
		this.gaurdianMobileNo = gaurdianMobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGaurdianEmailId() {
		return gaurdianEmailId;
	}

	public void setGaurdianEmailId(String gaurdianEmailId) {
		this.gaurdianEmailId = gaurdianEmailId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreated_On(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getFeeReceivedBy() {
		return feeReceivedBy;
	}

	public void setFeeReceivedBy(String feeReceivedBy) {
		this.feeReceivedBy = feeReceivedBy;
	}

	public Date getFeeReceivedOn() {
		return feeReceivedOn;
	}

	public void setFeeReceivedOn(Date feeReceivedOn) {
		this.feeReceivedOn = feeReceivedOn;
	}

	public String getDocumentReceivedBy() {
		return documentReceivedBy;
	}

	public void setDocumentReceivedBy(String documentReceivedBy) {
		this.documentReceivedBy = documentReceivedBy;
	}

	public Date getDocumentReceivedOn() {
		return documentReceivedOn;
	}

	public void setDocumentReceivedOn(Date documentReceivedOn) {
		this.documentReceivedOn = documentReceivedOn;
	}

	public String getDocumentVerifiedBy() {
		return documentVerifiedBy;
	}

	public void setDocumentVerifiedBy(String documentverifiedBy) {
		this.documentVerifiedBy = documentverifiedBy;
	}

	public Date getDocumentVerifiedOn() {
		return documentVerifiedOn;
	}

	public void setDocumentVerifiedOn(Date documentVerifiedOn) {
		this.documentVerifiedOn = documentVerifiedOn;
	}

	public String getManagementApprovedBy() {
		return managementApprovedBy;
	}

	public void setManagementApprovedBy(String managementApprovedBy) {
		this.managementApprovedBy = managementApprovedBy;
	}

	public Date getManagementApprovedOn() {
		return managementApprovedOn;
	}

	public void setManagementApprovedOn(Date managementApprovedOn) {
		this.managementApprovedOn = managementApprovedOn;
	}

	public String getDomicileState() {
		return domicileState;
	}

	public void setDomicileState(String domicileState) {
		this.domicileState = domicileState;
	}

	public List<AddressDetail> getAddressDtl() {
		return addressDtl;
	}

	public void setAddressDtl(List<AddressDetail> addressDtl) {
		this.addressDtl = addressDtl;
	}

	public boolean isHostel() {
		return hostel;
	}

	public void setHostel(boolean hostel) {
		this.hostel = hostel;
	}

	public boolean isTransportation() {
		return transportation;
	}

	public void setTransportation(boolean transportation) {
		this.transportation = transportation;
	}

	public boolean isManagementApproval() {
		return managementApproval;
	}

	public void setManagementApproval(boolean managementApproval) {
		this.managementApproval = managementApproval;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	public List<BranchPreference> getBranchPreference() {
		return branchPreference;
	}

	public void setBranchPreference(List<BranchPreference> branchPreference) {
		this.branchPreference = branchPreference;
	}

	@Override
	public String toString() {
		return "StudentDetail [fileNo=" + fileNo + ", enrollNo=" + enrollNo
				+ ", uniEnrollNo=" + uniEnrollNo + ", photo="
				+ Arrays.toString(photo) + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", fatherName=" + fatherName
				+ ", motherName=" + motherName + ", gender=" + gender
				+ ", dob=" + dob + ", bloodGroup=" + bloodGroup
				+ ", fatherOccupation=" + fatherOccupation + ", fixedlineNo="
				+ fixedlineNo + ", selfMobileNo=" + selfMobileNo
				+ ", parentMobileNo=" + parentMobileNo + ", gaurdianMobileNo="
				+ gaurdianMobileNo + ", emailId=" + emailId
				+ ", gaurdianEmailId=" + gaurdianEmailId + ", hostel=" + hostel
				+ ", transportation=" + transportation + ", academicYear="
				+ academicYear + ", semester=" + semester
				+ ", managementApproval=" + managementApproval + ", feePaid="
				+ feePaid + ", categoryId=" + categoryId + ", courseId="
				+ courseId + ", branchId=" + branchId + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", feeReceivedBy="
				+ feeReceivedBy + ", feeReceivedOn=" + feeReceivedOn
				+ ", documentReceivedBy=" + documentReceivedBy
				+ ", documentReceivedOn=" + documentReceivedOn
				+ ", documentVerifiedBy=" + documentVerifiedBy
				+ ", documentVerifiedOn=" + documentVerifiedOn
				+ ", managementApprovedBy=" + managementApprovedBy
				+ ", managementApprovedOn=" + managementApprovedOn
				+ ", domicileState=" + domicileState + ", academicDtl="
				+ academicDtl + ", DiscountDtl=" + DiscountDtl
				+ ", addressDtl=" + addressDtl + ", branchPreference="
				+ branchPreference + "]";
	}
	
	
}
