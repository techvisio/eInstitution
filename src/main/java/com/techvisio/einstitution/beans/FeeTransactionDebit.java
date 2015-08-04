package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FEE_TRANSACTION_DEBIT")
public class FeeTransactionDebit extends BasicFeeTransaction{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Transaction_Id")
	private Long transactionId;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
}
