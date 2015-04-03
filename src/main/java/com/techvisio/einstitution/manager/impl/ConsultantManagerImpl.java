package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.ContextProvider;

public class ConsultantManagerImpl implements ConsultantManager {

	ConsultantDao consultantDao = ContextProvider.getContext().getBean(
			ConsultantDao.class);
	UniqueIdentifierGenerator identifierGenerator = new UniqueIdentifierFactory().getGenerator();

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

	public void saveConsultant(List<ConsultantDetail> consultantDetails){

		consultantDao.saveConsultant(consultantDetails);
	}

	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails) {

		consultantDao.deleteConsultantDtl(fileNo, consultantDetails);
	}

	
}
