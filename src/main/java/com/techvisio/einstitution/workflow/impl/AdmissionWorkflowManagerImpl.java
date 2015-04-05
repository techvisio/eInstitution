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
	
	public Long addStudentDetails(StudentDetail studentDetail) {
		
		ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();
		ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();
	
		Long fileNo = admissionManager.addStudentDtl(studentDetail);
		
		if(studentDetail.getConsultantDetail() !=null){
		List<ConsultantDetail> consultantDetails = studentDetail.getConsultantDetail();
		if(consultantDetails != null){
		
			for(ConsultantDetail consultantDetail : consultantDetails){
			consultantDetail.setFileNo(fileNo);
			}
		}
	    consultantWorkflowManager.saveConsultant(consultantDetails);
		}
		
		if(studentDetail.getScholarshipDetail() != null){
		ScholarshipDetail scholarshipDetail = studentDetail.getScholarshipDetail();
		scholarshipDetail.setFileNo(fileNo);
		scholarshipWorkflowManager.addScholarDetail(scholarshipDetail);
		}
		
		return fileNo;
	}

	public Long updateStudentDetails(StudentDetail studentDetail) {
	
		ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();
		ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();
	
		Long fileNo = admissionManager.updateStudentDtl(studentDetail);
		
		if(studentDetail.getConsultantDetail() !=null){
		List<ConsultantDetail >consultantDetails = studentDetail.getConsultantDetail();
		if(consultantDetails != null){
		for(ConsultantDetail consultantDetail : consultantDetails){	
		
		consultantDetail.setFileNo(fileNo);
		}
		}
		consultantWorkflowManager.saveConsultant(consultantDetails);
		}
		
		if(studentDetail.getScholarshipDetail() != null){
		ScholarshipDetail scholarshipDetail = studentDetail.getScholarshipDetail();
		scholarshipDetail.setFileNo(fileNo);
		scholarshipWorkflowManager.addScholarDetail(scholarshipDetail);
		}
		else
		{
			scholarshipWorkflowManager.deleteScholarshipDetail(fileNo);
		}
		
		return fileNo;
	}

	public StudentDetail getStudentDetails(Long fileNo) {

	ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();	
	ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();	
	StudentDetail studentDetail = admissionManager.getStudentDtl(fileNo);

	
	ScholarshipDetail scholarshipDetail = scholarshipWorkflowManager.getScholarshipDetail(fileNo);
	studentDetail.setScholarshipDetail(scholarshipDetail);
	
	List<ConsultantDetail> consultantDetails = consultantWorkflowManager.getConsultantDtl(fileNo);
	studentDetail.setConsultantDetail(consultantDetails);
	
	return studentDetail;
	
	
	}

	public void deleteStudentDetails(Long fileNo){
		
	 admissionManager.deleteSudentDtl(fileNo);
	
	}

	public void proceedToNextStep(String workFlow, Long fileNo) {
		if(workFlow !=null){
			AdmissionWorkFlowStatus workFlowStatus=AdmissionWorkFlowStatus.valueOf(workFlow);
			if(AdmissionWorkFlowStatus.FEE_NEGOTIATED.equals(workFlowStatus)){
				feeManager.generateStudentFeeStaging(fileNo);
			}
		}		
	}

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		List<StudentBasicInfo> studentBasicInfos = admissionManager.getStudentDtlBySearchCriteria(searchCriteria);
		return studentBasicInfos;
	}

	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
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
