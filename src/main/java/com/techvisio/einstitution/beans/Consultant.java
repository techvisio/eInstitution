package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONSULTANT_MASTER")    
public class Consultant extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Consultant_Id")
	private Long consultantId;
	@Column(name = "Name")
	private String name;
	@Column(name="Consultancy_Name")
	private String consultancyName;
	@Column(name = "Primary_Contact_No")
	private String primaryContactNo;
	@Column(name = "Secondary_contact_No")
	private String secondaryContactNo;
	@Column(name = "Address")
	private String address;
	@Column(name = "Email_Id")
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
	
	public String getConsultancyName() {
		return consultancyName;
	}

	public void setConsultancyName(String consultancyName) {
		this.consultancyName = consultancyName;
	}

}
