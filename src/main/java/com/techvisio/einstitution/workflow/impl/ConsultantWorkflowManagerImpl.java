package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
@Component
public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(ConsultantWorkflowManagerImpl.class);
	private static final int ConsultantDetail = 0;
	
	@Autowired
	ConsultantManager consultantManager;
	
	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager ;

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria for Student:{}",this.getClass().getName(), searchCriteria.getFirstName());		
		List<StudentBasicInfo> studentBasicInfos = consultantManager.getStudentDtlBySearchCriteria(searchCriteria);
		return studentBasicInfos;
	}
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		logger.info("{} : calling getConsultant by passing consultantId:{}",this.getClass().getName(), consultantId);
		Consultant consultant = consultantManager.getConsultant(consultantId);
		return consultant;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
		logger.info("{} : calling saveConsultant for consltant:{}",this.getClass().getName(), consultant.getName());		
		Long consultantId = consultantManager.saveConsultant(consultant);
		consultant.setConsultantId(consultantId);
		return consultantId;
	}

	@Override
	public void deleteConsultant(Long consultantId) {
		logger.info("{} :calling deleteConsultant by passing consultantId:{}",this.getClass().getName(), consultantId);
		consultantManager.deleteConsultant(consultantId);
	}

	
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo) {
		logger.info("{} :calling getConsultantDtl by passing fileNo:{}",this.getClass().getName(), fileNo);
		
		List<ConsultantDetail> consultantDetails = consultantManager.getConsultantDtl(fileNo);
		
		return consultantDetails;
	}

	public void saveConsultant(List<ConsultantDetail> consultantDetails) {
		logger.info("{} :calling saveConsultantDetail by passing consultantDetails:{}",this.getClass().getName(), consultantDetails);           
		consultantManager.saveConsultantDetail(consultantDetails);
	}

	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails) {
		logger.info("{} :calling deleteConsultantDtl by passing fileno:{} and consultantDetails:{}",this.getClass().getName(), fileNo,consultantDetails);
		consultantManager.deleteConsultantDtl(fileNo, consultantDetails);
	}

	@Override
	public List<Consultant> getConsultantBySearchCriteria(
			SearchCriteria searchCriteria) {
		logger.info("{} :calling getConsultantBySearchCriteria for consultant:{} and consultantDetails:{}",this.getClass().getName(), searchCriteria.getFirstName());		
		List<Consultant> consultants = consultantManager.getConsultantBySearchCriteria(searchCriteria);
		return consultants;
	}

	@Override
	public ConsultantAdmissionDetail getConsultantAdmissionDetail(Long fileNo){
		logger.info("{} : getConsultantAdmissionDetail passing fileno:{}",this.getClass().getName(), fileNo);		
        ConsultantAdmissionDetail consultantAdmissionDetail=new ConsultantAdmissionDetail();
        
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		consultantAdmissionDetail.setBasicInfo(basicInfo);
		
		List<ConsultantDetail> consultantDetails = getConsultantDtl(fileNo);
		consultantAdmissionDetail.setConsultantDetails(consultantDetails);
		
		return consultantAdmissionDetail;
	}
	
	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		logger.info("{} :calling saveConsultantAdmissionDetail for Student:{} ",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());
		consultantManager.saveConsultantAdmissionDetail(consultantAdmissionDetail);
	}
}
