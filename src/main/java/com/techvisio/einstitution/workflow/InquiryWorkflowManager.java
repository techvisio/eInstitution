package com.techvisio.einstitution.workflow;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.beans.SearchCriteria;

public interface InquiryWorkflowManager {

	public AdmissionInquiry getInquiry(Long inquiryId);
	
	public AdmissionInquiry getInquiryByTaskDate(Date taskDate);

	public Long addInquiry (AdmissionInquiry admissionInquiry);

	public void updateInquiry(AdmissionInquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionInquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

}
