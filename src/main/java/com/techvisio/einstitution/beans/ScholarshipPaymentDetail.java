package com.techvisio.einstitution.beans;

import java.util.Date;

public class ScholarshipPaymentDetail {

	private Long fileNo;
	private Double amountReceived;
	private Date receivingDate;
	
	
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
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	
}
