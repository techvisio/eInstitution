package com.techvisio.einstitution.beans;

public class State {

	private Long id;
	private String stateName;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", stateName=" + stateName + "]";
	}
	
}
