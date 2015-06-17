package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotacodemaster")
public class QuotaCode {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	private Long quotaId;
	private String code;
	private String description;
	
	public Long getQuotaId() {
		return quotaId;
	}
	
	public void setQuotaId(Long quotaId) {
		this.quotaId = quotaId;
	}
	
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "QuotaCode [id=" + quotaId + ", code=" + code + ", description="
				+ description + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
