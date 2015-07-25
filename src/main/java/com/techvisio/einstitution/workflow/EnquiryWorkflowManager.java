package com.techvisio.einstitution.workflow;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTask;
import com.techvisio.einstitution.beans.SearchCriteria;
@Component
public interface EnquiryWorkflowManager {
	
	public Long saveEnquiryAndTask (EnquiryAndTask enquiryAndTask);
    
	public EnquiryAndTask getEnquiryandTask(Long inquiryId);
	
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

	public Long proceedToAdmission(EnquiryAndTask enquiryAndTaskBean);

	public Long toggleEnquiryStatus(EnquiryAndTask enquiryAndTaskBean);
}
