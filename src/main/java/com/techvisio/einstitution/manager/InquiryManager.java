package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.AdmissionInquiry;

public interface InquiryManager {

	public void getInquiryDetail(AdmissionInquiry admissionInquiry);
	public void addInquiryDetail(AdmissionInquiry admissionInquiry);
	public void updateInquiryDetail(AdmissionInquiry admissionInquiry);
	public void deleteInquiryDetail(AdmissionInquiry admissionInquiry);
	

}
