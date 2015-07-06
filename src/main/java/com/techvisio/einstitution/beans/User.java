package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

public class User {

	private Long userId;
	private String department;
	private String name;
	private List<Privilege> privileges;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
}
