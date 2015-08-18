package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
@Component
public class FeeWorkflowManagerImpl implements FeeWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(FeeWorkflowManagerImpl.class);	
	@Autowired
	FeeManager feeManager;

	@Autowired
	AdmissionWorkflowManager admissionWorkFlow;

	@Override
	public List<FeeAdmission> getPendingfeeInfo(int limit){
		logger.info("{} : calling getPendingfeeInfo by passing limit:{}",this.getClass().getName(),limit);		
		List<FeeAdmission> feeAdmissionBeans = feeManager.getPendingfeeInfo(limit);
		return feeAdmissionBeans;
	}

	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria) {
		logger.info("{} : calling getApplicableFeeDetail for courseId:{} and branchId:{}",this.getClass().getName(),criteria.getCourseId(), criteria.getBranchId());
		// details = null;
		List<ApplicableFeeDetail>	details =feeManager.getApplicableFeeDetail(criteria);
		return details;
	}


	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getDebitedFeeTransaction by passing file no:{} ",this.getClass().getName(), fileNo);	
		List<FeeTransactionDebit> feeTransactionDebits = feeManager.getDebitedFeeTransaction(fileNo);
		return feeTransactionDebits;
	}

	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit) {
		logger.info("{} : calling addFeeTransactionDebit for file no:{} ",this.getClass().getName(), feeTransactionDebit.getFileNo());		
		feeManager.addFeeTransactionDebit(feeTransactionDebit);

	}

	public List<FeeTransactionCredit> getCreditedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getCreditedFeeTransaction by passing file no:{} ",this.getClass().getName(), fileNo);
		List<FeeTransactionCredit> feeTransactionCredits = feeManager.getCreditedFeeTransaction(fileNo);
		return feeTransactionCredits;
	}

	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit) {
		logger.info("{} : calling addFeeTransactionCredit for file no:{} ",this.getClass().getName(), feeTransactionCredit.getFileNo());
		Long fileNo = feeTransactionCredit.getFileNo();
		StudentBasicInfo basicInfo = admissionWorkFlow.getStudentBsInfo(fileNo);
		feeTransactionCredit.setBatch(basicInfo.getBatch());
		feeTransactionCredit.setSession(basicInfo.getSession());
		feeManager.addFeeTransactionCredit(feeTransactionCredit);
	}

	@Override
	public FeeTransactionAdmission getFeeTransactionDetail(Long fileNo){
		logger.info("{} : calling getFeeTransactionDetail by passing file no:{} ",this.getClass().getName(), fileNo);	
		FeeTransactionAdmission admissionBean = new FeeTransactionAdmission();
		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
		if(basicInfo!=null){
			admissionBean = feeManager.getFeeTransactionDetail(fileNo);
		}
		admissionBean.setBasicInfo(basicInfo);
		return admissionBean;
	}
}