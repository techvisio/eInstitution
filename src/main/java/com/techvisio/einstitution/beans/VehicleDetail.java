package com.techvisio.einstitution.beans;

public class VehicleDetail {

	private Long vehicleId;
	private String type;
	private String capacity;
	private String vehicleNo;
	private String routeCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
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

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "VehicleDetail [vehicleId=" + vehicleId + ", type=" + type
				+ ", capacity=" + capacity + ", vehicleNo=" + vehicleNo
				+ ", routeCode=" + routeCode + "]";
	}


}
