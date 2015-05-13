package com.techvisio.einstitution.beans;

import java.util.List;

public class TransportAllocationDetailForVehicle {
	private Integer capacity;
	private Long vehicleId;
	private List<StudentBasicInfo> basicInfos;
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public List<StudentBasicInfo> getBasicInfos() {
		return basicInfos;
	}
	public void setBasicInfos(List<StudentBasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}
}
