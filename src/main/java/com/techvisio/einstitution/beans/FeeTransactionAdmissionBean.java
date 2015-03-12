package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class FeeTransactionAdmissionBean extends BasicDetailBeanEntity {

	private List<FeeTransaction> feeTransactionDebit;
	private List<FeeTransaction> feeTransactionCredit;
	
	public List<FeeTransaction> getFeeTransactionDebit() {
		return feeTransactionDebit;
	}

	public void setFeeTransactionDebit(List<FeeTransaction> feeTransactionDebit) {
		this.feeTransactionDebit = feeTransactionDebit;
	}

	public List<FeeTransaction> getFeeTransactionCredit() {
		return feeTransactionCredit;
	}

	public void setFeeTransactionCredit(List<FeeTransaction> feeTransactionCredit) {
		this.feeTransactionCredit = feeTransactionCredit;
	}

}
