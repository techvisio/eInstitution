package com.techvisio.einstitution.beans;

public class TransportReservation {

	private Long fileNo;
	private String routeCode;
	private boolean feePaid;
    private boolean isActive;
    private String allocationStatus;
    private Double price;
    private String description;
    

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
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
				+ ", allocationStatus=" + allocationStatus + ", price=" + price
				+ ", description=" + description + "]";
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
