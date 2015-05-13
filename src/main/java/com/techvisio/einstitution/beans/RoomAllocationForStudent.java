package com.techvisio.einstitution.beans;

import java.util.List;


public class RoomAllocationForStudent {

	private StudentBasicInfo basicInfo;
	private RoomAllocationDetail activeAllocation;
	private List<RoomAllocationDetail> previousAllocation;


public StudentBasicInfo getBasicInfo() {
	return basicInfo;
}
public void setBasicInfo(StudentBasicInfo basicInfo) {
	this.basicInfo = basicInfo;
}
public RoomAllocationDetail getActiveAllocation() {
	return activeAllocation;
}
public void setActiveAllocation(RoomAllocationDetail activeAllocation) {
	this.activeAllocation = activeAllocation;
}
public List<RoomAllocationDetail> getPreviousAllocation() {
	return previousAllocation;
}
public void setPreviousAllocation(List<RoomAllocationDetail> previousAllocation) {
	this.previousAllocation = previousAllocation;
}


}
