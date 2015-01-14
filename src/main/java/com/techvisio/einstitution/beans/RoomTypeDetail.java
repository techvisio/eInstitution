package com.techvisio.einstitution.beans;

public class RoomTypeDetail {
private String	roomNo ;
private String	typeCode ;
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
}
