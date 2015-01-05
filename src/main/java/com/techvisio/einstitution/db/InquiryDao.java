package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.AdmissionInquiry;


public interface InquiryDao {

	public AdmissionInquiry getInquiry(Long inquiryId);
	
	public void addInquiry (AdmissionInquiry admissionInquiry);
	
	public void updateInquiry(AdmissionInquiry admissionInquiry);
	
	public void deleteInquiry(Long inquiryId);
}
