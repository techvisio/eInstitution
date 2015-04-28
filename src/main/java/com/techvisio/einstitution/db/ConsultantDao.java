package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.beans.SearchCriteria;


public interface ConsultantDao {

	public Consultant getConsultant(Long consultantId);
	public void saveConsultant(Consultant consultant);
	//public void updateConsultant(Consultant consultant);
	public void deleteConsultant(Long consultantId);
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo);
	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtlExclusion(Long fileNo, List<ConsultantDetail> consultantDetails);
	
	
	public List<ConsultantPaymentDtl> getConsultantPaymentDtl(Long fileNo,Long consultantId);
	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	
	
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo, Long consultantId);
	public void addConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void updateConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria);
	public void deleteConsultantPaymentCriteriaExclusion(Long fileNo,
			List<Long> consultantIds);
}
