package com.techvisio.einstitution.beans;

import java.sql.Date;

public class ConsultantDetail {

	private String fileNo;
	private String consultantId;
	private boolean consultancyAgreed; 
	private String paymentMode;
	private long amountToPay; 
	private Date dueDate;

	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	
	
	public String getConsultantId() {
		return consultantId;
	}

	
	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	
	
	public boolean getConsultancyAgreed() {
	   return consultancyAgreed;
	}


	public void setConsultancyAgreed(boolean consultancyAgreed) {
		this.consultancyAgreed = consultancyAgreed;
	}

	
	
	public String getPaymentMode() {
		return paymentMode;
	}

	
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	
	
	public Long getAmountToPay() {
		return amountToPay;
	}

	
	public void setAmountToPay(long amountToPay) {
		this.amountToPay = amountToPay;
	}

	
	
	public java.util.Date getDueDate() {
		return dueDate;
	}

	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
