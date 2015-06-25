package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
@Component
public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(ConsultantWorkflowManagerImpl.class);
	private static final int ConsultantDetail = 0;
	
	@Autowired
	ConsultantManager consultantManager;

	@Override
	public void saveAdmissionConsultantDtl(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {
		consultantManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
	}

	@Override
	public void saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl) {
        consultantManager.saveAdmissionConsultantDtl(admissnConsltntDtl); 		
	}

	@Override
	public void deleteAdmissionConsultantDtlExclusion(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {
		consultantManager.deleteAdmissionConsultantDtlExclusion(admissnConsltntDtls, fileNo);
	}

	@Override
	public void saveConsultantPayment(List<ConsultantPayment> consultantPayments, Long fileNo) {
		consultantManager.saveConsultantPayment(consultantPayments, fileNo);
	}

	@Override
	public void saveConsultantPayment(ConsultantPayment consultantPayment) {
		consultantManager.saveConsultantPayment(consultantPayment);
	}

	@Override
	public void deleteConsultantPaymentExclusion(List<ConsultantPayment> consultantPayments, Long fileNo) {
		consultantManager.deleteConsultantPaymentExclusion(consultantPayments, fileNo);
	}

	@Override
	public void saveConsultantPaymentCriteria(List<ConsultantPaymentCriteria> consultantPaymentCriterias,Long fileNo) {
		consultantManager.saveConsultantPaymentCriteria(consultantPaymentCriterias, fileNo);
	}

	@Override
	public void saveConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria) {
		consultantManager.saveConsultantPaymentCriteria(consultantPaymentCriteria);
	}

	@Override
	public void deleteConsultantPaymentCriteriaExclusion(List<ConsultantPaymentCriteria> consultantPaymentCriterias,Long fileNo) {
		consultantManager.deleteConsultantPaymentCriteriaExclusion(consultantPaymentCriterias, fileNo);
	}

	@Override
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo) {
		List<AdmissnConsltntDtl> admissnConsltntDtls = consultantManager.getAdmissnConsltntDtl(fileNo);
		return admissnConsltntDtls;
	}

	@Override
	public List<ConsultantPayment> getConsultantPayment(Long fileNo) {
		List<ConsultantPayment> consultantPayments = consultantManager.getConsultantPayment(fileNo);
		return consultantPayments;
	}

	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(
			Long fileNo) {
		List<ConsultantPaymentCriteria> consultantPaymentCriterias = consultantManager.getConsultantPaymentCriteria(fileNo);
		return consultantPaymentCriterias;
	}

}
