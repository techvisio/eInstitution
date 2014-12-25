package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.ConsultantManager;

public class ConsultantManagerImpl implements ConsultantManager {

	private ConsultantDao consultantDao;

	public ConsultantDao getConsultantDao() {
		return consultantDao;

	}

	public void setConsultantDao(ConsultantDao consultantDao) {

		this.consultantDao = consultantDao;

	}

	public void getConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.getConsultantDtl(consultantDetail);
	}

	public void addConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.addConsultantDtl(consultantDetail);
	}

	public void updateConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.updateConsultantDtl(consultantDetail);
	}

	public void deleteConsultantDetail(ConsultantDetail consultantDetail) {

		consultantDao.deleteConsultantDtl(consultantDetail);
	}

}
