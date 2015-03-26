package com.techvisio.einstitution.workflow;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.SearchCriteria;

public interface EnquiryWorkflowManager {

	public EnquiryAndTaskBean getEnquiryandTask(Long inquiryId);
	
	public AdmissionEnquiry getInquiryByTaskDate(Date taskDate);

	public Long addEnquiryandTask (EnquiryAndTaskBean enquiryAndTaskBean);

	public Long updateEnquiryandTask(EnquiryAndTaskBean enquiryAndTaskBean);

	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

}
