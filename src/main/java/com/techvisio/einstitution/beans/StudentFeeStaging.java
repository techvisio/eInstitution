package com.techvisio.einstitution.beans;

public class StudentFeeStaging {
	private String fileNo;
	private int course;
	private int branch;
	private int semester;
	private boolean feeGenerated;
	
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public int getBranch() {
		return branch;
	}
	public void setBranch(int branch) {
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

