package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class ConsultantManagerImpl implements ConsultantManager {
	private static CustomLogger logger = CustomLogger.getLogger(CacheManagerImpl.class);
	@Autowired
	ConsultantDao consultantDao ;
	
	@Autowired
	UniqueIdentifierGenerator identifierGenerator;

	
	public ConsultantManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria method for student:{}",this.getClass().getName(), searchCriteria.getFirstName());	
		List<StudentBasicInfo> studentBasicInfos = null;
		studentBasicInfos=consultantDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentBasicInfos;
	}
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		logger.info("{} : calling getConsultant method by consultant id:{} ",this.getClass().getName(), consultantId);
		Consultant consultant = consultantDao.getConsultant(consultantId);
		return consultant;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
		logger.info("{} : calling saveConsultant method by consultant id:{} ",this.getClass().getName(), consultant.getConsultantId());
		Long consultantId = consultant.getConsultantId();
		if(consultantId == null){
			consultantId = identifierGenerator.getUniqueIdentifierForConsultant(); 
		}
		consultant.setConsultantId(consultantId);
		consultantDao.saveConsultant(consultant);
		return consultantId;
	}

	@Override
	public void deleteConsultant(Long consultantId) {
		logger.info("{} : calling deleteConsultant method by passing consultant id:{} ",this.getClass().getName(), consultantId);
		consultantDao.deleteConsultant(consultantId);
	}

	
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo) {
		logger.info("{} : calling getConsultantDtl method by passing file no:{} ",this.getClass().getName(), fileNo);
	List<ConsultantDetail> consultantDetails = null;
		consultantDetails = consultantDao.getConsultantDtl(fileNo);
	
		return consultantDetails;
	}

	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails){
		logger.info("{} : calling saveConsultantDetail method  ",this.getClass().getName());
		consultantDao.saveConsultantDetail(consultantDetails);
	}

	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails) {
		logger.info("{} : calling deleteConsultantDtlExclusion method by passing file no:{} and consultantDetails:{} ",this.getClass().getName(), fileNo, consultantDetails);
		consultantDao.deleteConsultantDtlExclusion(fileNo, consultantDetails);
	}

	@Override
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getConsultantBySearchCriteria method for name:{} ",this.getClass().getName(), searchCriteria.getFirstName());
		List<Consultant> consultants = null;
		consultants=consultantDao.getConsultantBySearchCriteria(searchCriteria);
		
		return consultants;
	}

	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		logger.info("{} : calling saveConsultantDetail method for Student:{} ",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());		
		List<ConsultantDetail> consultantDetails = consultantAdmissionDetail.getConsultantDetails();
	    saveConsultantDetail(consultantDetails);  
	}
	
}
