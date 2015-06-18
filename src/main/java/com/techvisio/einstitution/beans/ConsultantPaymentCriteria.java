package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "consultantpaymentcriteria")    
public class ConsultantPaymentCriteria extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "File_No")
	private Long fileNo; 
	
	@Column(name = "Consultant_Id")
	private Long consultantId;
	
	@Column(name = "Fee_Received")
	private Double feeReceived;
	
	@Column(name = "Amount_To_Be_Paid")
	private Double amountToBePaid; 
	
	@Column(name = "Fee_Due_Date")
	private Date feeDueDate;
	
	@Column(name = "Approved")
	private boolean approved;
	
	@Column(name = "Paid")
	private boolean paid;
	
	@Column(name = "Triggered")
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
