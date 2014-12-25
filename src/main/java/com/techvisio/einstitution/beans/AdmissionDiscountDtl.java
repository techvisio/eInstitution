package com.techvisio.einstitution.beans;

public class AdmissionDiscountDtl {

	private String fileNo;
	private Long feeHeadId;
	private double amount;
	private float percent;
	
	
	public String getFileNo() {
		return fileNo;
	}
	
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
	
	public Long getFeeHeadId() {
		return feeHeadId;
	}
	
	public void setFeeHeadId(Long feeHeadId) {
		this.feeHeadId = feeHeadId;
	}
	
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public float getPercent() {
		return percent;
	}
	
	public void setPercent(float percent) {
		this.percent = percent;
	}
}
