package com.techvisio.einstitution.workflow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
@Component
public class ScholarshipWorkflowManagerImpl implements ScholarshipWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(ScholarshipWorkflowManagerImpl.class);
	@Autowired
	ScholarshipManager scholarshipManager;
	
	public ScholarshipDetail getScholarshipDetail(Long fileNo) {
		logger.info("{} : calling getScholarshipDetail by passing fleno:{} ",this.getClass().getName(),fileNo);	
		ScholarshipDetail scholarshipDetail = scholarshipManager.getScholarshipDetail(fileNo);
	
		return scholarshipDetail;
	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {
		logger.info("{} : calling addScholarDetail for fleno:{} ",this.getClass().getName(),scholarshipDetail.getFileNo());
		scholarshipManager.addScholarDetail(scholarshipDetail);
	}


	public void deleteScholarshipDetail(Long fileNo) {
		logger.info("{} : calling deleteScholarshipDetail by passing fleno:{} ",this.getClass().getName(),fileNo);
		scholarshipManager.deleteScholarshipDetail(fileNo);
	}

	public void accomodateManagementChanges(StudentBasicInfo basicInfo, ScholarshipDetail newScholarshipDetail){
		logger.info("{} : calling accomodateManagementChanges for Student:{} ",this.getClass().getName(), basicInfo.getFirstName()+basicInfo.getLastName());		
		scholarshipManager.accomodateManagementChanges(basicInfo, newScholarshipDetail);
	}
	
}
