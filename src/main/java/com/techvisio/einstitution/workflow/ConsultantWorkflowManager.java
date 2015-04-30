
package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
@Component
public interface ConsultantWorkflowManager {
	
	public Consultant getConsultant(Long consultantId);
	public Long saveConsultant(Consultant consultant);
	public void deleteConsultant(Long consultantId);
	

	public List<ConsultantDetail> getConsultantDtl(Long fileNo);
	public void saveConsultant(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails);
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria);
	public ConsultantAdmissionDetail getConsultantAdmissionDetail(Long fileNo);
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail);
    	
}
