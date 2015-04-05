package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;

public class FeeWorkflowManagerImpl implements FeeWorkflowManager{
	
	
	FeeManager feeManager=FeeManagerImpl.getInstance();

	@Override
	public List<FeeAdmissionBean> getPendingfeeInfo(int limit){
		
		List<FeeAdmissionBean> feeAdmissionBeans = feeManager.getPendingfeeInfo(limit);
		
		return feeAdmissionBeans;
		
	}
	
	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		FeeDiscountHead feeDiscountHead = feeManager.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeManager.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeManager.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {

		feeManager.deleteFeeDiscountHead(headId);
	}

	@Override
	public List<ApplicableFeeDetail> getFeeDetail(ApplicableFeeCriteria criteria) {
		// details = null;
		 List<ApplicableFeeDetail>	details =feeManager.getFeeDetail(criteria);
		return details;
	}

	public void addFeeDetail(ApplicableFeeDetail feeDetail) {
		feeManager.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(ApplicableFeeDetail feeDetail) {
		feeManager.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId) {
		feeManager.deleteFeeDetail(course, branch, feeHeadId);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId) {
		List<StudentFeeStaging> feeStaging = feeManager.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}

	public void saveFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeManager.saveFeeStaging(studentFeeStaging);
		
	}

	

	public void updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings){
		feeManager.updateStudentFeeStaging(studentFeeStagings);
		
	} 
	public void deleteStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeManager.deleteStudentFeeStaging(studentFeeStaging);
	}

	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo) {
	
		List<FeeTransaction> feeTransactions = feeManager.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		
		feeManager.addFeeTransactionDebit(feeTransaction);
		
	}

	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {

		List<FeeTransaction> feeTransactions = feeManager.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {

		feeManager.addFeeTransactionCredit(feeTransaction);
	}
    @Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(Long fileNo){
		
		AdmissionWorkflowManager admissionWorkFlow = new AdmissionWorkflowManagerImpl();
		FeeTransactionAdmissionBean admissionBean = new FeeTransactionAdmissionBean();
		
		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
		
		if(basicInfo!=null){

			admissionBean = feeManager.getFeeTransactionDetail(fileNo);
			
	}
		admissionBean.setBasicInfo(basicInfo);
		
		return admissionBean;
	
}

	}