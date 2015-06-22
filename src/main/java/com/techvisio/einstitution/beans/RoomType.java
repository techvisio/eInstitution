
package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_TYPE_MASTER")
public class RoomType {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Room_Type_Id")
	private Long roomTypeId;
	
	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	@Column(name = "Type_Code")
	private String	typeCode;
	@Column(name = "Description")
	private String	description;
	@Column(name = "Threshold")
	private int	threshold;
	@Column(name = "Price")
	private Double	price;
	@Column(name = "Room_Capacity")
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
