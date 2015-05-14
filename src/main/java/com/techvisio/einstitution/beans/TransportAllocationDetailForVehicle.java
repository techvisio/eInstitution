package com.techvisio.einstitution.beans;

import java.util.List;

public class TransportAllocationDetailForVehicle {
	private Integer capacity;
	private Long vehicleId;
	private String vehicleNo;
	private String routeCode;
	private String description;
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
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
