package com.techvisio.einstitution.workflow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
@Component
public class EnquiryWorkflowManagerImpl implements EnquiryWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(EnquiryWorkflowManagerImpl.class);	

	@Autowired
	EnquiryManager enquiryManager;

	@Override
	public AdmissionEnquiry getInquiry(Long enquiryId) {
        AdmissionEnquiry admissionEnquiry = enquiryManager.getInquiry(enquiryId);
		return admissionEnquiry;
	}

	@Override
	public void saveInquiry(AdmissionEnquiry admissionEnquiry) {
		enquiryManager.saveInquiry(admissionEnquiry);
		
	}
	

}
