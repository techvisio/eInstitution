package com.techvisio.einstitution.beans;

public class TransportAllocation {

	private Long fileNo;
	private Long vehicleId;

	@Override
	public String toString() {
		return "TransportAllocation [fileNo=" + fileNo + ", vehicleId="
				+ vehicleId + "]";
	}

	

	public Long getFileNo() {
		return fileNo;
	}



	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}



	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}



	}

