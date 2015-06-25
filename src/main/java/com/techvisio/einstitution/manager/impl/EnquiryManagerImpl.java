package com.techvisio.einstitution.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class EnquiryManagerImpl implements EnquiryManager {
//	private static CustomLogger logger = CustomLogger.getLogger(EnquiryManagerImpl.class);
//	@Autowired
//	EnquiryDao inquiryDao;
//	
//	@Autowired
//	UniqueIdentifierGenerator identifierGenerator;
//
//
//	private static EnquiryManagerImpl instance=null;
//	public static synchronized EnquiryManagerImpl getInstance()
//	{
//		if(instance == null){
//			instance=new EnquiryManagerImpl();
//		}
//		
//		return instance;
//	}
//	
//	private EnquiryManagerImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	public AdmissionEnquiry getInquiry(Long inquiryId) {
//		logger.info("{} : calling getInquiry method by passing enquiryId:{} ",this.getClass().getName(), inquiryId);
//		AdmissionEnquiry admissionInquiry=null;
//		
//		admissionInquiry=inquiryDao.getInquiry(inquiryId);
//		
//		return admissionInquiry;
//	}
//
//	public Long addInquiry (AdmissionEnquiry admissionInquiry) {
//		logger.info("{} : calling addInquiry method for inquiryId:{} ",this.getClass().getName(), admissionInquiry.getEnquiryId());
//		Long enquiryId=null;
//		if(admissionInquiry!=null){
//		 enquiryId=identifierGenerator.getUniqueIdentifierForEnquiry();
//		admissionInquiry.setEnquiryId(enquiryId);
//		inquiryDao.addInquiry(admissionInquiry);
//		}
//		return enquiryId;
//	}
//
//	public Long updateInquiry(AdmissionEnquiry admissionInquiry) {
//		logger.info("{} : calling updateInquiry method for inquiryId:{} ",this.getClass().getName(), admissionInquiry.getEnquiryId());
//		Long enquiryId = admissionInquiry.getEnquiryId();
//
//		inquiryDao.updateInquiry(admissionInquiry);
//
//		return enquiryId;
//	}
//
//	public void deleteInquiry(Long inquiryId) {
//		logger.info("{} : calling deleteInquiry method by passing enquiryId:{} ",this.getClass().getName(), inquiryId);
//		inquiryDao.deleteInquiry(inquiryId);;
//	}
//
//	@Override
//	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
//		logger.info("{} : calling searchInqByCriteria method for enquiryId:{} ",this.getClass().getName(), searchCriteria.getInquryId());
//		return inquiryDao.searchInqByCriteria(searchCriteria);
//	}
//
//	@Override
//	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {
//		logger.info("{} : calling getInquiryByTaskDate method by passing taskDate:{} ",this.getClass().getName(), taskDate);
//		return inquiryDao.getInquiryByTaskDate(taskDate);
//	}

}
