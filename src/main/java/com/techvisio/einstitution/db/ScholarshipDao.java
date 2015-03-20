package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.ScholarshipPaymentDetail;

public interface ScholarshipDao {

	public ScholarshipDetail getScholarshipDetail(String fileNo);
	public void addScholarDetail(ScholarshipDetail scholarshipDetail);
	public void deleteScholarshipDetail(String fileNo);
	
	
	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(String fileNo);
	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
	public void deleteScholarshipPaymentDetail(String fileNo);
	
	
	
}
