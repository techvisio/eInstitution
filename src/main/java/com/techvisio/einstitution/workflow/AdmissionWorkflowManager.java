package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionWorkflowManager {

	public String addStudentDetails(StudentDetail studentDetail);
	public String updateStudentDetails(StudentDetail studentDetail);
	public StudentDetail getStudentDetails(String fileNo);
	public void deleteStudentDetails(String fileNo);
	//public void updateWorkflowStatus(String fileNo);
	public void proceedToNextStep(String workFlow,String FileNo);
	
	public StudentBasicInfo getStudentBsInfo(String fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);
}
