package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
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

	public List<FeeDetail> getFeeDetail(Long course,Long branch ) {
		// details = null;
		 List<FeeDetail> details =  feeDetailDao.getFeeDetail(course, branch);
		return details;
	}

	public void addFeeDetail(FeeDetail feeDetail) {
		feeDetailDao.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(FeeDetail feeDetail) {
		feeDetailDao.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course,Long branch, Integer semester ) {
		feeDetailDao.deleteFeeDetail(course, branch, semester);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(String fileNo, Long feeHeadId) {
		List<StudentFeeStaging> feeStaging = new ArrayList<StudentFeeStaging>();
		feeStaging = feeDetailDao.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeDetailDao.addStudentFeeStaging(studentFeeStaging);
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeDetailDao.updateStudentFeeStaging(studentFeeStaging);
	}
	
	public void updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings){
		
		feeDetailDao.updateStudentFeeStaging(studentFeeStagings);
	} 
	
	public void deleteStudentFeeStaging(StudentFeeStaging studentFeeStaging){

		feeDetailDao.deleteStudentFeeStaging(studentFeeStaging);
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

	public void generateStudentFeeStaging(String fileNo) {
		StudentFeeStaging feeStaging=new StudentFeeStaging();
		feeStaging.setFileNo(fileNo);
		feeStaging.setCreatedBy("testUser");
	}

	public List<FeeTransaction> getDebitedFeeTransaction(String fileNo) {

		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		
		feeDetailDao.addFeeTransactionDebit(feeTransaction);
		
	}

	public List<FeeTransaction> getCreditedFeeTransaction(String fileNo) {

		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {
		
		feeDetailDao.addFeeTransactionCredit(feeTransaction);
		
	}

	@Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(String fileNo){
		
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

