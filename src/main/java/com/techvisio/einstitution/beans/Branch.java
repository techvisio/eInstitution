package com.techvisio.einstitution.beans;

public class Branch {

	private Course course;
	private Long id;
	private String branchName;
	
	
	public Course getCourseId() {
		return course;
	}
	
	public void setCourseId(Course courseId) {
		this.course = courseId;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
}
