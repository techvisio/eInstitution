package com.techvisio.einstitution.beans;

import java.util.List;

public class EnquiryAndTaskBean {

	private AdmissionEnquiry admissionEnquiry;
	private List<TaskAndFollowUp> tasks;
	
	
	public AdmissionEnquiry getAdmissionInquiry() {
		return admissionEnquiry;
	}
	public void setAdmissionInquiry(AdmissionEnquiry admissionInquiry) {
		this.admissionEnquiry = admissionInquiry;
	}
	public List<TaskAndFollowUp> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskAndFollowUp> tasks) {
		this.tasks = tasks;
	}
	
}
