package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
@Component
@Transactional
public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(ConsultantWorkflowManagerImpl.class);
	private static final int ConsultantDetail = 0;
	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager;

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

	@Override
	public ConsultantAdmissionDetail getConsultantAdmissionDetail(Long fileNo){
		logger.info("{} : getConsultantAdmissionDetail passing fileno:{}",this.getClass().getName(), fileNo);		
		ConsultantAdmissionDetail consultantAdmissionDetail=new ConsultantAdmissionDetail();


		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		consultantAdmissionDetail.setBasicInfo(basicInfo);

		List<AdmissnConsltntDtl> consultantDetails = getAdmissnConsltntDtl(fileNo);
		consultantAdmissionDetail.setConsultantDetails(consultantDetails);

		return consultantAdmissionDetail;
	}

	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		logger.info("{} :calling saveConsultantAdmissionDetail for Student:{} ",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());
		consultantManager.saveConsultantAdmissionDetail(consultantAdmissionDetail);
	}

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria for Student:{}",this.getClass().getName(), searchCriteria.getFirstName());		
		List<StudentBasicInfo> studentBasicInfos = consultantManager.getStudentDtlBySearchCriteria(searchCriteria);
		return studentBasicInfos;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
		Long consultantId=consultantManager.saveConsultant(consultant);
		return consultantId;
	}

	@Override
	public Consultant getConsultant(Long consultantId) {
		Consultant consultant = consultantManager.getConsultant(consultantId);
		return consultant;
	}

	@Override
	public List<Consultant> getConsultantBySearchCriteria(
			SearchCriteria searchCriteria) {
		List<Consultant> consultants = consultantManager.getConsultantBySearchCriteria(searchCriteria);
		return consultants;
	}
}
