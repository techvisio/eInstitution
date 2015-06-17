package com.techvisio.einstitution.manager;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
public interface ScholarshipManager {

	public Scholarship getScholarshipDetail(Long fileNo);
	public void addScholarDetail(Scholarship scholarshipDetail);
	public void deleteScholarshipDetail(Long fileNo);
	public void accomodateManagementChanges(StudentBasicInfo basicInfo, Scholarship newScholarshipDetail);
	

//	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(String fileNo);
//	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void deleteScholarshipPaymentDetail(String fileNo);

}
