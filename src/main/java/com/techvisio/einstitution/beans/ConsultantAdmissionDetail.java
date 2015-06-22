package com.techvisio.einstitution.beans;

import java.util.List;

public class ConsultantAdmissionDetail {

	private List<AdmissnConsltntDtl> consultantDetails;
	private StudentBasicInfo basicInfo;
	
	public List<AdmissnConsltntDtl> getConsultantDetails() {
		return consultantDetails;
	}
	public void setConsultantDetails(List<AdmissnConsltntDtl> consultantDetails) {
		this.consultantDetails = consultantDetails;
	}
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
}
