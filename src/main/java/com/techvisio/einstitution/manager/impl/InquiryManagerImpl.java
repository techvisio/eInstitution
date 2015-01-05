package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class InquiryManagerImpl implements InquiryManager {

	
	InquiryDao inquiryDao=ContextProvider.getContext().getBean(InquiryDao.class);
	


	public void getInquiryDetail(Long inquiryId) {

		inquiryDao.getInquiry(inquiryId);
	}

	public void addInquiryDetail(AdmissionInquiry admissionInquiry) {
		//TODO:populate inquiry id from seq factory
		inquiryDao.addInquiry(admissionInquiry);
	}

	public void updateInquiryDetail(AdmissionInquiry admissionInquiry) {

		inquiryDao.updateInquiry(admissionInquiry);
	}

	public void deleteInquiryDetail(Long inquiryId) {

		inquiryDao.deleteInquiry(inquiryId);;
	}

}
