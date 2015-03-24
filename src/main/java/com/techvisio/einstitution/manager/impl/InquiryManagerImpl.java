package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.InquiryDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class InquiryManagerImpl implements InquiryManager {

	
	InquiryDao inquiryDao=ContextProvider.getContext().getBean(InquiryDao.class);
	UniqueIdentifierGenerator identifierGenerator=UniqueIdentifierFactory.getGenerator();


	private static InquiryManagerImpl instance=null;
	public static synchronized InquiryManagerImpl getInstance()
	{
		if(instance == null){
			instance=new InquiryManagerImpl();
		}
		
		return instance;
	}
	
	private InquiryManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AdmissionEnquiry getInquiry(Long inquiryId) {

		AdmissionEnquiry admissionInquiry=null;
		
		admissionInquiry=inquiryDao.getInquiry(inquiryId);
		
		return admissionInquiry;
	}

	public Long addInquiry (AdmissionEnquiry admissionInquiry) {
		Long enquiryId=null;
		if(admissionInquiry!=null){
		 enquiryId=identifierGenerator.getUniqueIdentifierForEnquiry();
		admissionInquiry.setEnquiryId(enquiryId);
		inquiryDao.addInquiry(admissionInquiry);
		}
		return enquiryId;
	}

	public void updateInquiry(AdmissionEnquiry admissionInquiry) {

		inquiryDao.updateInquiry(admissionInquiry);
	}

	public void deleteInquiry(Long inquiryId) {

		inquiryDao.deleteInquiry(inquiryId);;
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		return inquiryDao.searchInqByCriteria(searchCriteria);
	}

}
