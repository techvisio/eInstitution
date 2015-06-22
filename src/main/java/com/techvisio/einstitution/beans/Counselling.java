package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUNSELLING_DETAIL")
public class Counselling extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Stdnt_Counslling_Id")
	private Long stdntCounsllingId;
	
	@Column(name = "File_No")
	private Long fileNo;

	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Counselling_Id")
	private CounsellingBody counsellingBody;
	
	@Column(name = "Roll_No")
	private String rollNo;
	
	@Column(name = "Rank")
	private Long rank;
	
	public CounsellingBody getCounsellingBody() {
		return counsellingBody;
	}


	public void setCounsellingBody(CounsellingBody counsellingBody) {
		this.counsellingBody = counsellingBody;
	}


	public void setStdntCounsllingId(Long stdntCounsllingId) {
		this.stdntCounsllingId = stdntCounsllingId;
	}


	@Column(name = "Category_Rank")
	private Long categoryRank;
	
	@Column(name = "Percentile")
	private Float percentile;

	public Long getFileNo() {
		return fileNo;
	}


	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
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


	public Long getStdntCounsllingId() {
		return stdntCounsllingId;
	}


	public void setStdntCounslling(Long stdntCounsllingId) {
		this.stdntCounsllingId = stdntCounsllingId;
	}

}
