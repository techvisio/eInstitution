package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantManager {

	public ConsultantDetail getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void updateConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(String fileNo);
	
	
}
