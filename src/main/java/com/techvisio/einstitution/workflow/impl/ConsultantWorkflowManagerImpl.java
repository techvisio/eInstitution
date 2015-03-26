package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;

public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{

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

	
	
	
	public List<ConsultantDetail> getConsultantDtl(String fileNo) {

		List<ConsultantDetail> consultantDetails = manager.getConsultantDtl(fileNo);
		
		return consultantDetails;
	}

	public void saveConsultant(List<ConsultantDetail> consultantDetails) {
           
		manager.saveConsultant(consultantDetails);
	}

	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails) {

		manager.deleteConsultantDtl(consultantDetails);
	}

	
}
