package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FEE_TRANSACTION_CREDIT")
public class FeeTransactionCredit extends BasicFeeTransaction {

	@Id
	@Column(name="Transaction_Id")
	private Long transactionId;
	
}
