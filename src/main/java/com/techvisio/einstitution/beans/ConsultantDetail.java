package com.techvisio.einstitution.beans;

import java.sql.Date;

public class ConsultantDetail {

	private String fileNo;
	private String consultantId;
	private boolean consultancyAgreed; 
	private String paymentMode;
	private Double amountToPay; 
	private Date dueDate;

	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	
	public String getPaymentMode() {
		return paymentMode;
	}

	
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	
	
	public Double getAmountToPay() {
		return amountToPay;
	}

	
	public void setAmountToPay(Double amountToPay) {
		this.amountToPay = amountToPay;
	}

	
	
	public java.util.Date getDueDate() {
		return dueDate;
	}

	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isConsultancyAgreed() {
		return consultancyAgreed;
	}

	public void setConsultancyAgreed(boolean consultancyAgreed) {
		this.consultancyAgreed = consultancyAgreed;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}
	
}
