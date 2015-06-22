package com.techvisio.einstitution.beans;

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
import javax.persistence.Table;

import com.techvisio.einstitution.util.CommonUtil;

@Entity
@Table(name = "ACADEMIC_DETAIL")
public class StudentAcademic {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Student_Qualification_Id")
	private Long stdntQualifctnId;
	@Column(name = "University")
	private String university;
	@Column(name = "College_Name")
	private String collegeName;
	@Column(name = "Passing_Year")
	private String passingYear;
	@Column(name = "Percentage")
	private float percentage;
	@Column(name = "Roll_No")
	private String rollNo;
	@Column(name = "File_No")
	private Long fileNo;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Qualification_Id")
	private Qualification qualification;
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Student_Qualification_Id")
	private List<QualificationSubject> qualificationSubDtl;

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}



	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
		CommonUtil.propogateIdentifierToQualification(this);
	}

	public List<QualificationSubject> getQualificationSubDtl() {
		return qualificationSubDtl;
	}

	public void setQualificationSubDtl(
			List<QualificationSubject> qualificationSubDtl) {
		this.qualificationSubDtl = qualificationSubDtl;
	}


	public Long getStdntQualifctnId() {
		return stdntQualifctnId;
	}

	public void setStdntQualifctnId(Long stdntQualifctnId) {
		this.stdntQualifctnId = stdntQualifctnId;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}


}
