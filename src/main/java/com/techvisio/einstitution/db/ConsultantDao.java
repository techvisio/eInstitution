package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;

@Component
public interface ConsultantDao {

	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo);
	public void saveAdmissionConsultantDtl(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo);
	public void saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl);
	public void deleteAdmissionConsultantDtlExclusion(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo);
	
	public List<ConsultantPayment> getConsultantPayment(Long fileNo);
	public void saveConsultantPayment(List<ConsultantPayment> consultantPayments, Long fileNo);
	public void saveConsultantPayment(ConsultantPayment consultantPayment);
	public void deleteConsultantPaymentExclusion(List<ConsultantPayment> consultantPayments, Long fileNo);

	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo);
	public void saveConsultantPaymentCriteria(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo);
	public void saveConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void deleteConsultantPaymentCriteriaExclusion(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo);

}
