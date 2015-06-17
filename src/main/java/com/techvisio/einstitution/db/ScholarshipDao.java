package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;

@Component
public interface ScholarshipDao {

	public Scholarship getScholarshipDetail(Long fileNo);
	public void addScholarDetail(Scholarship scholarshipDetail);
	public void deleteScholarshipDetail(Long fileNo);
	
	
	List<ScholarshipPayment> getScholarshipPaymentDetail(Long fileNo);
	public void addScholarshipPaymentDetail(ScholarshipPayment scholarshipPaymentDetail);
	public void updateScholarshipPaymentDetail(ScholarshipPayment scholarshipPaymentDetail);
	public void deleteScholarshipPaymentDetail(Long fileNo);
	
	
	
}
