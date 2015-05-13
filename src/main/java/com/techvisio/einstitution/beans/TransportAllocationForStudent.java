package com.techvisio.einstitution.beans;

import java.util.List;

public class TransportAllocationForStudent {
	private StudentBasicInfo basicInfo;
	private TransportAllocation activeAllocation;
	private List<TransportAllocation> previousAllocation;
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	public TransportAllocation getActiveAllocation() {
		return activeAllocation;
	}
	public void setActiveAllocation(TransportAllocation activeAllocation) {
		this.activeAllocation = activeAllocation;
	}
	public List<TransportAllocation> getPreviousAllocation() {
		return previousAllocation;
	}
	public void setPreviousAllocation(List<TransportAllocation> previousAllocation) {
		this.previousAllocation = previousAllocation;
	}
}
