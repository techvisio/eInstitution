package com.techvisio.einstitution.beans;

public class BranchPreference {

	private Long branchPreferenceId;
	private String fileNo;
	private Long branchId;
	private Long coureseId;

	public Long getBranchPreferenceId() {
		return branchPreferenceId;
	}

	public void setBranchPreferenceId(Long branchPreferenceId) {
		this.branchPreferenceId = branchPreferenceId;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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
