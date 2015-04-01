package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.ScholarshipPaymentDetail;

public interface ScholarshipDao {

	public ScholarshipDetail getScholarshipDetail(Long fileNo);
	public void addScholarDetail(ScholarshipDetail scholarshipDetail);
	public void deleteScholarshipDetail(Long fileNo);
	
	
	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(Long fileNo);
	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
	public void deleteScholarshipPaymentDetail(Long fileNo);
	
	
	
}
