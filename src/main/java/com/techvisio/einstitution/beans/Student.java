package com.techvisio.einstitution.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techvisio.einstitution.util.CommonUtil;

@Entity
@Table(name = "STUDENT_DETAIL")
public class Student extends BasicEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "File_No")
	private Long fileNo;

	@Column(name = "Admission_Mode")
	private String admissionMode;

	@Column(name = "Registration_No")
	private String registrationNo;
	@Column(name = "Enroll_No")
	private String enrollNo;
	@Column(name = "Academic_Year")
	private String academicYear;
	@Column(name = "Semester")
	private String semester;
	@ManyToOne
	@JoinColumn(name="Course_Id")
	private Course course;

	@ManyToOne
	@JoinColumn(name="Branch_Id")
	private Branch branch;
	@Column(name = "First_Name")
	private String firstName;
	@Column(name = "Last_Name")
	private String lastName;
	@Column(name = "UniEnroll_No")
	private String uniEnrollNo;
	@Column(name = "DOB")
	private Date dob;

	@Column(name = "Father_Name")
	private String fatherName;
	@Column(name = "Mother_Name")
	private String motherName;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Blood_Group")
	private String bloodGroup;
	@Column(name = "Email_Id")
	private String emailId;
	@Column(name = "Gaurdian_Email_Id")
	private String gaurdianEmailId;
	@Column(name = "FixedLine_No")
	private String fixedlineNo;
	@Column(name = "Self_Mobile_No")
	private String selfMobileNo;
	@Column(name = "Parent_Mobile_No")
	private String parentMobileNo;
	@Column(name = "Gaurdian_Mobile_No")
	private String gaurdianMobileNo;
	@Column(name = "Father_Occupation")
	private String fatherOccupation;
	@ManyToOne
	@JoinColumn(name="Categry_Id")
	private CasteCategory category;
	private boolean hostel;
	private boolean transportation;

	@Column(name = "Is_Management_Approved")
	private boolean managementApproval;
	@Column(name = "Is_Fee_Paid")
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
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	@JsonIgnore
	private List<StudentAcademic> academicDtl;

	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<AdmissionDiscount> DiscountDtl=new ArrayList<AdmissionDiscount>();

	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<Address> addressDtl=new ArrayList<Address>();

	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private Scholarship scholarship;

	@JsonIgnore
	public Scholarship getScholarship() {
		return scholarship;
	}

	@JsonProperty("scholarship")
	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
	}

	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<BranchPreference> branchPreference=new ArrayList<BranchPreference>();

	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<Counselling> counsellingDtl=new ArrayList<Counselling>();

	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<AdmissnConsltntDtl> consultantDetail;
	@ManyToOne
	@JoinColumn(name="Quota_Id")
	private QuotaCode quotaCode;
	@Column(name = "Referred_By")
	private String referredBy;
	@Column(name = "Is_Lateral")
	private boolean lateral;
	@Column(name = "Application_Status")
	private String applicationStatus;
	@ManyToOne
	@JoinColumn(name="Section_Id")
	private Section section;
	@ManyToOne
	@JoinColumn(name="Shift_Id")
	private Shift shift;
	@ManyToOne
	@JoinColumn(name="Centre_Id")
	private Centre centre;
	@ManyToOne
	@JoinColumn(name="Batch_Id")
	private Batch batch;
	@ManyToOne
	@JoinColumn(name="Session_Id")
	private Session session;
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="File_No")
	private List<Remark> remarks;
	
	@OneToMany
	@JoinColumn(name = "File_No")
	private List<StudentDocument> documents = new ArrayList<StudentDocument>();

	public CasteCategory getCategory() {
		return category;
	}

	public void setCategory(CasteCategory category) {
		this.category = category;
	}

	public QuotaCode getQuotaCode() {
		return quotaCode;
	}

	public void setQuotaCode(QuotaCode quotaCode) {
		this.quotaCode = quotaCode;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

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
		CommonUtil.propogateIdentifiertoAdmission(this);
	}


	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	@JsonIgnore
	public List<StudentAcademic> getAcademicDtl() {
		return academicDtl;
	}

	@JsonProperty("academicDtl")
	public void setAcademicDtl(List<StudentAcademic> academicDtl) {
		this.academicDtl = academicDtl;
	}

	@JsonIgnore
	public List<AdmissionDiscount> getDiscountDtl() {
		return DiscountDtl;
	}

	@JsonProperty("discountDtl")
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

	@JsonIgnore
	public List<Address> getAddressDtl() {
		return addressDtl;
	}

	@JsonProperty("addressDtl")
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

	@JsonIgnore
	public List<BranchPreference> getBranchPreference() {
		return branchPreference;
	}

	@JsonProperty("branchPreference")
	public void setBranchPreference(List<BranchPreference> branchPreference) {
		this.branchPreference = branchPreference;
	}


	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	@JsonIgnore
	public List<Counselling> getCounsellingDtl() {
		return counsellingDtl;
	}

	@JsonProperty("counsellingDtl")
	public void setCounsellingDtl(List<Counselling> counsellingDtl) {
		this.counsellingDtl = counsellingDtl;
	}

	@JsonIgnore
	public List<AdmissnConsltntDtl> getConsultantDetail() {
		return consultantDetail;
	}

	@JsonProperty("consultantDetail")
	public void setConsultantDetail(List<AdmissnConsltntDtl> consultantDetail) {
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


	public Date getDob() {
		return dob;
	}

	public void setDob(Date date) {
		this.dob = date;
	}


	public void setFixedlineNo(String fixedlineNo) {
		this.fixedlineNo = fixedlineNo;
	}

	public void setSelfMobileNo(String selfMobileNo) {
		this.selfMobileNo = selfMobileNo;
	}

	@JsonIgnore
	public List<Remark> getRemarks() {
		return remarks;
	}

	@JsonProperty("remarks")
	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}

	@JsonIgnore
	public List<StudentDocument> getDocuments() {
		return documents;
	}

	@JsonProperty("documents")
	public void setDocuments(List<StudentDocument> documents) {
		this.documents = documents;
	}

}