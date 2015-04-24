package com.techvisio.einstitution.beans;

import java.util.List;

public class ConsultantAdmissionDetail {

	private List<ConsultantDetail> consultantDetails;
	private StudentBasicInfo basicInfo;
	
	public List<ConsultantDetail> getConsultantDetails() {
		return consultantDetails;
	}
	public void setConsultantDetails(List<ConsultantDetail> consultantDetails) {
		this.consultantDetails = consultantDetails;
	}
	public StudentBasicInfo getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(StudentBasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
}
