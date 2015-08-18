package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
@Component
public interface FeeManager {
	
	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo);
	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit);
	public List<FeeTransactionCredit> getCreditedFeeTransaction(Long fileNo);
	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit);
	
	public FeeTransactionAdmission getFeeTransactionDetail(Long fileNo);
	public List<FeeAdmission> getPendingfeeInfo(int limit);
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria);

}
