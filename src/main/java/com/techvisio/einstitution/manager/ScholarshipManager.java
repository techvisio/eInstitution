package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;

public interface ScholarshipManager {

	public ScholarshipDetail getScholarshipDetail(Long fileNo);
	public void addScholarDetail(ScholarshipDetail scholarshipDetail);
	public void deleteScholarshipDetail(Long fileNo);
	public void accomodateManagementChanges(StudentBasicInfo basicInfo, ScholarshipDetail newScholarshipDetail);
	

//	List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(String fileNo);
//	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail);
//	public void deleteScholarshipPaymentDetail(String fileNo);

}
