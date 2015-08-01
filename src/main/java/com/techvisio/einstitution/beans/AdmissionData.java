package com.techvisio.einstitution.beans;

import java.util.List;

public class AdmissionData {

	private Student student;
	private List<Workflow> workflows;
	private List<StudentActivity> previousActivities;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Workflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<Workflow> workflows) {
		this.workflows = workflows;
	}
	public List<StudentActivity> getPreviousActivities() {
		return previousActivities;
	}
	public void setPreviousActivities(List<StudentActivity> previousActivities) {
		this.previousActivities = previousActivities;
	}
	
}
