package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;


public interface ConsultantDao {

	public void getConsultant(Consultant consultantMasterData);
	public void addConsultant(Consultant consultantMasterData);
	public void updateConsultant(Consultant consultantMasterData);
	public void deleteConsultant(Consultant consultantMasterData);
	
	
	public void getConsultantDtl(ConsultantDetail consultantDetail);
	public void addConsultantDtl(ConsultantDetail consultantDetail);
	public void updateConsultantDtl(ConsultantDetail consultantDetail);
	public void deleteConsultantDtl(ConsultantDetail consultantDetail);
	
	
	public void getConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	public void deleteConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl);
	
	
	
}
