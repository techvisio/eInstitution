package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.db.FeeDetailDao;
import com.techvisio.einstitution.manager.FeeDetailManager;
import com.techvisio.einstitution.util.ContextProvider;

public class FeeDetailManagerImpl implements FeeDetailManager{

	FeeDetailDao feeDetailDao = ContextProvider.getContext().getBean(
			FeeDetailDao.class);

	
	private static FeeDetailManagerImpl instance=null;
	public static synchronized FeeDetailManagerImpl getInstance()
	{
		if(instance == null){
			instance=new FeeDetailManagerImpl();
		}
		
		return instance;
	}
	
	private FeeDetailManagerImpl() {
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

	public FeeDetail getFeeDetail(Long FeeHeadId,Long Course,Long Branch) {
		FeeDetail detail = null;
		detail = feeDetailDao.getFeeDetail(FeeHeadId, Course, Branch);
		return detail;
	}

	public void addFeeDetail(FeeDetail feeDetail) {
		feeDetailDao.addFeeDetail(feeDetail);
		
	}

	public void updateFeeDetail(FeeDetail feeDetail) {
		feeDetailDao.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long FeeHeadId,Long Course,Long Branch) {
		feeDetailDao.deleteFeeDetail(FeeHeadId, Course, Branch);
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

}


