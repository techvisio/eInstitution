package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOSTEL_RESERVATION")
public class HostelReservation extends BasicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hostl_Rsrvation_Id")
	private Long hostlRsrvationId;

	@Column(name = "File_No")
	private Long fileNo;

	@Column(name = "Is_Fee_Paid")
	private boolean feePaid;
	
	@Column(name = "Type_Code")
	private String typeCode;

	@Column(name = "Allocation_Status")
	private String allocationStatus;

	@Column(name = "Is_Active")
	private boolean active;
	
	@Column(name = "Charges")
	private Double charges;
	
	@Column(name = "Is_Fee_Generated")
	private boolean feeGenerated;

	@Column(name = "Price")
	private Double price;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public boolean isFeeGenerated() {
		return feeGenerated;
	}

	public void setFeeGenerated(boolean feeGenerated) {
		this.feeGenerated = feeGenerated;
	}

	@Column(name = "Description")
	private String description;

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
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

	public Long getHostlRsrvationId() {
		return hostlRsrvationId;
	}

	public void setHostlRsrvationId(Long hostlRsrvationId) {
		this.hostlRsrvationId = hostlRsrvationId;
	}

}
