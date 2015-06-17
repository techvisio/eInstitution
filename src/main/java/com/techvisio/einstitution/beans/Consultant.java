package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consultantmaster")    
public class Consultant extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long consultantId;
	private String name;
	private String primaryContactNo;
	private String secondaryContactNo;
	private String address;
	private String emailId;
	
	public Long getConsultantId() {
		return consultantId;
	}
	
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getPrimaryContactNo() {
		return primaryContactNo;
	}
	
	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}
	
	
	public String getSecondaryContactNo() {
		return secondaryContactNo;
	}
	
	public void setSecondaryContactNo(String secondaryContactNo) {
		this.secondaryContactNo = secondaryContactNo;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Consultant [consultantId=" + consultantId + ", name=" + name
				+ ", primaryContactNo=" + primaryContactNo
				+ ", secondaryContactNo=" + secondaryContactNo + ", address="
				+ address + ", emailId=" + emailId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}      
	   
	 
}
