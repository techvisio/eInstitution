package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;


public interface ConsultantDao {

	public Consultant getConsultant(Long consultantId);
	public void addConsultant(Consultant consultant);
	public void updateConsultant(Consultant consultant);
	public void deleteConsultant(String fileNo);
	
	
	public List<ConsultantDetail> getConsultantDtl(String fileNo);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails);
	
	
	List<ConsultantPaymentDtl> getConsultantPaymentDtl(String fileNo,Long consultantId);
	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void deleteConsultantPaymentDtl(String fileNo, Long consultantId);
	
	
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(String fileNo, Long consultantId);
	public void addConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void updateConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void deleteConsultantPaymentCriteria(String fileNo, Long consultantId);
}
