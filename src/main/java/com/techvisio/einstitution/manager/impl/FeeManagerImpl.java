package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class FeeManagerImpl implements FeeManager{
	private static CustomLogger logger = CustomLogger.getLogger(FeeManagerImpl.class);
	@Autowired
	FeeDao feeDetailDao ;

	@Autowired
	AdmissionManager admissionManager;

	@Autowired
	CacheManager  cacheManager;

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
	public List<FeeAdmission> getPendingfeeInfo(int limit){
		logger.info("{} : calling getPendingfeeInfo method by passing limit:{} ",this.getClass().getName(), limit);	
		List<FeeAdmission> feeAdmissionBeans = feeDetailDao.getPendingfeeInfo(limit);

		return feeAdmissionBeans;

	}
	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria) {
		// details = null;
		logger.info("{} : calling getApplicableFeeDetails method for courseId:{} and branchId:{} ",this.getClass().getName(), criteria.getCourseId(),criteria.getBranchId());
		List<ApplicableFeeDetail> details =  feeDetailDao.getApplicableFeeDetails(criteria);
		return details;
	}

	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getDebitedFeeTransaction method by passing file no:{} ",this.getClass().getName(), fileNo);
		List<FeeTransactionDebit> feeTransactionDebits = null;
		feeTransactionDebits = feeDetailDao.getDebitedFeeTransaction(fileNo);
		return feeTransactionDebits;
	}

	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit) {
		feeDetailDao.addFeeTransactionDebit(feeTransactionDebit);

	}

	public List<FeeTransactionCredit> getCreditedFeeTransaction(Long fileNo) {
		List<FeeTransactionCredit> feeTransactionCredits = null;
		feeTransactionCredits = feeDetailDao.getCreditedFeeTransaction(fileNo);
		return feeTransactionCredits;
	}

	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit) {
		feeDetailDao.addFeeTransactionCredit(feeTransactionCredit);
	}

	@Override
	public FeeTransactionAdmission getFeeTransactionDetail(Long fileNo){
		logger.info("{} : calling getCreditedFeeTransaction, getDebitedFeeTransaction and getPreviousSemBalance  method by passing file no:{} ",this.getClass().getName(),fileNo);		
		FeeTransactionAdmission transactionAdmissionBean = new FeeTransactionAdmission();

		List<FeeTransactionCredit> TransactionCredit = feeDetailDao.getCreditedFeeTransaction(fileNo);
		transactionAdmissionBean.setFeeTransactionCredit(TransactionCredit); 	

		List<FeeTransactionDebit> TransactionDebit = feeDetailDao.getDebitedFeeTransaction(fileNo);
		transactionAdmissionBean.setFeeTransactionDebit(TransactionDebit);

		Double previousBalance = feeDetailDao.getPreviousSemBalance(fileNo);
		transactionAdmissionBean.setAmountDiffrence(previousBalance);

		return transactionAdmissionBean;
	}

}

