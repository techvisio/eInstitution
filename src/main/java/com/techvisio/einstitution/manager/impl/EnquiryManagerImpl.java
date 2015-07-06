package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
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
	public void saveInquiry (AdmissionEnquiry admissionInquiry) {
		inquiryDao.saveInquiry(admissionInquiry);
	}

	}
