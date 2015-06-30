package com.techvisio.einstitution.beans;

public class ApplicableFeeCriteria {
private Long courseId;
private Long branchId;
private Long sessionId;
private Long centreId;
private Long shiftId;
private boolean isLateral;
public Long getCourseId() {
	return courseId;
}
public void setCourseId(Long courseId) {
	this.courseId = courseId;
}
public Long getBranchId() {
	return branchId;
}
public void setBranchId(Long branchId) {
	this.branchId = branchId;
}
public Long getSessionId() {
	return sessionId;
}
public void setSessionId(Long sessionId) {
	this.sessionId = sessionId;
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
public boolean isLateral() {
	return isLateral;
}
public void setLateral(boolean isLateral) {
	this.isLateral = isLateral;
}

}
