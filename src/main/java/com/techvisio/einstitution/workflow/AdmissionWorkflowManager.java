package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionWorkflowManager {

	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public String addStudentDetails(StudentDetail studentDetail);
	public String updateStudentDetails(StudentDetail studentDetail);
	public StudentDetail getStudentDetails(String fileNo);
	public void deleteStudentDetails(String fileNo);
	//public void updateWorkflowStatus(String fileNo);
	public void proceedToNextStep(String workFlow,String FileNo);
}
