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
@Table(name = "ROOM_ALLOCATION_DETAIL")
public class RoomAllocation extends BasicEntity {
    
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Room_Allocation_Id")
	private Long roomAllocationId;
	@Column(name="File_No")
	private Long fileNo;
	@ManyToOne
    @JoinColumn(name="Room_Id")
    private RoomTypeDetail roomTypeDetail;
	@Column(name = "Allocated_On")
    private Date allocatedOn;
	@Column(name = "Allocated_By")
    private String allocatedBy;
	@Column(name = "Checkout_On")
    private Date checkoutOn;
	@Column(name = "Is_Active")
	private boolean active;
	@Column(name = "Remark")
    private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCheckoutOn() {
		return checkoutOn;
	}

	public void setCheckoutOn(Date date) {
		this.checkoutOn = date;
	}

	public Date getAllocatedOn() {
		return allocatedOn;
	}

	public void setAllocatedOn(Date date) {
		this.allocatedOn = date;
	}

	public Long getRoomAllocationId() {
		return roomAllocationId;
	}

	public void setRoomAllocationId(Long iroomAllocationIdd) {
		this.roomAllocationId = iroomAllocationIdd;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}
