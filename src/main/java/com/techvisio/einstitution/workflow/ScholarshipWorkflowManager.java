package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
@Transactional
public interface ScholarshipWorkflowManager {

	public Scholarship getScholarship(Long fileNo);
	public void saveScholarship(Scholarship scholarship);	

	public List<ScholarshipPayment> getScholarshipPayments(Long fileNo);
	public void saveScholarshipPaymentDtl(List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo);
	public void saveScholarshipPaymentDetail(ScholarshipPayment scholarshipPaymentDetail);
	public void deleteScholarshipPaymentDetailExclusion(List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo);

}
