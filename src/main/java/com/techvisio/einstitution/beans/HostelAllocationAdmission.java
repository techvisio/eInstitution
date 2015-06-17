package com.techvisio.einstitution.beans;

public class HostelAllocationAdmission {

	private StudentBasicInfo basicInfo;
	private RoomAllocation hostelAllocation;
	
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	
	public RoomAllocation getHostelAllocation() {
		return hostelAllocation;
	}
	
	public void setHostelAllocation(RoomAllocation hostelAllocation) {
		this.hostelAllocation = hostelAllocation;
	}
}
