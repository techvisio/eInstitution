
package com.techvisio.einstitution.beans;

public class HostelInventory {
private String	typeCode; 
private String	description;
private int	threshold;
private Double	price;
private int	roomCapacity;
public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getThreshold() {
	return threshold;
}
public void setThreshold(int threshold) {
	this.threshold = threshold;
}
public int getRoomCapacity() {
	return roomCapacity;
}
public void setRoomCapacity(int roomCapacity) {
	this.roomCapacity = roomCapacity;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
@Override
public String toString() {
	return "HostelInventory [typeCode=" + typeCode + ", description="
			+ description + ", threshold=" + threshold + ", price=" + price
			+ ", roomCapacity=" + roomCapacity + "]";
}


}
