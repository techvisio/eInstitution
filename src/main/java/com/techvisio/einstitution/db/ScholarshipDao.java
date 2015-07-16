package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;

@Component
public interface ScholarshipDao {

    public List<Scholarship> getScholarship(Long fileNo);
	public void saveScholarship(List<Scholarship> scholarship);	
	public void saveScholarship(Scholarship scholarship);
	public void deleteScholarshipExlusion(List<Scholarship> scholarships, Long fileNo);
	
	public List<ScholarshipPayment> getScholarshipPayments(Long fileNo);
	public void saveScholarshipPaymentDtl(List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo);
	public void saveScholarshipPaymentDetail(ScholarshipPayment scholarshipPaymentDetail);
	public void deleteScholarshipPaymentDetailExclusion(List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo);
	
	
}
