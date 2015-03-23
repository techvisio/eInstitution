
package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantWorkflowManager {

	public List<ConsultantDetail> getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(String fileNo);
    	
}
