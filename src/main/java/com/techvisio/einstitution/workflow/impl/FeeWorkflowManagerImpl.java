package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
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
	public List<FeeAdmissionBean> getPendingfeeInfo(int limit){
		logger.info("{} : calling getPendingfeeInfo by passing limit:{}",this.getClass().getName(),limit);		
		List<FeeAdmissionBean> feeAdmissionBeans = feeManager.getPendingfeeInfo(limit);
		
		return feeAdmissionBeans;
		
	}
	
	public FeeDiscountHead getfeeDiscountHead(Long headId) {
		logger.info("{} : calling getfeeDiscountHead by passing headId:{}",this.getClass().getName(),headId);
		FeeDiscountHead feeDiscountHead = feeManager.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		logger.info("{} : calling addFeeDiscountHead for headId:{}",this.getClass().getName(),feeDiscountHead.getHeadId());
		feeManager.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		logger.info("{} : calling updateFeeDiscountHead for headId:{}",this.getClass().getName(),feeDiscountHead.getHeadId());
		feeManager.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {
		logger.info("{} : calling deleteFeeDiscountHead by passing headId:{}",this.getClass().getName(),headId);
		feeManager.deleteFeeDiscountHead(headId);
	}

	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria) {
		logger.info("{} : calling getApplicableFeeDetail for courseId:{} and branchId:{}",this.getClass().getName(),criteria.getCourseId(), criteria.getBranchId());
		// details = null;
		 List<ApplicableFeeDetail>	details =feeManager.getApplicableFeeDetail(criteria);
		return details;
	}

	public void addFeeDetail(ApplicableFeeDetail feeDetail) {
		logger.info("{} : calling addFeeDetail for course:{} and branch:{}",this.getClass().getName(),feeDetail.getCourse(),feeDetail.getBranch());
		feeManager.addFeeDetail(feeDetail);
	}

	public void updateFeeDetail(ApplicableFeeDetail feeDetail) {
		logger.info("{} : calling updateFeeDetail for course:{} and branch:{}",this.getClass().getName(),feeDetail.getCourse(),feeDetail.getBranch());
		feeManager.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId) {
		logger.info("{} : calling deleteFeeDetail by passing course:{} and branch:{} and feeHeadId:{}",this.getClass().getName(),course,branch,feeHeadId);		
		feeManager.deleteFeeDetail(course, branch, feeHeadId);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId) {
		logger.info("{} : calling getStudentFeeStaging by passing fileNo:{} and  feeHeadId:{}",this.getClass().getName(),fileNo,feeHeadId);
		List<StudentFeeStaging> feeStaging = feeManager.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}

	public void saveStudentFeeStaging(StudentFeeStaging studentFeeStaging){
		logger.info("{} : calling saveStudentFeeStaging for fileNo:{} ",this.getClass().getName(),studentFeeStaging.getFileNo());	
		feeManager.saveStudentFeeStaging(studentFeeStaging);
	}
	
	public void saveStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings){
		logger.info("{} : calling saveStudentFeeStaging by passing studentFeeStagings:{} ",this.getClass().getName(),studentFeeStagings);
		feeManager.saveStudentFeeStaging(studentFeeStagings);
		
	} 
	public void deleteStudentFeeStagingbyfileNo(StudentFeeStaging feeStaging){
		logger.info("{} : calling deleteStudentFeeStagingbyfileNo for file no:{} ",this.getClass().getName(), feeStaging.getFileNo());
		feeManager.deleteStudentFeeStagingbyfileNo(feeStaging);
	}

	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getDebitedFeeTransaction by passing file no:{} ",this.getClass().getName(), fileNo);	
		List<FeeTransaction> feeTransactions = feeManager.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		logger.info("{} : calling addFeeTransactionDebit for file no:{} ",this.getClass().getName(), feeTransaction.getFileNo());		
		feeManager.addFeeTransactionDebit(feeTransaction);
		
	}

	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getCreditedFeeTransaction by passing file no:{} ",this.getClass().getName(), fileNo);
		List<FeeTransaction> feeTransactions = feeManager.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {
		logger.info("{} : calling addFeeTransactionCredit for file no:{} ",this.getClass().getName(), feeTransaction.getFileNo());
		Long fileNo = feeTransaction.getFileNo();
		StudentBasicInfo basicInfo = admissionWorkFlow.getStudentBsInfo(fileNo);
		feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
		feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
		StudentDetail studentDetail = admissionWorkFlow.getStudentDetails(fileNo);
		studentDetail.setFeePaid(true);
		admissionWorkFlow.updateStudentDetails(studentDetail);
		feeManager.addFeeTransactionCredit(feeTransaction);
	}

	@Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(Long fileNo){
		logger.info("{} : calling getFeeTransactionDetail by passing file no:{} ",this.getClass().getName(), fileNo);	
		FeeTransactionAdmissionBean admissionBean = new FeeTransactionAdmissionBean();
		
		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
		
		if(basicInfo!=null){

			admissionBean = feeManager.getFeeTransactionDetail(fileNo);
			
	}
		admissionBean.setBasicInfo(basicInfo);
		
		return admissionBean;
	
}

	@Override
	public void generateStudentFeeStaging(Long fileNo) {
		logger.info("{} : calling generateDiscountinStagging by passing file no:{} ",this.getClass().getName(), fileNo);
       feeManager.generateDiscountinStagging(fileNo);
		
	}

	public Boolean isManagementApproved(Long fileNo){
		logger.info("{} : calling isManagementApproved by passing file no:{} ",this.getClass().getName(), fileNo);		
		return feeManager.isManagementApproved(fileNo);
	}

	@Override
	public void handleManagementChangesforDiscounts(StudentBasicInfo basicInfo,List<StudentFeeStaging> newStaggingDiscounts) {
		logger.info("{} : calling moveStaggingandBaseFeetoTransaction for student:{} by passing newStaggingDiscounts:{} ",this.getClass().getName(), basicInfo.getFirstName()+basicInfo.getLastName(),newStaggingDiscounts);
		feeManager.moveStaggingandBaseFeetoTransaction(basicInfo, newStaggingDiscounts);
	}

		}