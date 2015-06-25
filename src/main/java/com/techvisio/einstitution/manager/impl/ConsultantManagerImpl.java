package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.ConsultantManager;
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


	@Override
	public void saveAdmissionConsultantDtl(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {
		consultantDao.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
	}


	@Override
	public void saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl) {
		consultantDao.saveAdmissionConsultantDtl(admissnConsltntDtl);
	}


	@Override
	public void deleteAdmissionConsultantDtlExclusion(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {
		consultantDao.deleteAdmissionConsultantDtlExclusion(admissnConsltntDtls, fileNo);
	}

	@Override
	public void saveConsultantPayment(List<ConsultantPayment> consultantPayments, Long fileNo) {
		consultantDao.saveConsultantPayment(consultantPayments, fileNo);
	}


	@Override
	public void saveConsultantPayment(ConsultantPayment consultantPayment) {
		consultantDao.saveConsultantPayment(consultantPayment);
	}


	@Override
	public void deleteConsultantPaymentExclusion(List<ConsultantPayment> consultantPayments, Long fileNo) {

		consultantDao.deleteConsultantPaymentExclusion(consultantPayments, fileNo);
	}


	@Override
	public void saveConsultantPaymentCriteria(List<ConsultantPaymentCriteria> consultantPaymentCriterias,Long fileNo) {
		consultantDao.saveConsultantPaymentCriteria(consultantPaymentCriterias, fileNo);
	}

	@Override
	public void saveConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria) {
		consultantDao.saveConsultantPaymentCriteria(consultantPaymentCriteria);
	}


	@Override
	public void deleteConsultantPaymentCriteriaExclusion(List<ConsultantPaymentCriteria> consultantPaymentCriterias,Long fileNo) {
		consultantDao.deleteConsultantPaymentCriteriaExclusion(consultantPaymentCriterias, fileNo);
	}


	@Override
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo) {
		List<AdmissnConsltntDtl> admissnConsltntDtls = consultantDao.getAdmissnConsltntDtl(fileNo);
		return admissnConsltntDtls;
	}


	@Override
	public List<ConsultantPayment> getConsultantPayment(Long fileNo) {
		List<ConsultantPayment> consultantPayments = consultantDao.getConsultantPayment(fileNo);
		return consultantPayments;
	}


	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(
			Long fileNo) {
		List<ConsultantPaymentCriteria> consultantPaymentCriterias = consultantDao.getConsultantPaymentCriteria(fileNo);
		return consultantPaymentCriterias;
	}
	

	
}
