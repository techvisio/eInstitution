package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.ContextProvider;

public class ConsultantManagerImpl implements ConsultantManager {

	ConsultantDao consultantDao = ContextProvider.getContext().getBean(
			ConsultantDao.class);

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
	
	
	public List<ConsultantDetail> getConsultantDtl(String fileNo) {

	List<ConsultantDetail> consultantDetails = null;
		consultantDetails = consultantDao.getConsultantDtl(fileNo);
	
		return consultantDetails;
	}

	public void saveConsultant(List<ConsultantDetail> consultantDetails){

		consultantDao.saveConsultant(consultantDetails);
	}

	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails) {

		consultantDao.deleteConsultantDtl(consultantDetails);
	}

}
