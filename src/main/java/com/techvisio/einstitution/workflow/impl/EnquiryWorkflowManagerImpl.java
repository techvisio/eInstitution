package com.techvisio.einstitution.workflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.manager.impl.DefaultManagerImpl;
import com.techvisio.einstitution.manager.impl.EnquiryManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.util.AppConstants.EnquiryStatus;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;
@Component
public class EnquiryWorkflowManagerImpl implements EnquiryWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(EnquiryWorkflowManagerImpl.class);	
	@Autowired
	EnquiryManager enquiryManager;
	
	@Autowired
	DefaultManager defaultManager;

	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager;
	
	@Autowired
	TaskFollowWorkflowManager taskFollowWorkflowManager;
	
	@Override
	public EnquiryAndTaskBean getEnquiryandTask(Long inquiryId) {
		logger.info("{} : calling getInquiry, getTaskAndFollowUpByByModuleAndEntityId by passing enquiryId:{}",this.getClass().getName(), inquiryId);
		EnquiryAndTaskBean enquiryAndTask=new EnquiryAndTaskBean();
		
		AdmissionEnquiry admissionEnquiry=enquiryManager.getInquiry(inquiryId);
		enquiryAndTask.setAdmissionEnquiry(admissionEnquiry);
		
		List<TaskAndFollowUp> tasks=taskFollowWorkflowManager.getTaskAndFollowUpByByModuleAndEntityId(inquiryId, AppConstants.ENQUIRY);
		enquiryAndTask.setTasks(tasks);
		
		return enquiryAndTask;
	}

	@Override
	public Long addEnquiryandTask(EnquiryAndTaskBean enquiryAndTaskBean) {
		logger.info("{} : add Enquiry and Task for enquiryId:{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());	
        enquiryAndTaskBean.getAdmissionEnquiry().setApplicationStatus(EnquiryStatus.OPEN.name());  
	
		Long enquiryId=enquiryManager.addInquiry(enquiryAndTaskBean.getAdmissionEnquiry());
		
		List<TaskAndFollowUp> followUps = enquiryAndTaskBean.getTasks();
		CommonUtil.propogateEntityIdToTaskAndFollowup(followUps, enquiryId, AppConstants.ENQUIRY);
		taskFollowWorkflowManager.saveTaskAndFollowUp(followUps);
		
		return enquiryId; 
		
	}

	@Override
	public Long updateEnquiryandTask(EnquiryAndTaskBean enquiryAndTaskBean) {
		logger.info("{} : update Enquiry and Task for enquiryId:{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());	
		
		Long enquiryId=enquiryManager.updateInquiry(enquiryAndTaskBean.getAdmissionEnquiry());
		
		List<TaskAndFollowUp> followUps = enquiryAndTaskBean.getTasks();
		CommonUtil.propogateEntityIdToTaskAndFollowup(followUps, enquiryId, AppConstants.ENQUIRY);
		taskFollowWorkflowManager.saveTaskAndFollowUp(followUps);
		
		return enquiryId;
	}

	@Override
	public void deleteInquiry(Long inquiryId) {
		logger.info("{} : calling deleteInquiry by passing enquiryId:{}",this.getClass().getName(), inquiryId);
		enquiryManager.deleteInquiry(inquiryId);
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling searchInqByCriteria for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		return enquiryManager.searchInqByCriteria(searchCriteria);
	}

	@Override
	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {
		logger.info("{} : calling getInquiryByTaskDate by passing taskDate:{}",this.getClass().getName(), taskDate);
		return enquiryManager.getInquiryByTaskDate(taskDate);
	}

	public Long proceedToAdmission(EnquiryAndTaskBean enquiryAndTaskBean){
		logger.info("{} : proceedToAdmission   enquiryId:{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());		
		
        AdmissionEnquiry enquiry = enquiryAndTaskBean.getAdmissionEnquiry();
        StudentDetail studentDetail = getStudentFromEquiry(enquiry);
        
        Long fileNo=admissionWorkflowManager.addStudentDetails(studentDetail);
        
        enquiry.setFileNo(fileNo);
        
        enquiry.setApplicationStatus(EnquiryStatus.MOVED_TO_ADMISSION.name());
        
        closeAllTasks(enquiryAndTaskBean);
        
        updateEnquiryandTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();
		
		
	}

	private void closeAllTasks(EnquiryAndTaskBean enquiryAndTaskBean) {
		logger.info("{} : closeAllTasks     enquiryId{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());
		if(enquiryAndTaskBean.getTasks() != null){
        for(TaskAndFollowUp taskAndFollowUp : enquiryAndTaskBean.getTasks()){
        	if(StringUtils.isEmpty(taskAndFollowUp.getRemark())){
        	taskAndFollowUp.setRemark("Auto Closed by System");
        	}
        	taskAndFollowUp.setStatus("C");
        }
        	
        }
	}

	private StudentDetail getStudentFromEquiry(AdmissionEnquiry enquiry) {
		logger.info("{} : getStudentFromEquiry      enquiryId{}",this.getClass().getName(), enquiry.getEnquiryId());
		StudentDetail studentDetail = new StudentDetail();
		String[] names=enquiry.getName().split(" ");
		if(names.length==1){
			studentDetail.setFirstName(names[0]);
			studentDetail.setLastName(" ");
		}
		else if(names.length==2){
			studentDetail.setFirstName(names[0]);
			studentDetail.setLastName(names[1]);
		}
		else if(names.length==3){
			studentDetail.setFirstName(names[0]+" "+names[1]);
			studentDetail.setLastName(names[2]);
		}
		
		studentDetail.setFatherName(enquiry.getFatherName());
		studentDetail.setDob(enquiry.getDob());
        studentDetail.setSelfMobile_No(enquiry.getContactNo());
        studentDetail.setCourseId(enquiry.getCourse().getId());
        studentDetail.setBranchId(enquiry.getBranch().getId());
        studentDetail.setEmailId(enquiry.getEmailId());
        studentDetail.setGender(enquiry.getGender());
        studentDetail.setLateral(enquiry.isLateral());
        studentDetail.setRemarks(enquiry.getRemarks());
        studentDetail.setAcademicYear(defaultManager.getDefaultAcademicYear().toString());
        studentDetail.setReferredBy(enquiry.getReferredBy());
        studentDetail.setAdmissionMode(enquiry.getAdmissionMode());
        studentDetail.setCategoryId(enquiry.getCategoryId());
        if(enquiry.getConsultantId()!=null){
        	
           	ConsultantDetail consultantDetail  =new ConsultantDetail();
              consultantDetail.getConsultant().setConsultantId(enquiry.getCategoryId());         		
       
              if(studentDetail.getConsultantDetail()==null){
              
              List<ConsultantDetail> consultantDetails = new ArrayList<ConsultantDetail>();
              consultantDetails.add(consultantDetail);
              
              studentDetail.setConsultantDetail(consultantDetails);
              
              }
        }
        
        return studentDetail;
	}
	@Override
	public Long toggleEnquiryStatus(EnquiryAndTaskBean enquiryAndTaskBean){
		logger.info("{} : toggleEnquiryStatus      enquiryId{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());		
		 AdmissionEnquiry enquiry = enquiryAndTaskBean.getAdmissionEnquiry();
		 if(EnquiryStatus.OPEN.name().equals(enquiry.getApplicationStatus())){
         enquiry.setApplicationStatus(EnquiryStatus.CLOSED.name());
		 }
		 else
		 {
			 enquiry.setApplicationStatus(EnquiryStatus.OPEN.name());
		 }
        
         closeAllTasks(enquiryAndTaskBean);
        
        updateEnquiryandTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();
		
	} 
	
}
