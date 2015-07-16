package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.ScholarshipManager;
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

	@Override
	public void saveScholarship(Scholarship scholarship) {
		scholarshipDao.saveScholarship(scholarship);
	}

	@Override
	public void saveScholarshipPaymentDtl(
			List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo) {
		scholarshipDao.saveScholarshipPaymentDtl(scholarshipPaymentDetails, fileNo);
	}

	@Override
	public void saveScholarshipPaymentDetail(
			ScholarshipPayment scholarshipPaymentDetail) {
		scholarshipDao.saveScholarshipPaymentDetail(scholarshipPaymentDetail);
		
	}

	@Override
	public void deleteScholarshipPaymentDetailExclusion(
			List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo) {
		scholarshipDao.deleteScholarshipPaymentDetailExclusion(scholarshipPaymentDetails, fileNo);
	}

	@Override
	public Scholarship getScholarship(Long fileNo) {
		Scholarship scholarship =scholarshipDao.getScholarship(fileNo);
		return scholarship;
	}

	@Override
	public List<ScholarshipPayment> getScholarshipPayments(Long fileNo) {
		List<ScholarshipPayment> scholarshipPayments = scholarshipDao.getScholarshipPayments(fileNo);
		return scholarshipPayments;
	}

	
}
