package com.techvisio.einstitution.beans;

public class CounsellingDetail {

	private String FileNo;
	private Long CounsellingId;
	private String Roll_No;
	private Long Rank;
	private Long Category_Rank;
	private Float Percentile;

	@Override
	public String toString() {
		return "CounsellingDetail [FileNo=" + FileNo + ", CounsellingId="
				+ CounsellingId + ", Roll_No=" + Roll_No + ", Rank=" + Rank
				+ ", Category_Rank=" + Category_Rank + ", Percentile="
				+ Percentile + "]";
	}

	public String getFileNo() {
		return FileNo;
	}

	public void setFileNo(String fileNo) {
		FileNo = fileNo;
	}

	public Long getCounsellingId() {
		return CounsellingId;
	}

	public void setCounsellingId(Long counsellingId) {
		CounsellingId = counsellingId;
	}

	public String getRoll_No() {
		return Roll_No;
	}

	public void setRoll_No(String roll_No) {
		Roll_No = roll_No;
	}

	public Long getRank() {
		return Rank;
	}

	public void setRank(Long rank) {
		Rank = rank;
	}

	public Long getCategory_Rank() {
		return Category_Rank;
	}

	public void setCategory_Rank(Long category_Rank) {
		Category_Rank = category_Rank;
	}

	public Float getPercentile() {
		return Percentile;
	}

	public void setPercentile(Float percentile) {
		Percentile = percentile;
	}

}
