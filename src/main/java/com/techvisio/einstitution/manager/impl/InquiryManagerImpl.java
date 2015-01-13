package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class InquiryManagerImpl implements InquiryManager {

	
	InquiryDao inquiryDao=ContextProvider.getContext().getBean(InquiryDao.class);
	


	public AdmissionInquiry getInquiry(Long inquiryId) {

		AdmissionInquiry admissionInquiry=null;
		
		admissionInquiry=inquiryDao.getInquiry(inquiryId);
		
		return admissionInquiry;
	}

	public void addInquiry (AdmissionInquiry admissionInquiry) {
		//TODO:populate inquiry id from seq factory
		inquiryDao.addInquiry(admissionInquiry);
	}

	public void updateInquiry(AdmissionInquiry admissionInquiry) {

		inquiryDao.updateInquiry(admissionInquiry);
	}

	public void deleteInquiry(Long inquiryId) {

		inquiryDao.deleteInquiry(inquiryId);;
	}

}
