package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.SearchCriteria;

import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionWorkflowManager {

	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public Long addStudentDetails(StudentDetail studentDetail);
	public Long updateStudentDetails(StudentDetail studentDetail);
	public StudentDetail getStudentDetails(Long fileNo);
	public void deleteStudentDetails(Long fileNo);
	//public void updateWorkflowStatus(String fileNo);
	public void proceedToNextStep(String workFlow,Long FileNo);
	
	public StudentBasicInfo getStudentBsInfo(Long fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);
}
