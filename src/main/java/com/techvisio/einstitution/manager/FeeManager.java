package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeManager {
	
	public List<FeeDetail> getFeeDetail(Long course,Long branch);
	public void addFeeDetail(FeeDetail feeDetail);
	public void updateFeeDetail(FeeDetail feeDetail);
	public void deleteFeeDetail(Long course,Long branch, Integer semester );
	
	
	public List<StudentFeeStaging> getStudentFeeStaging(String fileNo);
	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void deleteStudentFeeStaging(StudentFeeStaging studentFeeStaging);

	public FeeTransaction getDebitedFeeTransaction(String fileNo);
	public void addFeeTransactionDebit(FeeTransaction feeTransaction);
	public FeeTransaction getCreditedFeeTransaction(String fileNo);
	public void addFeeTransactionCredit(FeeTransaction feeTransaction);

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);
	
	public void generateStudentFeeStaging(String fileNo);

}
