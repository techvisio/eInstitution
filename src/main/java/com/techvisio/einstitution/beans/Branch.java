package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coursebranchmaster")    
public class Branch extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Course_Id")
	private Long courseId;
	@Column(name = "Branch_Id")
	private Long branchId;
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

		public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
