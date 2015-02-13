package com.techvisio.einstitution.manager.impl;

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
	
	
	public ConsultantDetail getConsultantDtl(String fileNo) {

		ConsultantDetail consultantDetail = null;
		consultantDetail = consultantDao.getConsultantDtl(fileNo);
	
		return consultantDetail;
	}

	public void addConsultantDtl(ConsultantDetail consultantDetail){

		consultantDao.addConsultantDtl(consultantDetail);
	}

	public void updateConsultantDtl(ConsultantDetail consultantDetail) {

		consultantDao.updateConsultantDtl(consultantDetail);
	}

	public void deleteConsultantDtl(String fileNo) {

		consultantDao.deleteConsultantDtl(fileNo);;
	}

}
