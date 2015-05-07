package com.techvisio.einstitution.beans;

import java.util.List;

public class CurrentAllocation {

	private int capacity;
	private String roomNo;
	private List<StudentBasicInfo> basicInfos;
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public List<StudentBasicInfo> getBasicInfos() {
		return basicInfos;
	}

	public void setBasicInfos(List<StudentBasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}
}
