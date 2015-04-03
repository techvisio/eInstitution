package com.techvisio.einstitution.beans;

public class Section {
	private Long sectionId;
	private String section;
	private Long courseId;
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


}
