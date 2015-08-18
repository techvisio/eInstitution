package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class FeeTransactionAdmission extends BasicDetailBeanEntity {

	private List<FeeTransactionDebit> feeTransactionDebit;
	private List<FeeTransactionCredit> feeTransactionCredit;
	private Double amountDiffrence;
	private Remark remark;


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

	public List<FeeTransactionDebit> getFeeTransactionDebit() {
		return feeTransactionDebit;
	}

	public void setFeeTransactionDebit(List<FeeTransactionDebit> feeTransactionDebit) {
		this.feeTransactionDebit = feeTransactionDebit;
	}

	public List<FeeTransactionCredit> getFeeTransactionCredit() {
		return feeTransactionCredit;
	}

	public void setFeeTransactionCredit(
			List<FeeTransactionCredit> feeTransactionCredit) {
		this.feeTransactionCredit = feeTransactionCredit;
	}

	
}
