package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.util.AppConstants.AdmissionWorkFlowStatus;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;

public class AdmissionWorkflowManagerImpl implements AdmissionWorkflowManager{

	AdmissionManager admissionManager=AdmissionManagerImpl.getInstance();
	FeeManager feeManager=FeeManagerImpl.getInstance();
	
	public String addStudentDetails(StudentDetail studentDetail) {
		return admissionManager.addStudentDtl(studentDetail);
	}

	public String updateStudentDetails(StudentDetail studentDetail) {
		
		return admissionManager.updateStudentDtl(studentDetail);
	}

	public StudentDetail getStudentDetails(String fileNo) {

	StudentDetail studentDetail = admissionManager.getStudentDtl(fileNo);
		return studentDetail;
	}

	public void deleteStudentDetails(String fileNo){
		
	 admissionManager.deleteSudentDtl(fileNo);
	
	}

	public void proceedToNextStep(String workFlow, String fileNo) {
		if(workFlow !=null){
			AdmissionWorkFlowStatus workFlowStatus=AdmissionWorkFlowStatus.valueOf(workFlow);
			if(AdmissionWorkFlowStatus.FEE_NEGOTIATED.equals(workFlowStatus)){
				feeManager.generateStudentFeeStaging(fileNo);
			}
		}		
	}
}
