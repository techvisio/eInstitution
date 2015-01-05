package com.techvisio.einstitution.beans;

import java.util.Date;

public class ConsultantPaymentDtl {
	
	private Double amount;
    private Date payDate; 
    private String fileNo;
	
    public Double getAmount() {
		return amount;
	}
	
    public void setAmount(Double amount) {
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

	@Override
	public String toString() {
		return "ConsultantPaymentDtl [amount=" + amount + ", payDate="
				+ payDate + ", fileNo=" + fileNo + "]";
	}

}
