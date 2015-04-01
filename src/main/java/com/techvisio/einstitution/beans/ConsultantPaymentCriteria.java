package com.techvisio.einstitution.beans;

import java.util.Date;

public class ConsultantPaymentCriteria {

	private Long fileNo; 
	private Long consultantId; 
	private Double feeReceived; 
	private Double amountToBePaid; 
	private Date feeDueDate; 
	private boolean approved; 
	private boolean paid; 
	private boolean triggered;

	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	public Double getFeeReceived() {
		return feeReceived;
	}
	public void setFeeReceived(Double feeReceived) {
		this.feeReceived = feeReceived;
	}
	public Double getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(Double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	public Date getFeeDueDate() {
		return feeDueDate;
	}
	public void setFeeDueDate(Date feeDueDate) {
		this.feeDueDate = feeDueDate;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isTriggered() {
		return triggered;
	}
	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
	@Override
	public String toString() {
		return "ConsultantPaymentCriteria [fileNo=" + fileNo
				+ ", consultantId=" + consultantId + ", feeReceived="
				+ feeReceived + ", amountToBePaid=" + amountToBePaid
				+ ", feeDueDate=" + feeDueDate + ", approved=" + approved
				+ ", paid=" + paid + ", triggered=" + triggered + "]";
	}

}
