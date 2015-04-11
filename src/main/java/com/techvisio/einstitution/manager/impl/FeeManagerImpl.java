package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.ContextProvider;

public class FeeManagerImpl implements FeeManager{

	FeeDao feeDetailDao = ContextProvider.getContext().getBean(
			FeeDao.class);

	
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

	public void saveStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings) {
		feeDetailDao.saveStudentFeeStaging(studentFeeStagings);
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


}

