package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WING_MASTER")
public class Wing extends BasicEntity{

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Wing_Id")
	private Long wingId;
	@Column(name = "Wing")
	private String wing;
	
	public Long getWingId() {
		return wingId;
	}
	
	public void setWingId(Long wingId) {
		this.wingId = wingId;
	}
	
	public String getWing() {
		return wing;
	}
	
	public void setWing(String wing) {
		this.wing = wing;
	}
	
}
