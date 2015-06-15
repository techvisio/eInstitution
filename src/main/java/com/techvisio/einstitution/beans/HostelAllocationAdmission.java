package com.techvisio.einstitution.beans;

public class HostelAllocationAdmissionBean {

	private StudentBasicInfo basicInfo;
	private RoomAllocationDetail hostelAllocation;
	
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	
	public RoomAllocationDetail getHostelAllocation() {
		return hostelAllocation;
	}
	
	public void setHostelAllocation(RoomAllocationDetail hostelAllocation) {
		this.hostelAllocation = hostelAllocation;
	}
}
