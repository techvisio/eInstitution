package com.techvisio.einstitution.db;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;


public interface EnquiryDao {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	public void addInquiry (AdmissionEnquiry admissionInquiry);
	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate);
	public void updateInquiry(AdmissionEnquiry admissionInquiry);
	public void deleteInquiry(Long inquiryId);
	List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria  searchCriteria);
}
