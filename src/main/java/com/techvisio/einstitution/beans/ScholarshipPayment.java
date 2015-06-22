package com.techvisio.einstitution.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCHOLARSHIP_PAYMENT_DETAIL")
public class ScholarshipPayment {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Schlarshp_Paymnt_Id")
	private Long schlarshpPaymntId;
	@Column(name="File_No")
	private Long fileNo;
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Long getSchlarshpPaymntId() {
		return schlarshpPaymntId;
	}
	@Column(name = "Amount_Received")
	private Double amountReceived;
	@Column(name = "Received_Date")
	private Date receivingDate;
	
	
	public Double getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}
	
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date date) {
		this.receivingDate = date;
	}
	public Long getschlarshpPaymntId() {
		return schlarshpPaymntId;
	}
	public void setSchlarshpPaymntId(Long ischlarshpPaymntIdd) {
		this.schlarshpPaymntId = ischlarshpPaymntIdd;
	}

}
