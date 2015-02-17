package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeDetailManager;
import com.techvisio.einstitution.manager.impl.FeeDetailManagerImpl;
import com.techvisio.einstitution.workflow.FeeDetailWorkflowManager;

public class FeeDetailWorkflowManagerImpl implements FeeDetailWorkflowManager{
	
	
	FeeDetailManager manager=FeeDetailManagerImpl.getInstance();
	
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

	public FeeDetail getFeeDetail(Long feeDetailFeeHeadId,Long feeDetailCourse,Long feeDetailBranch) {
		FeeDetail detail = manager.getFeeDetail(feeDetailFeeHeadId, feeDetailCourse, feeDetailBranch);
		return detail;
	}

	public void addFeeDetail(FeeDetail feeDetail) {
		manager.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(FeeDetail feeDetail) {
		manager.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long feeDetailFeeHeadId,Long feeDetailCourse,Long feeDetailBranch) {
		manager.deleteFeeDetail(feeDetailFeeHeadId, feeDetailCourse, feeDetailBranch);
	}

	public StudentFeeStaging getStudentFeeStaging(String studentFeeStaging) {
		StudentFeeStaging feeStaging = manager.getStudentFeeStaging(studentFeeStaging);
		return feeStaging;
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		manager.addStudentFeeStaging(studentFeeStaging);
		
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		manager.updateStudentFeeStaging(studentFeeStaging);
	}

	public void deleteStudentFeeStaging(String studentFeeStaging) {
		manager.deleteStudentFeeStaging(studentFeeStaging);
	}

	public FeeTransaction getFeeTransaction(String feeTransaction) {
		FeeTransaction transaction = manager.getFeeTransaction(feeTransaction);
		return transaction;
	}

	public void addFeeTransaction(FeeTransaction feeTransaction) {
		manager.addFeeTransaction(feeTransaction);
	}

	
	
}
