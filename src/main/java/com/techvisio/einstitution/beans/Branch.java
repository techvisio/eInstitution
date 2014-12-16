package com.techvisio.einstitution.beans;

public class Branch {

	private Course course;
	private int id;
	private String branchName;
	
	
	public Course getCourseId() {
		return course;
	}
	
	public void setCourseId(Course courseId) {
		this.course = courseId;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
}
