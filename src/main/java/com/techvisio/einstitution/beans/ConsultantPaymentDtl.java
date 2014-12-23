package com.techvisio.einstitution.beans;

import java.util.Date;

public class ConsultantPaymentDtl {
	
	private String amount;
    private Date payDate; 
    private String fileNo;
	
    public String getAmount() {
		return amount;
	}
	
    public void setAmount(String amount) {
		this.amount = amount;
	}
	
    
    public Date getPayDate() {
		return payDate;
	}
	
    public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
    
    public String getFileNo() {
		return fileNo;
	}
	
    public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

}
