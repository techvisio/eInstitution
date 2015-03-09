package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.ScholarshipDetail;

public interface ScholarshipWorkflowManager {

	public ScholarshipDetail getScholarshipDetail(String fileNo);
	public void addScholarDetail(ScholarshipDetail scholarshipDetail);
	public void updateScholarDetail(ScholarshipDetail scholarshipDetail);
	public void deleteScholarshipDetail(String fileNo);
	
	
//	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(String fileNo);
//	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void deleteScholarshipPaymentDetail(String fileNo);

	
	
}
