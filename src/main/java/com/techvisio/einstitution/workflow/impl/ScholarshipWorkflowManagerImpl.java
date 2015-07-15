package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
@Component
@Transactional
public class ScholarshipWorkflowManagerImpl implements ScholarshipWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(ScholarshipWorkflowManagerImpl.class);
	@Autowired
	ScholarshipManager scholarshipManager;
	@Override
	public void saveScholarship(List<Scholarship> scholarship) {
		scholarshipManager.saveScholarship(scholarship);
		
	}
	@Override
	public void saveScholarshipPaymentDtl(
			List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo) {
		scholarshipManager.saveScholarshipPaymentDtl(scholarshipPaymentDetails, fileNo);
		
	}
	@Override
	public void saveScholarshipPaymentDetail(
			ScholarshipPayment scholarshipPaymentDetail) {
		scholarshipManager.saveScholarshipPaymentDetail(scholarshipPaymentDetail);
		
	}
	@Override
	public void deleteScholarshipPaymentDetailExclusion(
			List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo) {
		scholarshipManager.deleteScholarshipPaymentDetailExclusion(scholarshipPaymentDetails, fileNo);
		
	}
	@Override
	public List<Scholarship> getScholarship(Long fileNo) {
	   List<Scholarship> scholarship = scholarshipManager.getScholarship(fileNo); 
		return scholarship;
	}
	@Override
	public List<ScholarshipPayment> getScholarshipPayments(Long fileNo) {
		List<ScholarshipPayment> scholarshipPayments = scholarshipManager.getScholarshipPayments(fileNo);
		return scholarshipPayments;
	}
	
	
}
