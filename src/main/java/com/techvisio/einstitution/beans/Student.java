package com.techvisio.einstitution.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.DynamicProperties;

@Entity
@Table(name = "studentDetail")
public class Student {

	@Column(name = "Admission_Mode")
	private String admissionMode;

	@Column(name = "Registration_No")
    private String registrationNo;
	@Id
	@Column(name = "File__No")
	private Long fileNo;
	@Column(name = "Enroll_No")
	private String enrollNo;
	@Column(name = "Academic_Year")
	private String academicYear;
	@Column(name = "Semester")
	@DynamicProperties(id="semester", title="Semester")
	private String semester;
	@Column(name = "Course_Id")
	@DynamicProperties(id="courseId", title="course", type="select", masterDataCode=AppConstants.COURSE)
	private Long courseId;
	@Column(name = "Branch_Id")
	@DynamicProperties(id="branchId", title="branch", type="select", masterDataCode=AppConstants.BRANCH)
	private Long branchId;
	@Column(name = "First_Name")
	@DynamicProperties(id="firstName", title="First Name")
	private String firstName;
	@Column(name = "Last_Name")
	@DynamicProperties(id="lastName", title="Last Name")
	private String lastName;
	@Column(name = "UniEnroll_No")
	@DynamicProperties(id="uniEnrollNo", title="University Enrollment No")
	private String uniEnrollNo;
	@Column(name = "DOB")
	@DynamicProperties(id="dob", title="DOB", type="date")
	private String dob;
	
	@Column(name = "Father_Name")
	@DynamicProperties(id="fatherName", title="Father Name")
	private String fatherName;
	@Column(name = "Mother_Name")
	@DynamicProperties(id="motherName", title="Mother Name")
	private String motherName;
	@Column(name = "Gender")
	@DynamicProperties(id="gender", title="Gender", type="radio", validValues={"Male","Female"})
	private String gender;
	@Column(name = "Blood_Group")
	@DynamicProperties(id="bloodGroup", title="Blood Group", type="select")
	private String bloodGroup;
	@Column(name = "Email_Id")
	@DynamicProperties(id="emailId", title="Student Email Id", masterDataCode=AppConstants.BLOODGROUP)
	private String emailId;
	@Column(name = "Gaurdian_Email_Id")
	@DynamicProperties(id="gaurdianEmailId", title="Gaurdian Email Id")
	private String gaurdianEmailId;
	@Column(name = "FixedLine_No")
	@DynamicProperties(id="fixedlineNo", title="Fixed Line No")
	private String fixedlineNo;
	@Column(name = "Self_Mobile_No")
	@DynamicProperties(id="selfMobileNo", title="Self Mobile No")
	private String selfMobileNo;
	@Column(name = "Parent_Mobile_No")
	@DynamicProperties(id="parentMobileNo", title="Parent Cell No")
	private String parentMobileNo;
	@Column(name = "Gaurdian_Mobile_No")
	@DynamicProperties(id="gaurdianMobileNo", title="Gaurdian Mobile No")
	private String gaurdianMobileNo;
	@Column(name = "Father_Occupation")
	@DynamicProperties(id="fatherOccupation", title="Father Occupation")
	private String fatherOccupation;
	@Column(name = "Category_Id")
	@DynamicProperties(id="categoryId", title="category", type="select" , masterDataCode=AppConstants.CATEGORY)
	private Long categoryId;
	@DynamicProperties(id="hostel", title="Hostel Required",type="check")
	private boolean hostel;
	@DynamicProperties(id="transportation", title="Transport Required", type="check")
	private boolean transportation;

	@Column(name = "Is_Management_Approved")
	@DynamicProperties(id="managementApproval", title="managementApproval" , type="check")
	private boolean managementApproval;
	@Column(name = "Is_Fee_Paid")
	@DynamicProperties(id="feePaid", title="feePaid", type="check")
	private boolean feePaid;
	
	@Column(name = "Photo")
	private byte[] photo;
//	private String feeReceivedBy;
//	private Date feeReceivedOn;
//	private String documentReceivedBy;
//	private Date documentReceivedOn;
//	private String documentVerifiedBy;
//	private Date documentVerifiedOn;
//	private String managementApprovedBy;
//	private Date managementApprovedOn;
	@ManyToOne
	
	@Column(name = "Id")
	private List<StudentAcademic> academicDtl=new ArrayList<StudentAcademic>();
	@ManyToOne
	@Column(name = "Id")
	private List<AdmissionDiscount> DiscountDtl=new ArrayList<AdmissionDiscount>();
	@Column(name = "Id")
	private List<Address> addressDtl=new ArrayList<Address>();
	@Column(name = "Id")
	private List<BranchPreference> branchPreference=new ArrayList<BranchPreference>();
	@Column(name = "Id")
	private List<Counselling> counsellingDtl=new ArrayList<Counselling>();
	@Column(name = "Id")
	private TransportReservation reservation;
	@Column(name = "Id")
    private List<ConsultantDetail> consultantDetail;
	@Column(name = "Id")
    private Scholarship scholarshipDetail;
    @Column(name = "Id")
    private String quotaCode;
    @Column(name = "Id")
    private String referredBy;
    @Column(name = "Id")
    private boolean lateral;
    @Column(name = "Application_Status")
    private String applicationStatus;
    @Column(name = "Id")
    private Long sectionId;
    @Column(name = "Id")
    private Long shiftId;
    @Column(name = "Id")
    private Long centreId;
    @Column(name = "Id")
    private Long batchId;
    @Column(name = "Id")
    private Long sessionId;
    private List<Remark> remarks;
    
	
	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

    
    public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	public List<StudentAcademic> getAcademicDtl() {
		return academicDtl;
	}

	public void setAcademicDtl(List<StudentAcademic> academicDtl) {
		this.academicDtl = academicDtl;
	}

	public List<AdmissionDiscount> getDiscountDtl() {
		return DiscountDtl;
	}

	public void setDiscountDtl(List<AdmissionDiscount> discountDtl) {
		this.DiscountDtl = discountDtl;
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


	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

		public List<Address> getAddressDtl() {
		return addressDtl;
	}

	public void setAddressDtl(List<Address> addressDtl) {
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

	public List<Counselling> getCounsellingDtl() {
		return counsellingDtl;
	}

	public void setCounsellingDtl(List<Counselling> counsellingDtl) {
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

	public Scholarship getScholarshipDetail() {
		return scholarshipDetail;
	}

	public void setScholarshipDetail(Scholarship scholarshipDetail) {
		this.scholarshipDetail = scholarshipDetail;
	}

	public List<ConsultantDetail> getConsultantDetail() {
		return consultantDetail;
	}

	public void setConsultantDetail(List<ConsultantDetail> consultantDetail) {
		this.consultantDetail = consultantDetail;
	}

	public boolean isLateral() {
		return lateral;
	}

	public void setLateral(boolean lateral) {
		this.lateral = lateral;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


	public void setFixedlineNo(String fixedlineNo) {
		this.fixedlineNo = fixedlineNo;
	}

	public void setSelfMobileNo(String selfMobileNo) {
		this.selfMobileNo = selfMobileNo;
	}

	public List<Remark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}

}