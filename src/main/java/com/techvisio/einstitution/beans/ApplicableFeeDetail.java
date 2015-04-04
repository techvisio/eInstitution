package com.techvisio.einstitution.beans;

public class FeeDetail {
private Long course;
private Long branch;
private Long feeHeadId;
private Double feeAmount;
private Long centreId;
private Long shiftId;
private Long sessionId;

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

public Long getCentreId() {
	return centreId;
}
public void setCentreId(Long centreId) {
	this.centreId = centreId;
}
public Long getShiftId() {
	return shiftId;
}
public void setShiftId(Long shiftId) {
	this.shiftId = shiftId;
}
public Long getSessionId() {
	return sessionId;
}
public void setSessionId(Long sessionId) {
	this.sessionId = sessionId;
}
@Override
public String toString() {
	return "FeeDetail [course=" + course + ", branch=" + branch
			+ ", feeHeadId=" + feeHeadId + ", feeAmount=" + feeAmount
			+ ", centreId=" + centreId + ", shiftId=" + shiftId
			+ ", sessionId=" + sessionId + "]";
}


}
