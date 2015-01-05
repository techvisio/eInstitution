package com.techvisio.einstitution.beans;

public class TransportReservation {

	private String fileNo;
	private String routeCode;
	private boolean feePaid;

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	@Override
	public String toString() {
		return "TransportReservation [fileNo=" + fileNo + ", routeCode="
				+ routeCode + ", feePaid=" + feePaid + "]";
	}
	
}
