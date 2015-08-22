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
	public Long saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl) {
		Long fileNo=consultantDao.saveAdmissionConsultantDtl(admissnConsltntDtl);
		return fileNo;
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
	
	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		logger.info("{} : calling saveConsultantDetail method for Student:{} ",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());
		StudentBasicInfo basicInfo = consultantAdmissionDetail.getBasicInfo();
		List<AdmissnConsltntDtl> consultantDetails = consultantAdmissionDetail.getConsultantDetails();
	    saveAdmissionConsultantDtl(consultantDetails, basicInfo.getFileNo());  
	}
	
	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria method for student:{}",this.getClass().getName(), searchCriteria.getFirstName());	
		List<StudentBasicInfo> studentBasicInfos = null;
		studentBasicInfos=consultantDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentBasicInfos;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
		Long consultantId=consultantDao.saveConsultant(consultant);
		return consultantId;
	}
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		Consultant consultant = consultantDao.getConsultant(consultantId);
		return consultant;
	}


	@Override
	public List<Consultant> getConsultantBySearchCriteria(
			SearchCriteria searchCriteria) {
		List<Consultant> consultants = consultantDao.getConsultantBySearchCriteria(searchCriteria);
		return consultants;
	}


	@Override
	public AdmissnConsltntDtl getAdmissionConsltntDtl(Long fileNo) {
        AdmissnConsltntDtl admissnConsltntDtl = consultantDao.getAdmissionConsltntDtl(fileNo); 	
		return admissnConsltntDtl;
	}
	}
