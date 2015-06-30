package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CENTRE_MASTER")    
public class Centre extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Centre_Id")
	private Long centreId;
	@Column(name = "Centre_Name")
	private String centreName;
	
	
	public Long getCentreId() {
		return centreId;
	}
	
	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
	
	public String getCentreName() {
		return centreName;
	}
	
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

}
