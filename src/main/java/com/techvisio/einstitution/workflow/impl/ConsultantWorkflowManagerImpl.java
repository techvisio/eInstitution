package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;

public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{

	private static final int ConsultantDetail = 0;
	ConsultantManager manager=ConsultantManagerImpl.getInstance();
	
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		Consultant consultant = manager.getConsultant(consultantId);
		return consultant;
	}

	@Override
	public Long saveConsultant(Consultant consultant) {
		Long consultantId = manager.saveConsultant(consultant);
		consultant.setConsultantId(consultantId);
		return consultantId;
	}

	@Override
	public void deleteConsultant(Long consultantId) {
		manager.deleteConsultant(consultantId);
	}

	
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo) {

		List<ConsultantDetail> consultantDetails = manager.getConsultantDtl(fileNo);
		
		return consultantDetails;
	}

	public void saveConsultant(List<ConsultantDetail> consultantDetails) {
           
		manager.saveConsultantDetail(consultantDetails);
	}

	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails) {

		manager.deleteConsultantDtl(fileNo, consultantDetails);
	}

	@Override
	public List<Consultant> getConsultantBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		List<Consultant> consultants = manager.getConsultantBySearchCriteria(searchCriteria);
		return consultants;
	}

	@Override
	public ConsultantAdmissionDetail getConsultantAdmissionDetail(Long fileNo){
		
		AdmissionWorkflowManager admissionWorkflowManager = new AdmissionWorkflowManagerImpl();
        ConsultantAdmissionDetail consultantAdmissionDetail=new ConsultantAdmissionDetail();
        
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		consultantAdmissionDetail.setBasicInfo(basicInfo);
		
		List<ConsultantDetail> consultantDetails = getConsultantDtl(fileNo);
		consultantAdmissionDetail.setConsultantDetails(consultantDetails);
		
		return consultantAdmissionDetail;
	}
	
	@Override
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail ){
		manager.saveConsultantAdmissionDetail(consultantAdmissionDetail);
	}
}
