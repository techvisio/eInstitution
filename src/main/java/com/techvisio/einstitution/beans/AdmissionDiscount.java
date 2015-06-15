package com.techvisio.einstitution.beans;

public class AdmissionDiscountDtl {

	private Long fileNo;
	private Long feeHeadId;
	private double amount;
	private float percent;
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	private String discountType;
	
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

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	@Override
	public String toString() {
		return "AdmissionDiscountDtl [fileNo=" + fileNo + ", feeHeadId="
				+ feeHeadId + ", amount=" + amount + ", percent=" + percent
				+ ", discountType=" + discountType + "]";
	}
	
	
}
