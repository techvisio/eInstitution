package com.techvisio.einstitution.beans;

import java.util.Date;

public class TransportAllocation {

	private Long fileNo;
	private VehicleDetail vehicleDetail;
	private Date allocatedOn;
    private String allocatedBy;
    private String updatedBy;
    private Date switchedOn;
    private boolean allocated;
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

	public Date getAllocatedOn() {
		return allocatedOn;
	}

	public void setAllocatedOn(Date allocatedOn) {
		this.allocatedOn = allocatedOn;
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

	public Date getSwitchedOn() {
		return switchedOn;
	}

	public void setSwitchedOn(Date switchedOn) {
		this.switchedOn = switchedOn;
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



		}

