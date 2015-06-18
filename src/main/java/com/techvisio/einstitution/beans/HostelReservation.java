package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hostelreservation")    
public class HostelReservation extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "File_No")
private Long	fileNo ;
	
	@Column(name = "Fee_Paid")
private	boolean feePaid ;
	
	@Column(name = "Type_Code")
private String	typeCode;
	
	@Column(name = "Allocation_Status")
private String allocationStatus;
	
	@Column(name = "Is_Active")
private boolean isActive;
	
	@Column(name = "Price")
private Double price;
	
	@Column(name = "Description")
private String description;
	

public Long getFileNo() {
	return fileNo;
}
public void setFileNo(Long fileNo) {
	this.fileNo = fileNo;
}
public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}
public boolean isFeePaid() {
	return feePaid;
}
public void setFeePaid(boolean feePaid) {
	this.feePaid = feePaid;
}
public String getAllocationStatus() {
	return allocationStatus;
}
public void setAllocationStatus(String allocationStatus) {
	this.allocationStatus = allocationStatus;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}


public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "HostelReservation [fileNo=" + fileNo + ", feePaid=" + feePaid
			+ ", typeCode=" + typeCode + ", allocationStatus="
			+ allocationStatus + ", isActive=" + isActive + "]";
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

}
