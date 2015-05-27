package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
public interface ConsultantManager {
	public Consultant getConsultant(Long consultantId);
	public Long saveConsultant(Consultant consultant);
	public void deleteConsultant(Long consultantId);
	
	

	public List<ConsultantDetail> getConsultantDtl(Long fileNo);
	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails);
	public void deleteConsultantDtl(Long fileNo, List<ConsultantDetail> consultantDetails);
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria);
	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail);
	
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
}
