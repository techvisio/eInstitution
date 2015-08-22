
package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
@Transactional
public interface ConsultantWorkflowManager {
	
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo);
	public AdmissnConsltntDtl getAdmissionConsltntDtl(Long fileNo);
	public void saveAdmissionConsultantDtl(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo);
	public Long saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl);
	public void deleteAdmissionConsultantDtlExclusion(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo);
	
	public List<ConsultantPayment> getConsultantPayment(Long fileNo);
	public void saveConsultantPayment(List<ConsultantPayment> consultantPayments, Long fileNo);
	public void saveConsultantPayment(ConsultantPayment consultantPayment);
	public void deleteConsultantPaymentExclusion(List<ConsultantPayment> consultantPayments, Long fileNo);

	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo);
	public void saveConsultantPaymentCriteria(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo);
	public void saveConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void deleteConsultantPaymentCriteriaExclusion(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo);
	public void saveConsultantAdmissionDetail(
			ConsultantAdmissionDetail consultantAdmissionDetail);
	public ConsultantAdmissionDetail getConsultantAdmissionDetail(Long fileNo);
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	public Long saveConsultant(Consultant consultant);
	public Consultant getConsultant(Long consultantId);
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria);

}
