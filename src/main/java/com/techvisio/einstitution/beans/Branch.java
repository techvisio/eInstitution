package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_BRANCH_MASTER")    
public class Branch extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Branch_Id")
	private Long branchId;
	@ManyToOne
	@JoinColumn(name = "Course_Id")
	private Course course;
	@Column(name = "Branch_Name")
	private String branchName;
	
	
	public Long getBranchId() {
		return branchId;
	}
	
	public void setBranchId(Long id) {
		this.branchId = id;
	}
	
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


}
