package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;

public interface ConsultantManager {

	public List<ConsultantDetail> getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(String fileNo);
	
	
}
