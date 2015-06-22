package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_MASTER")    
public class Course extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Course_Id")
	private Long courseId;
	
	@Column(name = "Course")
	private String course;
	
	@Column(name = "Course_Type")
	private String courseType;
	
	public Long getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Long id) {
		this.courseId = id;
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

}
