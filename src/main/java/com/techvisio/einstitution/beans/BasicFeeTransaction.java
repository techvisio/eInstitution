package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BasicFeeTransaction extends BasicEntity{

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="Transaction_Id")
	private Long transactionId;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead feeDiscountHead=new FeeDiscountHead();
	@Column(name="User")
	private String user;
	@Column(name="Remark")
	private String remark;
	@Column(name="Mode")
	private String mode;
	@Column(name="File_No")
	private Long fileNo;
	@Column(name="Amount")
	private Double amount;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Batch_Id")
	private Batch batch;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Session_Id")
	private Session session;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public BasicFeeTransaction(){

	}

	public BasicFeeTransaction(Batch batch, Session session, Long fileNo, Double amount, FeeDiscountHead discountHead){

		this.amount=amount;
		this.batch=batch;
		this.session=session;
		this.fileNo=fileNo;
		this.feeDiscountHead=discountHead;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}