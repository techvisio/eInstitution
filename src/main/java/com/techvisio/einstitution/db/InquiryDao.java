package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.AdmissionEnquiry;


public interface InquiryDao {

	public void getInquiry(AdmissionEnquiry admissionInquiry);
	
	public void addInquiry (AdmissionEnquiry admissionInquiry);
	
	public void updateInquiry(AdmissionEnquiry admissionInquiry);
	
	public void deleteInquiry(AdmissionEnquiry admissionInquiry);
}
