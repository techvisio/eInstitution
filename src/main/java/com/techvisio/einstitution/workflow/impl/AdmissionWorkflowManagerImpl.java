package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

@Component
public class AdmissionWorkflowManagerImpl implements AdmissionWorkflowManager{

	private static CustomLogger logger=CustomLogger.getLogger(AdmissionWorkflowManagerImpl.class);
	@Autowired
	AdmissionManager admissionManager;
	
	@Autowired
	FeeManager feeManager;
	
	@Autowired
	ScholarshipWorkflowManager scholarshipWorkflowManager;
	
	@Autowired
	ConsultantWorkflowManager consultantWorkflowManager;

	@Autowired
	FeeWorkflowManager feeWorkflowManager;
	
	public Long addStudentDetails(StudentDetail studentDetail) {
		logger.info("{} : New Student record being created: Student Name : {}",this.getClass().getName(),studentDetail.getFirstName()+studentDetail.getLastName());
		ConsultantWorkflowManager consultantWorkflowManager = new ConsultantWorkflowManagerImpl();
		ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();
	
		Long fileNo = admissionManager.addStudentDtl(studentDetail);

		if(studentDetail.getConsultantDetail() !=null){
		List<ConsultantDetail> consultantDetails = studentDetail.getConsultantDetail();
		if(consultantDetails != null){
			
			consultantWorkflowManager.saveConsultant(consultantDetails);
		}
		}
		
		if(studentDetail.getScholarshipDetail() != null && studentDetail.getScholarshipDetail().getStateId() != null){
		ScholarshipDetail scholarshipDetail = studentDetail.getScholarshipDetail();
		scholarshipDetail.setFileNo(fileNo);
		scholarshipWorkflowManager.addScholarDetail(scholarshipDetail);
		}
		
		return fileNo;
	}

	public Long updateStudentDetails(StudentDetail studentDetail) {
	
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

	@Override
	public Long moveAdmissiontoNextStep(StudentDetail studentDetail,String status){
		
		studentDetail.setApplicationStatus(status);
		
		Long fileNo = admissionManager.updateStudentDtl(studentDetail);
		
		feeWorkflowManager.generateStudentFeeStaging(fileNo);
		
		return fileNo;
		
	}

	@Override
	public Remark getRemarkByFileNo(Long fileNo) {
		
		return admissionManager.getRemarkByFileNo(fileNo);
	}

	@Override
	public void saveRemark(Remark remark) {

		admissionManager.saveRemark(remark);
	}
	
}
