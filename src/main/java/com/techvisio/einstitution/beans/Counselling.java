package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author f
 *
 */
@Entity
@Table(name = "counsellingdetail")
public class Counselling extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "File_No")
	private Long fileNo;
	
	@Column(name = "Counselling_Id")
	private Long counsellingId;
	
	@Column(name = "Roll_No")
	private String rollNo;
	
	@Column(name = "Rank")
	private Long rank;
	
	@Column(name = "Category_Rank")
	private Long categoryRank;
	
	@Column(name = "Percentile")
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
