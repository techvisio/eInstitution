
package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sectionmaster")

public class Section {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Section_Id")
	private Long sectionId;
	@Column(name = "Section")
	private String section;
	@Column(name = "Course_Id")
	private Long courseId;
	@Column(name = "Branch_Id")
	private Long branchId;
	
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	
	public String getSection(){
		return section;
		
	}
	
	public void setSection(String section){
		this.section = section;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", section=" + section
				+ ", courseId=" + courseId + ", branchId=" + branchId + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


}
