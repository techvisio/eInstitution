package com.techvisio.einstitution.beans;

import java.util.List;

public class RoomAllocationDetailForRoom {

	private Integer capacity;
	private String roomNo;
	private List<StudentBasicInfo> basicInfos;
	
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
