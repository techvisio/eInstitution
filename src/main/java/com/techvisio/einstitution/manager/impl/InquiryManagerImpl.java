package com.techvisio.einstitution.manager.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class InquiryManagerImpl implements InquiryManager {

	
	InquiryDao inquiryDao=ContextProvider.getContext().getBean(InquiryDao.class);
	


	public void getInquiryDetail(AdmissionInquiry admissionInquiry) {

		inquiryDao.getInquiry(admissionInquiry);
	}

	public void addInquiryDetail(AdmissionInquiry admissionInquiry) {
		//TODO:populate inquiry id from seq factory
		inquiryDao.addInquiry(admissionInquiry);
	}

	public void updateInquiryDetail(AdmissionInquiry admissionInquiry) {

		inquiryDao.updateInquiry(admissionInquiry);
	}

	public void deleteInquiryDetail(AdmissionInquiry admissionInquiry) {

		inquiryDao.deleteInquiry(admissionInquiry);
	}

}
