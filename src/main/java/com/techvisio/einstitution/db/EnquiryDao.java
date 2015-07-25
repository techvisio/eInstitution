package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;

@Component
public interface EnquiryDao {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	public Long saveInquiry (AdmissionEnquiry admissionInquiry);
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria);
}
