package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branchpreference")    
public class BranchPreference extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;	
	@Column(name = "Branch_Preference_Id")
	private Long branchPreferenceId;
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name = "Branch_Id")
	private Long branchId;
	@Column(name = "Course_Id")
	private Long courseId;

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public Long getBranchPreferenceId() {
		return branchPreferenceId;
	}

	public void setBranchPreferenceId(Long branchPreferenceId) {
		this.branchPreferenceId = branchPreferenceId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "BranchPreference [branchPreferenceId=" + branchPreferenceId
				+ ", fileNo=" + fileNo + ", branchId=" + branchId
				+ ", coureseId=" + courseId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
