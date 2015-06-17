package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;

@Component
public interface ConsultantDao {

	public Consultant getConsultant(Long consultantId);
	public void saveConsultant(Consultant consultant);
	//public void updateConsultant(Consultant consultant);
	public void deleteConsultant(Long consultantId);
	
	
	public List<ConsultantDetail> getConsultantDtl(Long fileNo);
	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtlExclusion(Long fileNo, List<ConsultantDetail> consultantDetails);
	
	
	public List<ConsultantPayment> getConsultantPaymentDtl(Long fileNo,Long consultantId);
	public void addConsultantPaymentDtl(ConsultantPayment consultantPaymentDtl);
	public void updateConsultantPaymentDtl(ConsultantPayment consultantPaymentDtl);
	
	
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo, Long consultantId);
	public void addConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public void updateConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria);
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria);
	public void deleteConsultantPaymentCriteriaExclusion(Long fileNo,List<Long> consultantIds);
	
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
}
