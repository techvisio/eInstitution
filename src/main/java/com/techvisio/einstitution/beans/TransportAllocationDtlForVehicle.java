package com.techvisio.einstitution.beans;

import java.util.List;

public class TransportAllocationDtlForVehicle {

	private Integer capacity;
	private Long vehicleId;
	private List<StudentBasicInfo> basicInfo;
	
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<StudentBasicInfo> getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(List<StudentBasicInfo> basicInfo) {
		this.basicInfo = basicInfo;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
}
