package com.techvisio.einstitution.beans;

/**
 * @author f
 *
 */
public class CounsellingDetail {

	private Long fileNo;
	private Long counsellingId;
	private String rollNo;
	private Long rank;
	private Long categoryRank;
	private Float percentile;

	

	@Override
	public String toString() {
		return "CounsellingDetail [fileNo=" + fileNo + ", counsellingId="
				+ counsellingId + ", rollNo=" + rollNo + ", rank=" + rank
				+ ", categoryRank=" + categoryRank + ", percentile="
				+ percentile + "]";
	}


	public Long getFileNo() {
		return fileNo;
	}


	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}


	public Long getCounsellingId() {
		return counsellingId;
	}

	public void setCounsellingId(Long counsellingId) {
		this.counsellingId = counsellingId;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public Long getCategoryRank() {
		return categoryRank;
	}

	public void setCategoryRank(Long categoryRank) {
		this.categoryRank = categoryRank;
	}

	public Float getPercentile() {
		return percentile;
	}

	public void setPercentile(Float percentile) {
		this.percentile = percentile;
	}

}
