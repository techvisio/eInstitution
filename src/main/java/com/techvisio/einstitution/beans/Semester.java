package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SEMESTER_MASTER")

public class Semester {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Semester_Id")
	private Long semesterId;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Course_Id")
	private Course course;
	@Column(name = "Semester")
	private String semester;
	

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Long getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
	}
	
}
