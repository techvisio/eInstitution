package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wingMaster")
public class Wing {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
