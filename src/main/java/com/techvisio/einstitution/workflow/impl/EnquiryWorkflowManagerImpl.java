package com.techvisio.einstitution.workflow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.EnquiryAndTask;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.AppConstants.EnquiryStatus;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;
@Component
@Transactional
public class EnquiryWorkflowManagerImpl implements EnquiryWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(EnquiryWorkflowManagerImpl.class);	

	@Autowired
	EnquiryManager enquiryManager;

	@Autowired
	TaskFollowWorkflowManager taskFollowWorkflowManager;

	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager;

	@Autowired
	DefaultManager defaultManager;

	@Override
	public EnquiryAndTask getEnquiryandTask(Long inquiryId) {
		logger.info("{} : calling getInquiry, getTaskAndFollowUpByByModuleAndEntityId by passing enquiryId:{}",this.getClass().getName(), inquiryId);
		EnquiryAndTask enquiryAndTask=new EnquiryAndTask();

		AdmissionEnquiry admissionEnquiry=enquiryManager.getInquiry(inquiryId);
		enquiryAndTask.setAdmissionEnquiry(admissionEnquiry);

		List<TaskAndFollowUp> tasks=taskFollowWorkflowManager.getTaskAndFollowUpByByModuleAndEntityId(inquiryId, AppConstants.ENQUIRY);
		enquiryAndTask.setTasks(tasks);

		return enquiryAndTask;
	}

	@Override
	public Long saveEnquiryAndTask(EnquiryAndTask enquiryAndTaskBean ) {

		enquiryAndTaskBean.getAdmissionEnquiry().setApplicationStatus(EnquiryStatus.OPEN.name());  

		Long enquiryId = enquiryManager.saveInquiry(enquiryAndTaskBean.getAdmissionEnquiry());

		List<TaskAndFollowUp> followUps = enquiryAndTaskBean.getTasks();
		CommonUtil.propogateEntityIdToTaskAndFollowup(followUps, enquiryId, AppConstants.ENQUIRY);
		taskFollowWorkflowManager.saveTaskAndFollowUp(followUps);
		
		return enquiryId;
	}


	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling searchInqByCriteria for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		return enquiryManager.searchInqByCriteria(searchCriteria);
	}

	public Long proceedToAdmission(EnquiryAndTask enquiryAndTaskBean){
		logger.info("{} : proceedToAdmission   enquiryId:{}",this.getClass().getName(), enquiryAndTaskBean.getAdmissionEnquiry().getEnquiryId());		

		AdmissionEnquiry enquiry = enquiryAndTaskBean.getAdmissionEnquiry();
		Student studentDetail = getStudentFromEquiry(enquiry);

		Long fileNo=admissionWorkflowManager.saveStudent(studentDetail);

		enquiry.setFileNo(fileNo);

		enquiry.setApplicationStatus(EnquiryStatus.MOVED_TO_ADMISSION.name());

		closeAllTasks(enquiryAndTaskBean);

		saveEnquiryAndTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();


	}

	private void closeAllTasks(EnquiryAndTask enquiryAndTaskBean) {
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

	private Student getStudentFromEquiry(AdmissionEnquiry enquiry) {
		logger.info("{} : getStudentFromEquiry      enquiryId{}",this.getClass().getName(), enquiry.getEnquiryId());
		Student studentDetail = new Student();
		String[] names=enquiry.getName().split(" ");
		if(names.length==1){
			studentDetail.getStudentBasics().setFirstName(names[0]);
			studentDetail.getStudentBasics().setLastName(" ");
		}
		else if(names.length==2){
			studentDetail.getStudentBasics().setFirstName(names[0]);
			studentDetail.getStudentBasics().setLastName(names[1]);
		}
		else if(names.length==3){
			studentDetail.getStudentBasics().setFirstName(names[0]+" "+names[1]);
			studentDetail.getStudentBasics().setLastName(names[2]);
		}

		studentDetail.getStudentBasics().setFatherName(enquiry.getFatherName());
		studentDetail.getStudentBasics().setDob(enquiry.getDob());
		studentDetail.getStudentBasics().setSelfMobile_No(enquiry.getContactNo());
		studentDetail.setCourse(enquiry.getCourse());
		studentDetail.setBranch(enquiry.getBranch());
		studentDetail.getStudentBasics().setEmailId(enquiry.getEmailId());
		studentDetail.getStudentBasics().setGender(enquiry.getGender());
		studentDetail.getStudentBasics().setLateral(enquiry.isLateral());
		studentDetail.setAcademicYear(defaultManager.getDefaultAcademicYear().toString());
		studentDetail.getStudentBasics().setReferredBy(enquiry.getReferredBy());
		studentDetail.getStudentBasics().setAdmissionMode(enquiry.getAdmissionMode());
		studentDetail.getStudentBasics().setCategory(enquiry.getCategory());
		if(enquiry.getConsultant().getConsultantId()!=null){

			AdmissnConsltntDtl consultantDetail  =new AdmissnConsltntDtl();
			consultantDetail.getConsultant().setConsultantId(enquiry.getConsultant().getConsultantId());         		

			if(studentDetail.getConsultantDetail()==null){

				List<AdmissnConsltntDtl> consultantDetails = new ArrayList<AdmissnConsltntDtl>();
				consultantDetails.add(consultantDetail);

				studentDetail.setConsultantDetail(consultantDetails);

			}
		}

		return studentDetail;
	}
	@Override
	public Long toggleEnquiryStatus(EnquiryAndTask enquiryAndTaskBean){
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

		saveEnquiryAndTask(enquiryAndTaskBean);
		return enquiry.getEnquiryId();

	}

}
