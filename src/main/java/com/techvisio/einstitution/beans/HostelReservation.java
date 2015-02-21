package com.techvisio.einstitution.beans;

import java.util.List;

public class HostelReservation {
private String	fileNo ;
private	boolean feePaid ;
private String	typeCode;
private String allocationStatus;
private boolean isActive;
private Double price;
private String description;
public String getFileNo() {
	return fileNo;
}
public void setFileNo(String fileNo) {
	this.fileNo = fileNo;
}

public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}
public boolean isFeePaid() {
	return feePaid;
}
public void setFeePaid(boolean feePaid) {
	this.feePaid = feePaid;
}
public String getAllocationStatus() {
	return allocationStatus;
}
public void setAllocationStatus(String allocationStatus) {
	this.allocationStatus = allocationStatus;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}


public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "HostelReservation [fileNo=" + fileNo + ", feePaid=" + feePaid
			+ ", typeCode=" + typeCode + ", allocationStatus="
			+ allocationStatus + ", isActive=" + isActive + "]";
}

}
