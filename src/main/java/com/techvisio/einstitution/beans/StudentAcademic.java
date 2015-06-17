package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.techvisio.einstitution.util.CommonUtil;

@Entity
@Table(name = "academicdetail")
public class StudentAcademic {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
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
	@Column(name = "Qualification_Id")
	private Long qualificationId;
	@Column(name = "Id")
	@ManyToOne
	@JoinColumn(name="subjectId")
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

	public Long getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}

	public List<QualificationSubject> getQualificationSubDtl() {
		return qualificationSubDtl;
	}

	public void setQualificationSubDtl(
			List<QualificationSubject> qualificationSubDtl) {
		this.qualificationSubDtl = qualificationSubDtl;
	}

	@Override
	public String toString() {
		return "StudentAcademicDetail [university=" + university
				+ ", collegeName=" + collegeName + ", passingYear="
				+ passingYear + ", percentage=" + percentage + ", rollNo="
				+ rollNo + ", fileNo=" + fileNo + ", qualificationId="
				+ qualificationId + ", qualificationSubDtl="
				+ qualificationSubDtl + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
