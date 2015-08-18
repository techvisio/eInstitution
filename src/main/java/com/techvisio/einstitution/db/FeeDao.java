package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;

@Component
public interface FeeDao {

	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit);

	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit);

	public List<ApplicableFeeDetail> getApplicableFeeDetails(
			ApplicableFeeCriteria criteria);

	public Double getPreviousSemBalance(Long fileNo);

	public List<FeeAdmission> getPendingfeeInfo(int limit);

	public List<FeeTransactionCredit> getCreditedFeeTransaction(Long fileNo);

	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo);
}
