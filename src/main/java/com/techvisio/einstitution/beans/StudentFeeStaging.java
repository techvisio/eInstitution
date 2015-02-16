package com.techvisio.einstitution.beans;

public class StudentFeeStaging {
	private String fileNo;
	private Long course;
	private Long branch;
	private int semester;
	private boolean feeGenerated;
	
	
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public Long getCourse() {
		return course;
	}
	public void setCourse(Long course) {
		this.course = course;
	}
	public Long getBranch() {
		return branch;
	}
	public void setBranch(Long branch) {
		this.branch = branch;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public boolean isFeeGenerated() {
		return feeGenerated;
	}
	
	public void setFeeGenerated(boolean feeGenerated) {
		this.feeGenerated = feeGenerated;
	}
	
	@Override
	public String toString() {
		return "StudentFeeStaging [fileNo=" + fileNo + ", course=" + course
				+ ", branch=" + branch + ", semester=" + semester
				+ ", feeGenerated=" + feeGenerated + "]";
	}
		

}

