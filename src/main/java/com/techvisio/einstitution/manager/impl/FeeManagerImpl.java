package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDetailDao;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.ContextProvider;

public class FeeManagerImpl implements FeeManager{

	FeeDetailDao feeDetailDao = ContextProvider.getContext().getBean(
			FeeDetailDao.class);

	
	private static FeeManagerImpl instance=null;
	public static synchronized FeeManagerImpl getInstance()
	{
		if(instance == null){
			instance=new FeeManagerImpl();
		}
		
		return instance;
	}
	
	private FeeManagerImpl() {
		// TODO Auto-generated constructor stub
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

	public List<FeeDetail> getFeeDetail(Long course,Long branch, Integer semester ) {
		// details = null;
		 List<FeeDetail> details = feeDetailDao.getFeeDetail(course, branch, semester);
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

	public StudentFeeStaging getStudentFeeStaging(String fileNo) {
		StudentFeeStaging feeStaging = null;
		feeStaging = feeDetailDao.getStudentFeeStaging(fileNo);
		return feeStaging;
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeDetailDao.addStudentFeeStaging(studentFeeStaging);
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		feeDetailDao.updateStudentFeeStaging(studentFeeStaging);
	}

	public void deleteStudentFeeStaging(String fileNo) {
		feeDetailDao.deleteStudentFeeStaging(fileNo);
	}

	public FeeTransaction getFeeTransaction(String fileNo) {
		FeeTransaction transaction = null;
		transaction = feeDetailDao.getFeeTransaction(fileNo);
		return transaction;
	}

	public void addFeeTransaction(FeeTransaction feeTransaction) {
		feeDetailDao.addFeeTransaction(feeTransaction);
	}

	public void createStagingFee(String fileNo) {
		// TODO Auto-generated method stub
		
	}

}


