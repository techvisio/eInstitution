package com.techvisio.einstitution.workflow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.manager.impl.ScholarshipManagerImpl;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
@Component
public class ScholarshipWorkflowManagerImpl implements ScholarshipWorkflowManager {

	@Autowired
	ScholarshipManager scholarshipManager;
	
	public ScholarshipDetail getScholarshipDetail(Long fileNo) {

		
		ScholarshipDetail scholarshipDetail = scholarshipManager.getScholarshipDetail(fileNo);
	
		return scholarshipDetail;
	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {

		scholarshipManager.addScholarDetail(scholarshipDetail);
	}


	public void deleteScholarshipDetail(Long fileNo) {

		scholarshipManager.deleteScholarshipDetail(fileNo);
	}

	public void accomodateManagementChanges(StudentBasicInfo basicInfo, ScholarshipDetail newScholarshipDetail){
		
		scholarshipManager.accomodateManagementChanges(basicInfo, newScholarshipDetail);
	}
	
}
