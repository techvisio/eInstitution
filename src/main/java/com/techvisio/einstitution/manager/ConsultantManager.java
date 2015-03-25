package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantManager {
	public Consultant getConsultant(Long consultantId);
	public void saveConsultant(Consultant consultant);
	public void deleteConsultant(Long consultantId);
	
	

	public List<ConsultantDetail> getConsultantDtl(String fileNo);
	public void saveConsultant(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails);
	
	
}
