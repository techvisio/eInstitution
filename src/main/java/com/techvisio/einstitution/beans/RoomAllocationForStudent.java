package com.techvisio.einstitution.beans;

import java.util.List;


public class RoomAllocationForStudent {

	private StudentBasicInfo basicInfo;
	private RoomAllocation activeAllocation;
	private List<RoomAllocation> previousAllocation;


public StudentBasicInfo getBasicInfo() {
	return basicInfo;
}
public void setBasicInfo(StudentBasicInfo basicInfo) {
	this.basicInfo = basicInfo;
}
public RoomAllocation getActiveAllocation() {
	return activeAllocation;
}
public void setActiveAllocation(RoomAllocation activeAllocation) {
	this.activeAllocation = activeAllocation;
}
public List<RoomAllocation> getPreviousAllocation() {
	return previousAllocation;
}
public void setPreviousAllocation(List<RoomAllocation> previousAllocation) {
	this.previousAllocation = previousAllocation;
}


}
