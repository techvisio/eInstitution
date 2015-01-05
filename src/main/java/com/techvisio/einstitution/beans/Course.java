package com.techvisio.einstitution.beans;

public class Course {

	private Long id;
	private String course;
	private String courseType;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	
	public String getCourseType() {
		return courseType;
	}
	
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", course=" + course + ", courseType="
				+ courseType + "]";
	}
	
	
	
	
}
