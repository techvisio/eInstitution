package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roomtypemaster")

public class RoomTypeDetail {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Room_No")
	private String	roomNo;
	@Column(name = "Type_Code")
	private String	typeCode;
	@Column(name = "Wing_Id")
	private Long wingId;
	@Column(name = "Floor_Id")
	private Long floorId;
	@Column(name = "Block_Id")
	private Long blockId;

	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	@Override
	public String toString() {
		return "RoomTypeDetail [roomNo=" + roomNo + ", typeCode=" + typeCode + "]";
	}
	public Long getWingId() {
		return wingId;
	}
	public void setWingId(Long wingId) {
		this.wingId = wingId;
	}
	public Long getFloorId() {
		return floorId;
	}
	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
