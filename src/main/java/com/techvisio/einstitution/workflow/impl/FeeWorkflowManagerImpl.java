package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;

public class FeeWorkflowManagerImpl implements FeeWorkflowManager{
	
	
	FeeManager manager=FeeManagerImpl.getInstance();
	
	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		FeeDiscountHead feeDiscountHead = manager.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		manager.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		manager.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {

		manager.deleteFeeDiscountHead(headId);
	}

	public List<FeeDetail> getFeeDetail(Long course,Long branch) {
		// details = null;
		 List<FeeDetail>	details =manager.getFeeDetail(course, branch);
		return details;
	}

	public void addFeeDetail(FeeDetail feeDetail) {
		manager.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(FeeDetail feeDetail) {
		manager.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course,Long branch, Integer semester) {
		manager.deleteFeeDetail(course, branch, semester);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(String fileNo) {
		List<StudentFeeStaging> feeStaging = manager.getStudentFeeStaging(fileNo);
		return feeStaging;
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		manager.addStudentFeeStaging(studentFeeStaging);
		
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		manager.updateStudentFeeStaging(studentFeeStaging);
	}

	public void deleteStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		manager.deleteStudentFeeStaging(studentFeeStaging);
	}

	public FeeTransaction getDebitedFeeTransaction(String fileNo) {
	
		FeeTransaction transaction = manager.getDebitedFeeTransaction(fileNo);
		return transaction;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		
		manager.addFeeTransactionDebit(feeTransaction);
		
	}

	public FeeTransaction getCreditedFeeTransaction(String fileNo) {

		FeeTransaction transaction = manager.getCreditedFeeTransaction(fileNo);
		return transaction;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {

		manager.addFeeTransactionCredit(feeTransaction);
	}

	
	
}
