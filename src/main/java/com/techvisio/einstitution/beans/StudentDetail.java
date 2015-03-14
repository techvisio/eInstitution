package com.techvisio.einstitution.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.DynamicProperties;

public class StudentDetail {

	private String admissionMode;
	
	@DynamicProperties(id="fileNo", title="File No")
	private String fileNo;
	@DynamicProperties(id="enrollNo", title="Enrollment No")
	private String enrollNo;
	@DynamicProperties(id="academicYear", title="Academic Year")
	private String academicYear;
	@DynamicProperties(id="semester", title="Semester")
	private String semester;
	
	@DynamicProperties(id="courseId", title="course", type="select", masterDataCode=AppConstants.COURSE)
	private Long courseId;
	@DynamicProperties(id="branchId", title="branch", type="select", masterDataCode=AppConstants.BRANCH)
	private Long branchId;
	
	@DynamicProperties(id="firstName", title="First Name")
	private String firstName;
	@DynamicProperties(id="lastName", title="Last Name")
	private String lastName;
	
	@DynamicProperties(id="uniEnrollNo", title="University Enrollment No")
	private String uniEnrollNo;
	@DynamicProperties(id="dob", title="DOB", type="date")
	private Date dob;
	@Override
	public String toString() {
		return "StudentDetail [admissionMode=" + admissionMode + ", fileNo="
				+ fileNo + ", enrollNo=" + enrollNo + ", academicYear="
				+ academicYear + ", semester=" + semester + ", courseId="
				+ courseId + ", branchId=" + branchId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", uniEnrollNo="
				+ uniEnrollNo + ", dob=" + dob + ", fatherName=" + fatherName
				+ ", motherName=" + motherName + ", gender=" + gender
				+ ", bloodGroup=" + bloodGroup + ", emailId=" + emailId
				+ ", gaurdianEmailId=" + gaurdianEmailId + ", fixedlineNo="
				+ fixedlineNo + ", selfMobileNo=" + selfMobileNo
				+ ", parentMobileNo=" + parentMobileNo + ", gaurdianMobileNo="
				+ gaurdianMobileNo + ", fatherOccupation=" + fatherOccupation
				+ ", categoryId=" + categoryId + ", hostel=" + hostel
				+ ", transportation=" + transportation
				+ ", managementApproval=" + managementApproval + ", feePaid="
				+ feePaid + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + ", academicDtl="
				+ academicDtl + ", DiscountDtl=" + DiscountDtl
				+ ", addressDtl=" + addressDtl + ", branchPreference="
				+ branchPreference + ", counsellingDtl=" + counsellingDtl
				+ ", quotaCode=" + quotaCode
				+ ", referredBy=" + referredBy + "]";
	}

	@DynamicProperties(id="fatherName", title="Father Name")
	private String fatherName;
	@DynamicProperties(id="motherName", title="Mother Name")
	private String motherName;
	@DynamicProperties(id="gender", title="Gender", type="radio", validValues={"Male","Female"})
	private String gender;
	@DynamicProperties(id="bloodGroup", title="Blood Group", type="select")
	private String bloodGroup;
	@DynamicProperties(id="emailId", title="Student Email Id", masterDataCode=AppConstants.BLOODGROUP)
	private String emailId;
	@DynamicProperties(id="gaurdianEmailId", title="Gaurdian Email Id")
	private String gaurdianEmailId;
	@DynamicProperties(id="fixedlineNo", title="Fixed Line No")
	private String fixedlineNo;
	@DynamicProperties(id="selfMobileNo", title="Self Mobile No")
	private String selfMobileNo;
	@DynamicProperties(id="parentMobileNo", title="Parent Cell No")
	private String parentMobileNo;
	@DynamicProperties(id="gaurdianMobileNo", title="Gaurdian Mobile No")
	private String gaurdianMobileNo;
	@DynamicProperties(id="fatherOccupation", title="Father Occupation")
	private String fatherOccupation;
	@DynamicProperties(id="categoryId", title="category", type="select" , masterDataCode=AppConstants.CATEGORY)
	private Long categoryId;
	@DynamicProperties(id="hostel", title="Hostel Required",type="check")
	private boolean hostel;
	@DynamicProperties(id="transportation", title="Transport Required", type="check")
	private boolean transportation;
	
	
	@DynamicProperties(id="managementApproval", title="managementApproval" , type="check")
	private boolean managementApproval;
	@DynamicProperties(id="feePaid", title="feePaid", type="check")
	private boolean feePaid;
	
	
	
	@DynamicProperties(id="createdBy", title="createdBy")
	private String createdBy;
	@DynamicProperties(id="createdOn", title="createdOn")
	private Date createdOn;
	@DynamicProperties(id="updatedBy", title="updatedBy")
	private String updatedBy;
	@DynamicProperties(id="updatedOn", title="updatedOn")
	private Date updatedOn;
	
	private byte[] photo;
//	private String feeReceivedBy;
//	private Date feeReceivedOn;
//	private String documentReceivedBy;
//	private Date documentReceivedOn;
//	private String documentVerifiedBy;
//	private Date documentVerifiedOn;
//	private String managementApprovedBy;
//	private Date managementApprovedOn;
	private List<StudentAcademicDetail> academicDtl=new ArrayList<StudentAcademicDetail>();
	private List<AdmissionDiscountDtl> DiscountDtl=new ArrayList<AdmissionDiscountDtl>();
	private List<AddressDetail> addressDtl=new ArrayList<AddressDetail>();
	private List<BranchPreference> branchPreference=new ArrayList<BranchPreference>();
	private List<CounsellingDetail> counsellingDtl=new ArrayList<CounsellingDetail>();
	
	private TransportReservation reservation;
    private ConsultantDetail consultantDetail;
    private ScholarshipDetail scholarshipDetail;
    private String quotaCode;
    private String referredBy;
    
	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

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

	
	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public List<CounsellingDetail> getCounsellingDtl() {
		return counsellingDtl;
	}

	public void setCounsellingDtl(List<CounsellingDetail> counsellingDtl) {
		this.counsellingDtl = counsellingDtl;
	}

	public String getQuotaCode() {
		return quotaCode;
	}

	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}

	public TransportReservation getReservation() {
		return reservation;
	}

	public void setReservation(TransportReservation reservation) {
		this.reservation = reservation;
	}

	public ScholarshipDetail getScholarshipDetail() {
		return scholarshipDetail;
	}

	public void setScholarshipDetail(ScholarshipDetail scholarshipDetail) {
		this.scholarshipDetail = scholarshipDetail;
	}

	public ConsultantDetail getConsultantDetail() {
		return consultantDetail;
	}

	public void setConsultantDetail(ConsultantDetail consultantDetail) {
		this.consultantDetail = consultantDetail;
	}
	
}