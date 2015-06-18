package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "floormaster")    
public class Floor extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Floor_id")
	private Long floorId;
	
	@Column(name = "Floor")
	private String floor;
	
	public Long getFloorId() {
		return floorId;
	}
	
	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
