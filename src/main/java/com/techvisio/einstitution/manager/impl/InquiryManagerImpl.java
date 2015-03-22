package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class InquiryManagerImpl implements InquiryManager {

	
	InquiryDao inquiryDao=ContextProvider.getContext().getBean(InquiryDao.class);
	UniqueIdentifierGenerator identifierGenerator=UniqueIdentifierFactory.getGenerator();


	public AdmissionInquiry getInquiry(Long inquiryId) {

		AdmissionInquiry admissionInquiry=null;
		
		admissionInquiry=inquiryDao.getInquiry(inquiryId);
		
		return admissionInquiry;
	}

	public Long addInquiry (AdmissionInquiry admissionInquiry) {
		Long enquiryId=null;
		if(admissionInquiry!=null){
		 enquiryId=identifierGenerator.getUniqueIdentifierForEnquiry();
		admissionInquiry.setEnquiryId(enquiryId);
		inquiryDao.addInquiry(admissionInquiry);
		}
		return enquiryId;
	}

	public void updateInquiry(AdmissionInquiry admissionInquiry) {

		inquiryDao.updateInquiry(admissionInquiry);
	}

	public void deleteInquiry(Long inquiryId) {

		inquiryDao.deleteInquiry(inquiryId);;
	}

}
