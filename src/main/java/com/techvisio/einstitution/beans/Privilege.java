package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRIVILEGE")    
public class Privilege {

	//Privilege 
	private String privilege;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PRIVILEGE_ID")
	private Long privilegeId;
	private String description;
	private String type;
	@Column(name="CREATED_ON")
	private Date createdOn;
	@Column(name="CREATED_BY")
	private String createdBy;

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long priviledgeId) {
		this.privilegeId = priviledgeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
