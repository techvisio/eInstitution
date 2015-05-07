package com.techvisio.einstitution.beans;

public class RoomTypeDetail {
private String	roomNo;
private String	typeCode;
private Long wingId;
private Long floorId;
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
}
