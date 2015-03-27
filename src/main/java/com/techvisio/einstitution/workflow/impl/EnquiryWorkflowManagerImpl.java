package com.techvisio.einstitution.workflow.impl;

import java.util.Date;
import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.manager.impl.EnquiryManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.AppConstants.EnquiryStatus;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;

public class EnquiryWorkflowManagerImpl implements EnquiryWorkflowManager {

	EnquiryManager enquiryManager = EnquiryManagerImpl.getInstance();
	
	
	@Override
	public EnquiryAndTaskBean getEnquiryandTask(Long inquiryId) {
		TaskFollowWorkflowManager taskFollowWorkflowManager=new TaskFollowWorkflowManagerImpl();
		
		EnquiryAndTaskBean enquiryAndTask=new EnquiryAndTaskBean();
		
		AdmissionEnquiry admissionEnquiry=enquiryManager.getInquiry(inquiryId);
		enquiryAndTask.setAdmissionEnquiry(admissionEnquiry);
		
		
		List<TaskAndFollowUp> tasks=taskFollowWorkflowManager.getTaskAndFollowUpByByModuleAndEntityId(inquiryId, AppConstants.ENQUIRY);
		enquiryAndTask.setTasks(tasks);
		
		return enquiryAndTask;
	}

	@Override
	public Long addEnquiryandTask(EnquiryAndTaskBean enquiryAndTaskBean) {
	
		 
		
		Long enquiryId=enquiryManager.addInquiry(enquiryAndTaskBean.getAdmissionEnquiry());
		
		TaskFollowWorkflowManager taskFollowWorkflowManager =  new  TaskFollowWorkflowManagerImpl();
         enquiryAndTaskBean.getAdmissionEnquiry().setApplicationStatus(EnquiryStatus.OPEN.name());  	
		
		
		List<TaskAndFollowUp> followUps = enquiryAndTaskBean.getTasks();
		CommonUtil.propogateEntityIdToTaskAndFollowup(followUps, enquiryId, AppConstants.ENQUIRY);
		taskFollowWorkflowManager.saveTaskAndFollowUp(followUps);
		
		return enquiryId; 
		
	}

	@Override
	public Long updateEnquiryandTask(EnquiryAndTaskBean enquiryAndTaskBean) {
		
		
		Long enquiryId=enquiryManager.updateInquiry(enquiryAndTaskBean.getAdmissionEnquiry());
	
		TaskFollowWorkflowManager taskFollowWorkflowManager =  new  TaskFollowWorkflowManagerImpl();
		
		List<TaskAndFollowUp> followUps = enquiryAndTaskBean.getTasks();
		CommonUtil.propogateEntityIdToTaskAndFollowup(followUps, enquiryId, AppConstants.ENQUIRY);
		taskFollowWorkflowManager.saveTaskAndFollowUp(followUps);
		
		return enquiryId;
	}

	@Override
	public void deleteInquiry(Long inquiryId) {

		enquiryManager.deleteInquiry(inquiryId);
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		return enquiryManager.searchInqByCriteria(searchCriteria);
	}

	@Override
	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {
		return enquiryManager.getInquiryByTaskDate(taskDate);
	}

	public Long proceedToAdmission(EnquiryAndTaskBean enquiryAndTaskBean){
		
		AdmissionWorkflowManager admissionWorkflowManager = new AdmissionWorkflowManagerImpl();
        AdmissionEnquiry enquiry = enquiryAndTaskBean.getAdmissionEnquiry();
        StudentDetail studentDetail = getStudentFromEquiry(enquiry);
        
        String fileNo=admissionWorkflowManager.addStudentDetails(studentDetail);
        enquiry.setFileNo(fileNo);
        
        enquiry.setApplicationStatus(EnquiryStatus.MOVED_TO_ADMISSION.name());
        
        closeAllTasks(enquiryAndTaskBean);
        
        updateEnquiryandTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();
		
		
	}

	private void closeAllTasks(EnquiryAndTaskBean enquiryAndTaskBean) {
		if(enquiryAndTaskBean.getTasks() != null){
        for(TaskAndFollowUp taskAndFollowUp : enquiryAndTaskBean.getTasks()){
        	taskAndFollowUp.setRemark("Auto Closed by System");
        	taskAndFollowUp.setStatus("C");
        }
        	
        }
	}

	private StudentDetail getStudentFromEquiry(AdmissionEnquiry enquiry) {
		StudentDetail studentDetail = new StudentDetail();
		studentDetail.setFirstName(enquiry.getName());
		studentDetail.setFatherName(enquiry.getFatherName());
		studentDetail.setDob(enquiry.getDob());
        studentDetail.setSelfMobile_No(enquiry.getContactNo());
        studentDetail.setCourseId(enquiry.getCourseId());
        studentDetail.setBranchId(enquiry.getBranchId());
        studentDetail.setEmailId(enquiry.getEmailId());
        studentDetail.setGender(enquiry.getGender());
		return studentDetail;
	}
	@Override
	public Long closeEnquiry(EnquiryAndTaskBean enquiryAndTaskBean){
		
		 AdmissionEnquiry enquiry = enquiryAndTaskBean.getAdmissionEnquiry();
         enquiry.setApplicationStatus(EnquiryStatus.CLOSED.name());
        
         closeAllTasks(enquiryAndTaskBean);
        
        updateEnquiryandTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();
		
	} 
	
}
