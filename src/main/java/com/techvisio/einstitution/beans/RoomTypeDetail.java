package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_TYPE_DETAIL")

public class RoomTypeDetail extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Room_Detail_Id")
	private Long roomDetailId;
	@Column(name = "Room_No")
	private String	roomNo;
	@ManyToOne
	@JoinColumn(name="Type_Code")
	private RoomType roomType;
	@ManyToOne
	@JoinColumn(name="Wing_Id")
	private Wing wing;
	@ManyToOne
	@JoinColumn(name="Floor_Id")
	private Floor floor;
	@ManyToOne
	@JoinColumn(name="Block_Id")
	private Block block;

	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
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
	public Long getRoomDetailId() {
		return roomDetailId;
	}
	public void setRoomDetailId(Long roomDetailId) {
		this.roomDetailId = roomDetailId;
	}
}
