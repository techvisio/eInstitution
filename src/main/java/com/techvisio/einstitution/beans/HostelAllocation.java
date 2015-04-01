package com.techvisio.einstitution.beans;

public class HostelAllocation {
private String	roomNo ;
private	String wing ;
private String	floor;
private String	block ;
private String	name;
private Long	fileNo ;
public String getRoomNo() {
	return roomNo;
}
public void setRoomNo(String roomNo) {
	this.roomNo = roomNo;
}
public String getWing() {
	return wing;
}
public void setWing(String wing) {
	this.wing = wing;
}
public String getFloor() {
	return floor;
}
public void setFloor(String floor) {
	this.floor = floor;
}
public String getBlock() {
	return block;
}
public void setBlock(String block) {
	this.block = block;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public Long getFileNo() {
	return fileNo;
}
public void setFileNo(Long fileNo) {
	this.fileNo = fileNo;
}
@Override
public String toString() {
	return "HostelAllocation [roomNo=" + roomNo + ", wing=" + wing + ", floor="
			+ floor + ", block=" + block + ", name=" + name + ", fileNo="
			+ fileNo + "]";
}
}
