package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;


public interface InquiryDao {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	
	public void addInquiry (AdmissionEnquiry admissionInquiry);
	
	public void updateInquiry(AdmissionEnquiry admissionInquiry);
	
	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);
}
