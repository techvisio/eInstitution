package com.techvisio.einstitution.beans;

public class FeeDetail {
private long course;
private long branch;
private long semester;
private long feeHeadId;
private Double feeAmount;
public long getCourse() {
	return course;
}
public void setCourse(long course) {
	this.course = course;
}
public long getBranch() {
	return branch;
}
public void setBranch(long branch) {
	this.branch = branch;
}
public long getSemester() {
	return semester;
}
public void setSemester(long semester) {
	this.semester = semester;
}
public long getFeeHeadId() {
	return feeHeadId;
}
public void setFeeHeadId(long feeHeadId) {
	this.feeHeadId = feeHeadId;
}
public Double getFeeAmount() {
	return feeAmount;
}
public void setFeeAmount(Double feeAmount) {
	this.feeAmount = feeAmount;
}
@Override
public String toString() {
	return "FeeDetail [course=" + course + ", branch=" + branch + ", semester="
			+ semester + ", feeHeadId=" + feeHeadId + ", feeAmount="
			+ feeAmount + "]";
}


}
