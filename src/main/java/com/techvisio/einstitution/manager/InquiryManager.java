package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;

public interface InquiryManager {

	public AdmissionEnquiry getInquiry(Long inquiryId);

	public Long addInquiry (AdmissionEnquiry admissionInquiry);

	public void updateInquiry(AdmissionEnquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

}
