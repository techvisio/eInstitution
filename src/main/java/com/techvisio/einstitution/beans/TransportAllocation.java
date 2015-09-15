package com.techvisio.einstitution.beans;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TRANSPORT_ALLOCATION")
public class TransportAllocation extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "transport_Alloctionn_Id")
	private Long trnsprtAllctnId;
	@Column(name="File_No")
	private Long fileNo;
	@ManyToOne
	@JoinColumn(name = "Vehicle_Id")
	private VehicleDetail vehicleDetail;
	@Column(name = "Allocated_On")
	private Date allocatedOn;
	@Column(name = "Allocated_By")
	private String allocatedBy;
	@Column(name = "Switched_On")
	private Date switchedOn;
	@Column(name = "Is_Active")
	private boolean active;
	
	public Long getFielNo() {
		return fileNo;
	}

	public void setFielNo(Long fielNo) {
		this.fileNo = fielNo;
	}
	
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "Remark")
	private String remark;


	public VehicleDetail getVehicleDetail() {
		return vehicleDetail;
	}

	public void setVehicleDetail(VehicleDetail vehicleDetail) {
		this.vehicleDetail = vehicleDetail;
	}

	public String getAllocatedBy() {
		return allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAllocatedOn() {
		return allocatedOn;
	}

	public void setAllocatedOn(Date date) {
		this.allocatedOn = date;
	}

	public Date getSwitchedOn() {
		return switchedOn;
	}

	public void setSwitchedOn(Date date) {
		this.switchedOn = date;
	}

	public Long getTrnsprtAllctnId() {
		return trnsprtAllctnId;
	}

	public void setTrnsprtAllctnId(Long trnsprtAllctnId) {
		this.trnsprtAllctnId = trnsprtAllctnId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}

