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
@Component
public class ConsultantManagerImpl implements ConsultantManager {
	
	@Autowired
	ConsultantDao consultantDao ;
	
	@Autowired
	UniqueIdentifierGenerator identifierGenerator;
	
	private static ConsultantManagerImpl instance=null;
	public static synchronized ConsultantManagerImpl getInstance()
	{
		if(instance == null){
			instance=new ConsultantManagerImpl();
		}
		
		return instance;
	}
	
	private ConsultantManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		Consultant consultant = consultantDao.getConsultant(consultantId);
		return consultant;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
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
		consultantDao.deleteConsultant(consultantId);
	}

	
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo) {

	List<ConsultantDetail> consultantDetails = null;
		consultantDetails = consultantDao.getConsultantDtl(fileNo);
	
		return consultantDetails;
	}

	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails){

		consultantDao.saveConsultantDetail(consultantDetails);
	}

	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails) {

		consultantDao.deleteConsultantDtlExclusion(fileNo, consultantDetails);
	}

	@Override
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria) {

		List<Consultant> consultants = null;
		consultants=consultantDao.getConsultantBySearchCriteria(searchCriteria);
		
		return consultants;
	}

	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		
		List<ConsultantDetail> consultantDetails = consultantAdmissionDetail.getConsultantDetails();
	    saveConsultantDetail(consultantDetails);  
	}
	
}
