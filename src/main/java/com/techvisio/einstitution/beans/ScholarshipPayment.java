package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scholarshippaymentdetail")
public class ScholarshipPayment {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name = "Amount_Received")
	private Double amountReceived;
	@Column(name = "Received_Date")
	private String receivingDate;
	
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Double getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}
	public String getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(String receivingDate) {
		this.receivingDate = receivingDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
