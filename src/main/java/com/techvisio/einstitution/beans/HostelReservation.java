package com.techvisio.einstitution.beans;

import java.util.List;

public class HostelReservation {
private String	fileNo ;
private	boolean feePaid ;
private String	typeCode;
private List<HostelAvailability> hostelAvailability;
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

public List<HostelAvailability> getHostelAvailability() {
	return hostelAvailability;
}
public void setHostelAvailability(List<HostelAvailability> hostelAvailability) {
	this.hostelAvailability = hostelAvailability;
}
@Override
public String toString() {
	return "HostelReservation [fileNo=" + fileNo + ", feePaid=" + feePaid
			+ ", typeCode=" + typeCode + ", hostelAvailability="
			+ hostelAvailability + "]";
}

}
