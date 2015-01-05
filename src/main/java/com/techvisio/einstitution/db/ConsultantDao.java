package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;


public interface ConsultantDao {

	public Consultant getConsultant(Long consultantId);
	public void addConsultant(Consultant consultant);
	public void updateConsultant(Consultant consultant);
	public void deleteConsultant(String fileNo);
	
	
	public ConsultantDetail getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void updateConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(String fileNo);
	
	
	List<ConsultantPaymentDtl> getConsultantPaymentDtl(String fileNo);
	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void deleteConsultantPaymentDtl(String fileNo);
	
	
	
}
