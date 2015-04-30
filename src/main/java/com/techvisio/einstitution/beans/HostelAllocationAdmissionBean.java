package com.techvisio.einstitution.beans;

public class HostelAllocationAdmissionBean {

	private StudentBasicInfo basicInfo;
	private HostelAllocation hostelAllocation;
	
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	
	public HostelAllocation getHostelAllocation() {
		return hostelAllocation;
	}
	
	public void setHostelAllocation(HostelAllocation hostelAllocation) {
		this.hostelAllocation = hostelAllocation;
	}
}
