package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSPORT_RESERVATION")
public class TransportReservation extends BasicEntity{

	@Id
	@Column(name="File_No")
	private Long fileNo;
	@OneToOne
	@JoinColumn(name="Route_Stop_Id")
	private RouteStoppage routeStoppage;
	@Column(name = "Is_Active")
	private boolean isActive;
	@Column(name = "Allocation_Status")
	private String allocationStatus;
	@Column(name = "Price")
	private Double price;
	@Column(name = "Description")
	private String description;


	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
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

	public RouteStoppage getRouteStoppage() {
		return routeStoppage;
	}

	public void setRouteStoppage(RouteStoppage routeStoppage) {
		this.routeStoppage = routeStoppage;
	}


}
