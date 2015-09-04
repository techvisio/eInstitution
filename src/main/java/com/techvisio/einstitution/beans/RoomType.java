
package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM_TYPE_MASTER")
public class RoomType extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Room_Type_Id")
	private Long roomTypeId;
	@Column(name = "Type_Code")
	private String	typeCode;
	@Column(name = "Description")
	private String	description;
	@Column(name = "Price")
	private Double	price;

	public Long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
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
		public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
