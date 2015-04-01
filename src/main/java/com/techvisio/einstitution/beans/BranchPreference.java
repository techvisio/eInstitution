package com.techvisio.einstitution.beans;

public class BranchPreference {

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

}
