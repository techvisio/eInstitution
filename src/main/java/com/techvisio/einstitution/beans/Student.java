package com.techvisio.einstitution.beans;

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
import javax.persistence.PrimaryKeyJoinColumn;
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
	@Column(name = "Registration_No")
	private String registrationNo;
	@Column(name = "Academic_Year")
	private String academicYear;
	@ManyToOne
	@JoinColumn(name="Course_Id")
	private Course course;
	@ManyToOne
	@JoinColumn(name="Branch_Id")
	private Branch branch;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<StudentAcademic> academicDtl;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<AdmissionDiscount> DiscountDtl=new ArrayList<AdmissionDiscount>();
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<Address> addressDtl=new ArrayList<Address>();
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private Scholarship scholarship;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<BranchPreference> branchPreference=new ArrayList<BranchPreference>();
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<Counselling> counsellingDtl=new ArrayList<Counselling>();
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<AdmissnConsltntDtl> consultantDetail;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="File_No")
	private List<Remark> remarks=new ArrayList<Remark>();
	@OneToMany
	@JoinColumn(name = "File_No")
	private List<StudentDocument> documents = new ArrayList<StudentDocument>();
   
	  @OneToOne(cascade = CascadeType.ALL)
	    @PrimaryKeyJoinColumn
	private StudentBasics studentBasics;
    
    
	@JsonIgnore
	public Scholarship getScholarship() {
		return scholarship;
	}

	@JsonProperty("scholarship")
	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
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


	@JsonIgnore
	public List<BranchPreference> getBranchPreference() {
		return branchPreference;
	}

	@JsonProperty("branchPreference")
	public void setBranchPreference(List<BranchPreference> branchPreference) {
		this.branchPreference = branchPreference;
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

	public StudentBasics getStudentBasics() {
		return studentBasics;
	}

	public void setStudentBasics(StudentBasics studentBasics) {
		this.studentBasics = studentBasics;
	}

}