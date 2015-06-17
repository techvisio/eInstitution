package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.util.CommonUtil;

public class StudentAcademic {

	private String university;
	private String collegeName;
	private String passingYear;
	private float percentage;
	private String rollNo;
	private Long fileNo;
	private Long qualificationId;
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
}
