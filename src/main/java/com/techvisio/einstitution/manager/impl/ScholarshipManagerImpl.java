package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class ScholarshipManagerImpl implements ScholarshipManager {
	private static CustomLogger logger = CustomLogger.getLogger(ScholarshipManagerImpl.class);	
	@Autowired
	ScholarshipDao scholarshipDao ;
	
	@Autowired
	AdmissionManager admissionManager;
	
	@Autowired
	FeeManager feeManager;
	private static ScholarshipManagerImpl instance = null;

	public static synchronized ScholarshipManagerImpl getInstance() {
		if (instance == null) {
			instance = new ScholarshipManagerImpl();
		}

		return instance;
	}

	private ScholarshipManagerImpl() {
		// TODO Auto-generated constructor stub
	}

	public ScholarshipDetail getScholarshipDetail(Long fileNo) {
		logger.info("{} : calling getScholarshipDetail method by passing file no:{}  ",this.getClass().getName(), fileNo);

		ScholarshipDetail scholarshipDetail = scholarshipDao.getScholarshipDetail(fileNo);
		
		return scholarshipDetail;

	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {
		logger.info("{} : calling addScholarDetail method for file no:{}  ",this.getClass().getName(), scholarshipDetail.getFileNo());
		scholarshipDao.addScholarDetail(scholarshipDetail);
	}

	public void deleteScholarshipDetail(Long fileNo) {
		logger.info("{} : calling deleteScholarshipDetail method by passing file no:{}  ",this.getClass().getName(), fileNo);
		scholarshipDao.deleteScholarshipDetail(fileNo);
	}

	@Override
	public void accomodateManagementChanges(StudentBasicInfo basicInfo, ScholarshipDetail newScholarshipDetail) {
		logger.info("{} : Accomodate Management Changes  for Student:{}  ",this.getClass().getName(), basicInfo.getFirstName()+basicInfo.getLastName());
		ScholarshipDetail newScholarshipObj = newScholarshipDetail;
		ScholarshipDetail oldScholarshipObj = scholarshipDao.getScholarshipDetail(newScholarshipObj.getFileNo());

		
		if (newScholarshipObj != null && newScholarshipObj.getStateId() != null) {
			if (oldScholarshipObj != null) {

				if (newScholarshipObj.isApproved() && !oldScholarshipObj.isApproved()) {

					FeeTransaction feeTransaction = new FeeTransaction();
					feeTransaction.setAmount(newScholarshipObj.getAmount());
					feeTransaction.setFileNo(newScholarshipObj.getFileNo());
					feeTransaction.getFeeDiscountHead().setHeadId(AppConstants.SCHOLARSHIP_HEAD_ID);
					feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
					feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
					feeManager.addFeeTransactionCredit(feeTransaction);
				}

				else if (!newScholarshipObj.isApproved() && oldScholarshipObj.isApproved()) {

					FeeTransaction feeTransaction = new FeeTransaction();
					feeTransaction.setAmount(oldScholarshipObj.getAmount());
					feeTransaction.setFileNo(newScholarshipDetail.getFileNo());
					feeTransaction.getFeeDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
					feeManager.addFeeTransactionDebit(feeTransaction);
				}

			}
			else {

				if (newScholarshipObj.isApproved()) {

					FeeTransaction feeTransaction = new FeeTransaction();
					feeTransaction.setAmount(newScholarshipObj.getAmount());
					feeTransaction.setFileNo(newScholarshipDetail.getFileNo());
					feeTransaction.getFeeDiscountHead().setHeadId(AppConstants.SCHOLARSHIP_HEAD_ID);
					feeManager.addFeeTransactionCredit(feeTransaction);
				}
			}
		}

		addScholarDetail(newScholarshipDetail);
	}
	
}
