package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;

public class ConsultantWorkflowManagerImpl implements ConsultantWorkflowManager{

	ConsultantManager manager=ConsultantManagerImpl.getInstance();
	
	public ConsultantDetail getConsultantDtl(String fileNo) {

		ConsultantDetail consultantDetail = manager.getConsultantDtl(fileNo);
		
		return consultantDetail;
	}

	public void addConsultantDtl(ConsultantDetail consultantDetail) {
           
		manager.addConsultantDtl(consultantDetail);
	}

	public void deleteConsultantDtl(String fileNo) {

		manager.deleteConsultantDtl(fileNo);
	}

}
