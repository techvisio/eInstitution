package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class EnquiryManagerImpl implements EnquiryManager {

	private static CustomLogger logger = CustomLogger.getLogger(EnquiryManagerImpl.class);
	@Autowired
	EnquiryDao inquiryDao;
	
	@Autowired
	UniqueIdentifierGenerator identifierGenerator;


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
		logger.info("{} : calling getInquiry method by passing enquiryId:{} ",this.getClass().getName(), inquiryId);
		AdmissionEnquiry admissionInquiry=null;
		admissionInquiry=inquiryDao.getInquiry(inquiryId);
		return admissionInquiry;
	}

	@Override
	public Long saveInquiry (AdmissionEnquiry admissionInquiry) {
		Long enquiryId=inquiryDao.saveInquiry(admissionInquiry);
		return enquiryId;
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling searchInqByCriteria method for enquiryId:{} ",this.getClass().getName(), searchCriteria.getInquryId());
		return inquiryDao.searchInqByCriteria(searchCriteria);
	}
	}
