package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.ContextProvider;

public class ConsultantManagerImpl implements ConsultantManager {

	ConsultantDao consultantDao = ContextProvider.getContext().getBean(
			ConsultantDao.class);

	public void getConsultantDetail(String fileNo) {

		consultantDao.getConsultantDtl(fileNo);
	}

	public void addConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.addConsultantDtl(consultantDetail);
	}

	public void updateConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.updateConsultantDtl(consultantDetail);
	}

	public void deleteConsultantDetail(String fileNo) {

		consultantDao.deleteConsultantDtl(fileNo);;
	}

}
