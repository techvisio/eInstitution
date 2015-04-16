package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ManagementWorkflowManagerImpl;

public class FeeManagerImpl implements FeeManager{

	FeeDao feeDetailDao = ContextProvider.getContext().getBean(
			FeeDao.class);
	AdmissionManager admissionManager=AdmissionManagerImpl.getInstance();
	
	private static FeeManagerImpl instance=null;
	public static synchronized FeeManagerImpl getInstance()
	{
		if(instance == null){
			instance=new FeeManagerImpl();
		}
		
		return instance;
	}
	
	private FeeManagerImpl() {

	}
@Override
	public List<FeeAdmissionBean> getPendingfeeInfo(int limit){
		
	List<FeeAdmissionBean> feeAdmissionBeans = feeDetailDao.getPendingfeeInfo(limit);
	
	return feeAdmissionBeans;
		
	}
	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		FeeDiscountHead feeDiscountHead =  null;
		feeDiscountHead = feeDetailDao.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeDetailDao.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeDetailDao.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {

		feeDetailDao.deleteFeeDiscountHead(headId);
	}

	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria) {
		// details = null;
		 List<ApplicableFeeDetail> details =  feeDetailDao.getApplicableFeeDetails(criteria);
		return details;
	}

	public void addFeeDetail(ApplicableFeeDetail feeDetail) {
		feeDetailDao.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(ApplicableFeeDetail feeDetail) {
		feeDetailDao.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId) {
		feeDetailDao.deleteFeeDetail(course, branch, feeHeadId);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId) {
		List<StudentFeeStaging> feeStaging = new ArrayList<StudentFeeStaging>();
		feeStaging = feeDetailDao.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}
	
	@Override
	public void moveStaggingandBaseFeetoTransaction(StudentBasicInfo basicInfo,List<StudentFeeStaging> newStagging){

		Long fileNo = basicInfo.getFileNo();
		List<FeeTransaction> creditFeetransaction = new ArrayList<FeeTransaction>();
		List<FeeTransaction> debitFeetransaction = new ArrayList<FeeTransaction>();

		if (newStagging != null) {
			List<StudentFeeStaging> oldFeeStagingObj = feeDetailDao.getStudentFeeStaging(fileNo, null);
			List<StudentFeeStaging> newFeeStagingObj = newStagging;

			Set<StudentFeeStaging> oldFeeStagingSet = new HashSet<StudentFeeStaging>(oldFeeStagingObj);
			Set<StudentFeeStaging> newFeeStagingSet = new HashSet<StudentFeeStaging>(newStagging);

			//getting new Data of Feestaging
			newFeeStagingSet.removeAll(oldFeeStagingSet);

			// iterate over new obj and creating fee transaction
			if (newFeeStagingSet != null && newFeeStagingSet.size() > 0) {
				for (StudentFeeStaging feeStaging : newFeeStagingSet) {
					if (feeStaging.isApproved()) {
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(feeStaging.getDiscountHead());
						feeTransaction.setAmount(feeStaging.getAmount());
						creditFeetransaction.add(feeTransaction);
					}
				}
			}
			
			//creating map of old vs new objects for comparing older and new approved status
			Map<StudentFeeStaging, StudentFeeStaging> feeStagingMap = new HashMap<StudentFeeStaging, StudentFeeStaging>();
			for(StudentFeeStaging oldFeeStaging : oldFeeStagingObj){
				for(StudentFeeStaging newFeeStaging : newFeeStagingObj){
					if(oldFeeStaging.equals(newFeeStaging)){
						feeStagingMap.put(oldFeeStaging, newFeeStaging);
						break;
					}
				}
			}
			
			for(StudentFeeStaging oldFeeStag:feeStagingMap.keySet())
			{
				StudentFeeStaging newFeeStag=feeStagingMap.get(oldFeeStag);
				if(newFeeStag!=null && "W".equalsIgnoreCase(newFeeStag.getDiscountHead().getTransactionType())){
					if(newFeeStag.isApproved() && !oldFeeStag.isApproved()){
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(newFeeStag.getDiscountHead());
						feeTransaction.setAmount(newFeeStag.getAmount());
						creditFeetransaction.add(feeTransaction);
					}
					else if(!newFeeStag.isApproved() && oldFeeStag.isApproved()){
						oldFeeStag.getDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(oldFeeStag.getDiscountHead());
						feeTransaction.setAmount(oldFeeStag.getAmount());
						debitFeetransaction.add(feeTransaction);
					}
				}
				//if no new entry found against old record means record is to be deleted
				//check if it was approved earlier if so create a reversal
				else{
					if(oldFeeStag.isApproved() && "W".equalsIgnoreCase(oldFeeStag.getDiscountHead().getTransactionType())){
						oldFeeStag.getDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(oldFeeStag.getDiscountHead());
						feeTransaction.setAmount(oldFeeStag.getAmount());
						debitFeetransaction.add(feeTransaction);
					}
				}
			}
		}
		
		//creating base applicable fee and other amenties charges
		Boolean managemetApproval=isManagementApproved(fileNo);
		if(!managemetApproval){
			
			ApplicableFeeCriteria criteria = CommonUtil.getApplicableFeeCriteriaFromStudentBasicInfo(basicInfo);
			List<ApplicableFeeDetail> applicableFee=getApplicableFeeDetail(criteria);
			
			for(ApplicableFeeDetail applicableFeeDetail:applicableFee){
				FeeTransaction feeTransaction = new FeeTransaction(null, null, null, applicableFeeDetail.getFeeAmount(), applicableFeeDetail.getFeeDetail());
                debitFeetransaction.add(feeTransaction);
			}
			
			for(StudentFeeStaging feeStaging:newStagging){
				if("D".equalsIgnoreCase(feeStaging.getDiscountHead().getTransactionType())){
					FeeTransaction feeTransaction = new FeeTransaction(null, null, null, feeStaging.getAmount(), feeStaging.getDiscountHead());
					debitFeetransaction.add(feeTransaction);
				}
			}
		
			StudentDetail sd=admissionManager.getStudentDtl(fileNo);
			sd.setManagementApproval(true);
			admissionManager.updateStudentDtl(sd);
			
		}
		for(FeeTransaction feeTransaction : creditFeetransaction){
			
			feeTransaction.setFileNo(fileNo);
			feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
			feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
			feeDetailDao.addFeeTransactionCredit(feeTransaction);
		}

		
		for(FeeTransaction feeTransaction : debitFeetransaction){

			feeTransaction.setFileNo(fileNo);
			feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
			feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
			feeDetailDao.addFeeTransactionDebit(feeTransaction);
		}
		
		feeDetailDao.saveStudentFeeStaging(newStagging);
	}

	public void saveStudentFeeStaging(List<StudentFeeStaging> newStagging) {

		feeDetailDao.saveStudentFeeStaging(newStagging);
	}

	public void saveStudentFeeStaging(StudentFeeStaging studentFeeStaging){
		feeDetailDao.saveStudentFeeStaging(studentFeeStaging);
	}	
	public void deleteStudentFeeStagingbyfileNo(StudentFeeStaging feeStaging){

		feeDetailDao.deleteStudentFeeStagingByFileNo(feeStaging);
	}

//	public FeeTransaction getFeeTransaction(String fileNo) {
//		FeeTransaction transaction = null;
//		transaction = feeDetailDao.getFeeTransaction(fileNo);
//		return transaction;
//	}
//
//	public void addFeeTransaction(FeeTransaction feeTransaction) {
//		feeDetailDao.addFeeTransaction(feeTransaction);
//	}

	@Override
	public void generateDiscountinStagging(Long fileNo) {
		feeDetailDao.generateDiscountforStudent(fileNo);
	}

	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo) {

		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		
		feeDetailDao.addFeeTransactionDebit(feeTransaction);
		
	}

	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {

		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {
		
		feeDetailDao.addFeeTransactionCredit(feeTransaction);
		
	}

	@Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(Long fileNo){
		
		FeeTransactionAdmissionBean transactionAdmissionBean = new FeeTransactionAdmissionBean();
			
		List<FeeTransaction> TransactionCredit = feeDetailDao.getCreditedFeeTransaction(fileNo);
	    transactionAdmissionBean.setFeeTransactionCredit(TransactionCredit); 	

	    List<FeeTransaction> TransactionDebit = feeDetailDao.getDebitedFeeTransaction(fileNo);
	    transactionAdmissionBean.setFeeTransactionDebit(TransactionDebit);

	    Double previousBalance = feeDetailDao.getPreviousSemBalance(fileNo);
	    transactionAdmissionBean.setAmountDiffrence(previousBalance);
	    
	    return transactionAdmissionBean;
	}

	public Boolean isManagementApproved(Long fileNo){
		return feeDetailDao.isManagementApproved(fileNo);
		
		
	}
}

