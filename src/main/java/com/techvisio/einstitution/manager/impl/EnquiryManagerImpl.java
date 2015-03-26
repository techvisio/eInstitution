package com.techvisio.einstitution.manager.impl;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.util.ContextProvider;

public class EnquiryManagerImpl implements EnquiryManager {

	
	EnquiryDao inquiryDao=ContextProvider.getContext().getBean(EnquiryDao.class);
	UniqueIdentifierGenerator identifierGenerator=UniqueIdentifierFactory.getGenerator();


	private static EnquiryManagerImpl instance=null;
	public static synchronized EnquiryManagerImpl getInstance()
	{
		if(instance == null){
			instance=new EnquiryManagerImpl();
		}
		
		return instance;
	}
	
	private EnquiryManagerImpl() {
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

	public Long updateInquiry(AdmissionEnquiry admissionInquiry) {

		Long enquiryId = admissionInquiry.getEnquiryId();

		inquiryDao.updateInquiry(admissionInquiry);

		return enquiryId;
	}

	public void deleteInquiry(Long inquiryId) {

		inquiryDao.deleteInquiry(inquiryId);;
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		return inquiryDao.searchInqByCriteria(searchCriteria);
	}

	@Override
	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {

		return inquiryDao.getInquiryByTaskDate(taskDate);
	}

}
