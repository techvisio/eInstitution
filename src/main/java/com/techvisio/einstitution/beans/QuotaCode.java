package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUOTACODE_MASTER")
public class QuotaCode extends BasicEntity{

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="Quota_Id")
	private Long quotaId;
	@Column(name="Quota_Code")
	private String quotaCode;
	@Column(name="Description")
	private String description;

	public QuotaCode (){}
	
	public QuotaCode(Long id){
		this.quotaId = id;
	}
	
	public Long getQuotaId() {
		return quotaId;
	}
	
	public void setQuotaId(Long quotaId) {
		this.quotaId = quotaId;
	}
	
	
	public String getQuotaCode() {
		return quotaCode;
	}
	
	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "QuotaCode [id=" + quotaId + ", code=" + quotaCode + ", description="
				+ description + "]";
	}
}
