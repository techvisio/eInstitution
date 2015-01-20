package com.techvisio.einstitution.beans;

public class Subject {

	private Long id;
	private String subjectName;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + "]";
	}
	
	
	
	
}
