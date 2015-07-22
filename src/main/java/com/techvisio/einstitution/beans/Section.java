
package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SECTION_MASTER")

public class Section extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Section_Id")
	private Long sectionId;
	@Column(name = "Section")
	private String section;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Course_Id")
	private Course course;
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Branch_Id")
	private Branch branch;
	
	public Section(){}
	public Section(Long id){
		this.sectionId=id;
	}
	
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
