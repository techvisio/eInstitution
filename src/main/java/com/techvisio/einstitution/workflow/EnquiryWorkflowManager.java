package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.SearchCriteria;

public interface EnquiryWorkflowManager {

	public EnquiryAndTaskBean getEnquiryandTask(Long inquiryId);

	public Long addEnquiryandTask (AdmissionEnquiry admissionInquiry);

	public void updateEnquiryandTask(AdmissionEnquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

}
