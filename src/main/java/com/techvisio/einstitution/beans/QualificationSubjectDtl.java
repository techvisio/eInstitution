package com.techvisio.einstitution.beans;

public class QualificationSubjectDtl {

	private int subjectId;
	private int qualificationId;
	private String enrollNo;
	private double marksObtained;
	private double maxMarks;
	
	
	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
	public int getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	
	public String getEnrollNo() {
		return enrollNo;
	}
	
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	
	
	public double getMarksObtained() {
		return marksObtained;
	}
	
	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}
	
	
	public double getMaxMarks() {
		return maxMarks;
	}
	
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	
}
