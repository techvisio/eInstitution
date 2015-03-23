package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.AdmissionInquiry;

public interface InquiryWorkflowManager {

	public AdmissionInquiry getInquiry(Long inquiryId);

	public Long addInquiry (AdmissionInquiry admissionInquiry);

	public void updateInquiry(AdmissionInquiry admissionInquiry);

	public void deleteInquiry(Long inquiryId);

}
