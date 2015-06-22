package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public abstract class BasicFeeTransaction{

	@Id
	@Column(name="Transaction_Id")
	private Long transactionId;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead feeDiscountHead=new FeeDiscountHead();
	@Column(name="User")
	private String user;
	@Column(name="Created_Date")
	private Date createdDate;
	@Column(name="Remark")
	private String remark;
	@Column(name="Mode")
	private String mode;
	@Column(name="File_No")
	private Long fileNo;
	@Column(name="Amount")
	private Double amount;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Batch_Id")
	private Long batchId;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Session_Id")
	private Long sessionId;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public BasicFeeTransaction(){

	}

	public BasicFeeTransaction(Long batchId, Long sessionId, Long fileNo, Double amount, FeeDiscountHead discountHead){

		this.amount=amount;
		this.batchId=batchId;
		this.sessionId=sessionId;
		this.fileNo=fileNo;
		this.feeDiscountHead=discountHead;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}


	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public FeeDiscountHead getFeeDiscountHead() {
		return feeDiscountHead;
	}
	public void setFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		this.feeDiscountHead = feeDiscountHead;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "FeeTransaction [feeDiscountHead=" + feeDiscountHead + ", user="
				+ user + ", createdDate=" + createdDate + ", remark=" + remark
				+ ", mode=" + mode + ", fileNo=" + fileNo + ", amount="
				+ amount + ", batchId=" + batchId + ", sessionId=" + sessionId
				+ "]";
	}


}