package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.EnquiryManager;
import com.techvisio.einstitution.manager.impl.EnquiryManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
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

	
	
	
}
