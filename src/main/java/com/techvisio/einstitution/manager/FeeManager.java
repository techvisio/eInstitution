package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeManager {
	
	public void addFeeDetail(ApplicableFeeDetail feeDetail);
	public void updateFeeDetail(ApplicableFeeDetail feeDetail);
	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId );
	
	
	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId);
	public void saveStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings);
	public void deleteStudentFeeStagingbyfileNo(StudentFeeStaging feeStaging);

	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo);
	public void addFeeTransactionDebit(FeeTransaction feeTransaction);
	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo);
	public void addFeeTransactionCredit(FeeTransaction feeTransaction);

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);
	
	public void generateStudentFeeStaging(Long fileNo);
	FeeTransactionAdmissionBean getFeeTransactionDetail(Long fileNo);
	List<FeeAdmissionBean> getPendingfeeInfo(int limit);
	List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria);

}
