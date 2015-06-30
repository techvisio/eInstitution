package com.techvisio.einstitution.beans;

import java.sql.Date;

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
@Table(name = "TRANSPORT_ALLOCATION")
public class TransportAllocation extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "transport_Alloctionn_Id")
	private Long trnsprtAllctnId;
	@Column(name="File_No")
	private Long fileNo;
	@OneToOne
	@JoinColumn(name = "Type_Id")
	private VehicleDetail vehicleDetail;
	@Column(name = "Allocated_On")
	private Date allocatedOn;
	@Column(name = "Allocated_By")
	private String allocatedBy;
	@Column(name = "Switched_On")
	private Date switchedOn;
	@Column(name = "Is_Allocated")
	private boolean allocated;
	
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

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
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

}

