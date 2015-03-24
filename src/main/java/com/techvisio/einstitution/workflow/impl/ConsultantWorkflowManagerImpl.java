package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;

public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{

	ConsultantManager manager=ConsultantManagerImpl.getInstance();
	
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
