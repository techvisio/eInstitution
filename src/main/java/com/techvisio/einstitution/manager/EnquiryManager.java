package com.techvisio.einstitution.manager;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;

public interface EnquiryManager {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	public AdmissionEnquiry getInquiryByTaskDate(Date taskDate);

	public Long addInquiry (AdmissionEnquiry admissionInquiry);

	public void updateInquiry(AdmissionEnquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);

}