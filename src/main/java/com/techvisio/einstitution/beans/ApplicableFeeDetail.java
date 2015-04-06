package com.techvisio.einstitution.beans;

public class ApplicableFeeDetail {
private Long course;
private Long branch;
private FeeDiscountHead feeDetail;
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

public FeeDiscountHead getFeeDetail() {
	return feeDetail;
}
public void setFeeDetail(FeeDiscountHead feeDetail) {
	this.feeDetail = feeDetail;
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
	return "ApplicableFeeDetail [course=" + course + ", branch=" + branch
			+ ", feeDetail=" + feeDetail + ", feeAmount=" + feeAmount
			+ ", centreId=" + centreId + ", shiftId=" + shiftId
			+ ", sessionId=" + sessionId + "]";
}


}
