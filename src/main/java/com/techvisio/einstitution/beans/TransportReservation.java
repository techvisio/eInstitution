package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSPORT_RESERVATION")
public class TransportReservation {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long trnsprtRsrvtnId;
    @Column(name="File_No")
	private Long fileNo;
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "Route_Code")
	private String routeCode;
	@Column(name = "Fee_Paid")
	private boolean feePaid;
	@Column(name = "Is_Active")
    private boolean isActive;
	@Column(name = "Allocation_Status")
    private String allocationStatus;
	@Column(name = "Price")
    private Double price;
	@Column(name = "Description")
    private String description;
 

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

	public Long getTrnsprtRsrvtnId() {
		return trnsprtRsrvtnId;
	}

	public void setTrnsprtRsrvtnId(Long trnsprtRsrvtnId) {
		this.trnsprtRsrvtnId = trnsprtRsrvtnId;
	}

}
