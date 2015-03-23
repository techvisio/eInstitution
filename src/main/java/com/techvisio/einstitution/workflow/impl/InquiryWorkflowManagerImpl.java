package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.manager.impl.InquiryManagerImpl;
import com.techvisio.einstitution.workflow.InquiryWorkflowManager;

public class InquiryWorkflowManagerImpl implements InquiryWorkflowManager {

	InquiryManager inquiryManager = InquiryManagerImpl.getInstance();
	
	
	@Override
	public AdmissionInquiry getInquiry(Long inquiryId) {
		
		
		return inquiryManager.getInquiry(inquiryId);
	}

	@Override
	public Long addInquiry(AdmissionInquiry admissionInquiry) {
	
		return  inquiryManager.addInquiry(admissionInquiry);
	}

	@Override
	public void updateInquiry(AdmissionInquiry admissionInquiry) {
		
		inquiryManager.updateInquiry(admissionInquiry);
		
	}

	@Override
	public void deleteInquiry(Long inquiryId) {

		inquiryManager.deleteInquiry(inquiryId);
	}

	
	
	
}
