package com.techvisio.einstitution.beans;

import java.util.Date;

public class FeeTransaction {
	private Long feeId;
	private Date date;
	private String user;
	private Double amount;
	private String amountTransactionType;
	private String fileNo;
	
	
	public Long getFeeId() {
		return feeId;
	}
	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAmountTransactionType() {
		return amountTransactionType;
	}
	public void setAmountTransactionType(String amountTransactionType) {
		this.amountTransactionType = amountTransactionType;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "FeeTransaction [feeId=" + feeId + ", date=" + date + ", user="
				+ user + ", amount=" + amount + ", amountTransactionType="
				+ amountTransactionType + ", fileNo=" + fileNo + "]";
	}
	
	

}

