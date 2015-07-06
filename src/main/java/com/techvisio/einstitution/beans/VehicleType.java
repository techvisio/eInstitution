package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_TYPE_MASTER")

public class VehicleType extends BasicEntity{

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Type_Id")
	private Long vehicleTypeId;
	@Column(name = "Type")
	private String type;
	
	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(Long typeId) {
		this.vehicleTypeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
