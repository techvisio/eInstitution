package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roomallocationdetail")
public class RoomAllocation {
    
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name = "")
    private RoomTypeDetail roomTypeDetail;
	@Column(name = "Allocated_On")
    private String allocatedOn;
	@Column(name = "Alocated_By")
    private String allocatedBy;
	@Column(name = "Updated_By")
    private String updatedBy;
	@Column(name = "Checkout_On")
    private String checkoutOn;
	@Column(name = "Allocated")
    private boolean allocated;
	@Column(name = "Remark")
    private String remark;

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

	public String getCheckoutOn() {
		return checkoutOn;
	}

	public void setCheckoutOn(String checkoutOn) {
		this.checkoutOn = checkoutOn;
	}

	public String getAllocatedOn() {
		return allocatedOn;
	}

	public void setAllocatedOn(String allocatedOn) {
		this.allocatedOn = allocatedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
