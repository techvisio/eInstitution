package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionWorkflowManager {

	public String addStudentDetails(StudentDetail studentDetail);
	public String updateStudentDetails(StudentDetail studentDetail);
	public StudentDetail getStudentDetails(String fileNo);
	public void deleteStudentDetails(String fileNo);
	//public void updateWorkflowStatus(String fileNo)
}
