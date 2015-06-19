package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transportallocation")
public class TransportAllocation {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name = "Vehicle_Id")
	@OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Type_Id")
	private VehicleDetail vehicleDetail;
	@Column(name = "Allocated_On")
	private String allocatedOn;
	@Column(name = "Allocated_By")
    private String allocatedBy;
	@Column(name = "Updated_By")
    private String updatedBy;
	@Column(name = "Switched_On")
    private String switchedOn;
	@Column(name = "Is_Allocated")
    private boolean allocated;
	@Column(name = "Remark")
    private String remark;



	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	public String getAllocatedOn() {
		return allocatedOn;
	}

	public void setAllocatedOn(String allocatedOn) {
		this.allocatedOn = allocatedOn;
	}

	public String getSwitchedOn() {
		return switchedOn;
	}

	public void setSwitchedOn(String switchedOn) {
		this.switchedOn = switchedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



		}

