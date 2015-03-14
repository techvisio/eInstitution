package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.util.AppConstants.AdmissionWorkFlowStatus;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

public class AdmissionWorkflowManagerImpl implements AdmissionWorkflowManager{

	AdmissionManager admissionManager=AdmissionManagerImpl.getInstance();
	FeeManager feeManager=FeeManagerImpl.getInstance();
	
	public String addStudentDetails(StudentDetail studentDetail) {
		
		ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();
		ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();
	
		String fileNo = admissionManager.addStudentDtl(studentDetail);
		
		ConsultantDetail consultantDetail = studentDetail.getConsultantDetail();
		consultantDetail.setFileNo(fileNo);
		consultantWorkflowManager.addConsultantDtl(consultantDetail);
        
		ScholarshipDetail scholarshipDetail = studentDetail.getScholarshipDetail();
		scholarshipDetail.setFileNo(fileNo);
		scholarshipWorkflowManager.addScholarDetail(scholarshipDetail);
	
		
		return fileNo;
	}

	public String updateStudentDetails(StudentDetail studentDetail) {
		
		return admissionManager.updateStudentDtl(studentDetail);
	}

	public StudentDetail getStudentDetails(String fileNo) {

	ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();	
	ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();	
	StudentDetail studentDetail = admissionManager.getStudentDtl(fileNo);
	
	ScholarshipDetail scholarshipDetail = scholarshipWorkflowManager.getScholarshipDetail(fileNo);
	studentDetail.setScholarshipDetail(scholarshipDetail);
	
	ConsultantDetail consultantDetail = consultantWorkflowManager.getConsultantDtl(fileNo);
	studentDetail.setConsultantDetail(consultantDetail);
	
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

	public StudentDetail getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		StudentDetail studentDetail = admissionManager.getStudentDtlBySearchCriteria(searchCriteria);
	      	
		String fileNo = studentDetail.getFileNo();
		
		studentDetail=getStudentDetails(fileNo);
	
		return studentDetail;
	}

	public StudentBasicInfo getStudentBsInfo(String fileNo) {
		StudentBasicInfo info = admissionManager.getStudentBsInfo(fileNo); 
		return info;
	}

	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit) {
		List<StudentBasicInfo> basicInfos = admissionManager.getLatestAdmissionInfo(limit);
		return basicInfos;
	}
	
	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit) {
		List<StudentBasicInfo> basicInfos = admissionManager.getUnapprovedAdmissions(limit);
		return basicInfos;
	}
}
