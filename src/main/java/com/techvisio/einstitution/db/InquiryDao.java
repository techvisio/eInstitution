package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.AdmissionInquiry;


public interface InquiryDao {

	public void getInquiry(AdmissionInquiry admissionInquiry);
	
	public void addInquiry (AdmissionInquiry admissionInquiry);
	
	public void updateInquiry(AdmissionInquiry admissionInquiry);
	
	public void deleteInquiry(AdmissionInquiry admissionInquiry);
}
