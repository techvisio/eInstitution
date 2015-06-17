package com.techvisio.einstitution.beans;

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
	private Long id;	
	private Long branchPreferenceId;
	private Long fileNo;
	private Long branchId;
	private Long coureseId;

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

	public Long getCoureseId() {
		return coureseId;
	}

	public void setCoureseId(Long coureseId) {
		this.coureseId = coureseId;
	}

	@Override
	public String toString() {
		return "BranchPreference [branchPreferenceId=" + branchPreferenceId
				+ ", fileNo=" + fileNo + ", branchId=" + branchId
				+ ", coureseId=" + coureseId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
