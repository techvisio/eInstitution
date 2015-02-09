package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;

public class AdmissionWorkflowManagerImpl implements AdmissionWorkflowManager{

	AdmissionManager manager=AdmissionManagerImpl.getInstance();
	
	public String addStudentDetails(StudentDetail studentDetail) {
		return manager.addStudentDtl(studentDetail);
	}

	public String updateStudentDetails(StudentDetail studentDetail) {
		
		return manager.updateStudentDtl(studentDetail);
	}

	public StudentDetail getStudentDetails(String fileNo) {

	StudentDetail studentDetail = manager.getStudentDtl(fileNo);
		return studentDetail;
	}

	public void deleteStudentDetails(String fileNo){
		
	 manager.deleteSudentDtl(fileNo);
	
	}
}
