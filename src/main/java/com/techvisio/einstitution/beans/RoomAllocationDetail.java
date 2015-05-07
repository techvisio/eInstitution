package com.techvisio.einstitution.beans;

import java.util.Date;

public class RoomAllocationDetail {

	private Long fileNo ;
    private RoomTypeDetail roomTypeDetail;
    private Date allocatedOn;
    private String allocatedBy;
    private String updatedBy;
    private Date checkoutOn;
    private boolean allocated;

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public RoomTypeDetail getRoomTypeDetail() {
		return roomTypeDetail;
	}

	public void setRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		this.roomTypeDetail = roomTypeDetail;
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

	public Date getCheckoutOn() {
		return checkoutOn;
	}

	public void setCheckoutOn(Date checkoutOn) {
		this.checkoutOn = checkoutOn;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

}
