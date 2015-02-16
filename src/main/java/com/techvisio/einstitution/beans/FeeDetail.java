package com.techvisio.einstitution.beans;

public class FeeDetail {
private Long course;
private Long branch;
private int semester;
private Long feeHeadId;
private Double feeAmount;


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

public Long getFeeHeadId() {
	return feeHeadId;
}
public void setFeeHeadId(Long feeHeadId) {
	this.feeHeadId = feeHeadId;
}
public Double getFeeAmount() {
	return feeAmount;
}
public void setFeeAmount(Double feeAmount) {
	this.feeAmount = feeAmount;
}
public int getSemester() {
	return semester;
}
public void setSemester(int semester) {
	this.semester = semester;
}
@Override
public String toString() {
	return "FeeDetail [course=" + course + ", branch=" + branch + ", semester="
			+ semester + ", feeHeadId=" + feeHeadId + ", feeAmount="
			+ feeAmount + "]";
}



}
