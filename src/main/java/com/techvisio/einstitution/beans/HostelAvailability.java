package com.techvisio.einstitution.beans;

public class HostelAvailability {
	 
	private int	threshold;
	private String	typeCode;
	private Double	price;
	private int reservedRoom;
	private int available;
	private String description;
	
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getReservedRoom() {
		return reservedRoom;
	}
	public void setReservedRoom(int reservedRoom) {
		this.reservedRoom = reservedRoom;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "HostelAvailability [threshold=" + threshold + ", typeCode="
				+ typeCode + ", price=" + price + ", reservedRoom="
				+ reservedRoom + ", available=" + available + ", description="
				+ description + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
