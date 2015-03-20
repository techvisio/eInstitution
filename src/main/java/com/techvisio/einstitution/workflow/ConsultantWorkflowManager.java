
package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantWorkflowManager {

	public ConsultantDetail getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(String fileNo);
	
}
