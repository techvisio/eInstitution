package com.techvisio.einstitution.beans;

public class CounsellingDetail {

	private String fileNo;
	private Long counsellingId;
	private String rollNo;
	private Long rank;
	private Long categoryRank;
	private Float percentile;

	@Override
	public String toString() {
		return "CounsellingDetail [FileNo=" + fileNo + ", CounsellingId="
				+ counsellingId + ", Roll_No=" + rollNo + ", Rank=" + rank
				+ ", Category_Rank=" + categoryRank + ", Percentile="
				+ percentile + "]";
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public Long getCounsellingId() {
		return counsellingId;
	}

	public void setCounsellingId(Long counsellingId) {
		this.counsellingId = counsellingId;
	}

	public String getRoll_No() {
		return rollNo;
	}

	public void setRoll_No(String roll_No) {
		this.rollNo = roll_No;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public Long getCategory_Rank() {
		return categoryRank;
	}

	public void setCategory_Rank(Long category_Rank) {
		this.categoryRank = category_Rank;
	}

	public Float getPercentile() {
		return percentile;
	}

	public void setPercentile(Float percentile) {
		this.percentile = percentile;
	}

}
