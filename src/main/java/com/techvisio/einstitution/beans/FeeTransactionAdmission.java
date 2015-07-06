package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class FeeTransactionAdmission extends BasicDetailBeanEntity {

	private List<BasicFeeTransaction> feeTransactionDebit;
	private List<BasicFeeTransaction> feeTransactionCredit;
	private Double amountDiffrence;
	private Remark remark;

	
	public List<BasicFeeTransaction> getFeeTransactionDebit() {
		return feeTransactionDebit;
	}

	public void setFeeTransactionDebit(List<BasicFeeTransaction> feeTransactionDebit) {
		this.feeTransactionDebit = feeTransactionDebit;
	}

	public List<BasicFeeTransaction> getFeeTransactionCredit() {
		return feeTransactionCredit;
	}

	public void setFeeTransactionCredit(List<BasicFeeTransaction> feeTransactionCredit) {
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
