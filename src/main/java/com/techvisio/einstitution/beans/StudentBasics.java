package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENT_BASIC")
public class StudentBasics extends BasicEntity{

	@Id
	@Column(name = "FILE_NO")
	private Long fileNo;
	@Column(name = "Admission_Mode")
	private String admissionMode;
	@Column(name = "Enroll_No")
	private String enrollNo;
	@Column(name = "Semester")
	private String semester;
	@Column(name = "First_Name")
	private String firstName;
	@Column(name = "Last_Name")
	private String lastName;
	@Column(name = "Uni_Enroll_No")
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
	@JoinColumn(name="Category_Id")
	private CasteCategory category;
	@Column(name = "Photo")
	private byte[] photo;
	@ManyToOne
	@JoinColumn(name="Quota_Id")
	private QuotaCode quotaCode;
	@Column(name = "Referred_By")
	private String referredBy;
	@Column(name = "Is_Lateral")
	private boolean lateral;
	@Column(name = "Application_Status")
	private Long applicationStatus;
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

	@JsonIgnore
	@MapsId
	   @JoinColumn(name = "FILE_NO", referencedColumnName = "FILE_NO")
	   @OneToOne(optional = false, fetch = FetchType.LAZY)
	private Student student;
	
	
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

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

	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	public boolean isLateral() {
		return lateral;
	}

	public void setLateral(boolean lateral) {
		this.lateral = lateral;
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
	public Student getStudent() {
		return student;
	}

	@JsonIgnore
	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(Long applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}
