package com.techvisio.einstitution.beans;

public class TransportReservation {

	private String fileNo;
	private String routeCode;
	private boolean feePaid;
    private boolean isActive;
    private String allocationStatus;
	
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
				+ routeCode + ", feePaid=" + feePaid + ", isActive=" + isActive
				+ ", allocationStatus=" + allocationStatus + "]";
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
	}
	
}
