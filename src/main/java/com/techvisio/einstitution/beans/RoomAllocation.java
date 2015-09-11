package com.techvisio.einstitution.beans;

import java.sql.Date;

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
	@OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="Room_Id")
    private RoomTypeDetail roomTypeDetail;
	@Column(name = "Allocated_On")
    private Date allocatedOn;
	@Column(name = "Allocated_By")
    private String allocatedBy;
	@Column(name = "Checkout_On")
    private Date checkoutOn;
	@Column(name = "Is_Allocated")
    private boolean allocated;
	@Column(name = "Is_Active")
	private boolean isActive;
	@ManyToOne
	@JoinColumn(name="Wing_Id")
	private Wing wing;
	@ManyToOne
	@JoinColumn(name="Floor_Id")
	private Floor floor;
	@ManyToOne
	@JoinColumn(name="Block_Id")
	private Block block;
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

	public Wing getWing() {
		return wing;
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}
