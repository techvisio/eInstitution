package com.techvisio.einstitution.beans;

public class QualificationSubjectDtl {

	private Long subjectId;
	private Long qualificationId;
	private Long fileNo;
	private double marksObtained;
	private double maxMarks;
	
	
	public Long getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	
	public Long getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
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

	@Override
	public String toString() {
		return "QualificationSubjectDtl [subjectId=" + subjectId
				+ ", qualificationId=" + qualificationId + ", fileNo=" + fileNo
				+ ", marksObtained=" + marksObtained + ", maxMarks=" + maxMarks
				+ "]";
	}
	
}
