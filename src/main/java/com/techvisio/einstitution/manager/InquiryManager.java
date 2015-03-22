package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.AdmissionInquiry;

public interface InquiryManager {

	public AdmissionInquiry getInquiry(Long inquiryId);

	public Long addInquiry (AdmissionInquiry admissionInquiry);

	public void updateInquiry(AdmissionInquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);


}
