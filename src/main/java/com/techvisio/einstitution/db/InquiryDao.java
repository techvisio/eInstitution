package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.beans.SearchCriteria;


public interface InquiryDao {

	public AdmissionInquiry getInquiry(Long inquiryId);
	
	public void addInquiry (AdmissionInquiry admissionInquiry);
	
	public void updateInquiry(AdmissionInquiry admissionInquiry);
	
	public void deleteInquiry(Long inquiryId);
	
	List<AdmissionInquiry> searchInqByCriteria(SearchCriteria  searchCriteria);
}
