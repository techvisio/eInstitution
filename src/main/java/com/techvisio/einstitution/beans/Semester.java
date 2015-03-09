package com.techvisio.einstitution.beans;

public class Semester {

	private Long courseId;
	private String semester;
	private Long id;

	
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Semester [courseId=" + courseId + ", semester=" + semester
				+ ", id=" + id + "]";
	}
	
	

}
