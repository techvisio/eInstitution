package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.ScholarshipDetail;

public interface ScholarshipManager {

	public ScholarshipDetail getScholarshipDetail(String fileNo);
	public void addScholarDetail(ScholarshipDetail scholarshipDetail);
	public void deleteScholarshipDetail(String fileNo);
	

//	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(String fileNo);
//	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void deleteScholarshipPaymentDetail(String fileNo);

}
