package com.techvisio.einstitution.beans;

public class QualificationSubjectDtl {

	private int subjectId;
	private int qualificationId;
	private String fileNo;
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
	
	
	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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
