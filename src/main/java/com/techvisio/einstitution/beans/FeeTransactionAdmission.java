package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class FeeTransactionAdmissionBean extends BasicDetailBeanEntity {

	private List<FeeTransaction> feeTransactionDebit;
	private List<FeeTransaction> feeTransactionCredit;
	private Double amountDiffrence;
	private Remark remark;

	
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

	public Double getAmountDiffrence() {
		return amountDiffrence;
	}

	public void setAmountDiffrence(Double amountDiffrence) {
		this.amountDiffrence = amountDiffrence;
	}

	public Remark getRemark() {
		return remark;
	}

	public void setRemark(Remark remark) {
		this.remark = remark;
	}

}
