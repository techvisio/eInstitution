package com.techvisio.einstitution.beans;

import java.util.Date;

public class StudentBasicInfo {
	
	@Override
	public String toString() {
		return "StudentBasicInfo [registrationNo=" + registrationNo
				+ ", fileNo=" + fileNo + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", enrollmentNo=" + enrollmentNo
				+ ", fatherName=" + fatherName + ", gender=" + gender
				+ ", dob=" + dob + ", course=" + course + ", branch=" + branch
				+ ", semester=" + semester + ", academicYear=" + academicYear
				+ ", casteCategory=" + casteCategory + ", batch=" + batch
				+ ", session=" + session + ", shift=" + shift + ", centre="
				+ centre + ", section=" + section + ", isLateral=" + isLateral
				+ ", modifiedDate=" + modifiedDate + ", remark=" + remark + "]";
	}
	private String registrationNo;
	private Long fileNo;
	private String firstName;
	private String lastName;
	private String enrollmentNo;
	private String fatherName;
	private String gender;
	private Date dob;
	private Course course;
	private Branch branch;
	private String semester;
	private String academicYear;
	private CasteCategory casteCategory; 
	private Batch batch;
	private Session session;
	private Shift shift;
	private Centre centre;
    private Section section;
	private boolean isLateral;
	private Date modifiedDate;
	private Remark remark;
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public String getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	public CasteCategory getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(CasteCategory casteCategory) {
		this.casteCategory = casteCategory;
	}
	public Remark getRemark() {
		return remark;
	}
	public void setRemark(Remark remark) {
		this.remark = remark;
	}
	
	public boolean isLateral() {
		return isLateral;
	}
	public void setLateral(boolean isLateral) {
		this.isLateral = isLateral;
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
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
}
