package com.techvisio.einstitution.beans;

import java.util.List;

public class StudentAcademicDetail {

	private String university;
	private String collegeName;
	private String passingYear;
	private float percentage;
	private String rollNo;
	private String fileNo;
	private int qualificationId;
	private List<QualificationSubjectDtl> qualificationSubDtl;
	
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
	
	
	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
	
	public int getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public List<QualificationSubjectDtl> getQualificationSubDtl() {
		return qualificationSubDtl;
	}

	public void setQualificationSubDtl(List<QualificationSubjectDtl> qualificationSubDtl) {
		this.qualificationSubDtl = qualificationSubDtl;
	} 
}
