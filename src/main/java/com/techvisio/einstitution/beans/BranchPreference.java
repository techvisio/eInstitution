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
@Table(name = "BRANCH_PREFERENCE")    
public class BranchPreference extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Branch_Preference_Id")
	private Long branchPreferenceId;
	@Column(name = "File_No")
	private Long fileNo;
    @ManyToOne
    @JoinColumn(name="Branch_Id")
	private Branch branch;
    @ManyToOne
    @JoinColumn(name="Course_Id")
	private Course course;

	public Long getFileNo() {
		return fileNo;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

}
