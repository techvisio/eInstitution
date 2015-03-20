package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDetail;
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

	public List<FeeDetail> getFeeDetail(Long course,Long branch) {
		// details = null;
		 List<FeeDetail>	details =feeManager.getFeeDetail(course, branch);
		return details;
	}

	public void addFeeDetail(FeeDetail feeDetail) {
		feeManager.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(FeeDetail feeDetail) {
		feeManager.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course,Long branch, Integer semester) {
		feeManager.deleteFeeDetail(course, branch, semester);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(String fileNo, Long feeHeadId) {
		List<StudentFeeStaging> feeStaging = feeManager.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeManager.addStudentFeeStaging(studentFeeStaging);
		
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeManager.updateStudentFeeStaging(studentFeeStaging);
	}

	public void updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings){
		feeManager.updateStudentFeeStaging(studentFeeStagings);
		
	} 
	public void deleteStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeManager.deleteStudentFeeStaging(studentFeeStaging);
	}

	public List<FeeTransaction> getDebitedFeeTransaction(String fileNo) {
	
		List<FeeTransaction> feeTransactions = feeManager.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		
		feeManager.addFeeTransactionDebit(feeTransaction);
		
	}

	public List<FeeTransaction> getCreditedFeeTransaction(String fileNo) {

		List<FeeTransaction> feeTransactions = feeManager.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {

		feeManager.addFeeTransactionCredit(feeTransaction);
	}
    @Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(String fileNo){
		
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