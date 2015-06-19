package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stateMaster")
public class State {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "State_Id")
	private Long stateId;
	@Column(name = "State_Name")
	private String stateName;
	
	
	public Long getStateId() {
		return stateId;
	}
	
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [id=" + stateId + ", stateName=" + stateName + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
