package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantManager {

	public List<ConsultantDetail> getConsultantDtl(String fileNo);
	public void saveConsultant(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails);
	
	
}
