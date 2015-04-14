package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.manager.impl.ScholarshipManagerImpl;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

public class ScholarshipWorkflowManagerImpl implements ScholarshipWorkflowManager {

	ScholarshipManager scholarshipManager=ScholarshipManagerImpl.getInstance();
	
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

	public void accomodateManagementChanges(ScholarshipDetail newScholarshipDetail){
		
		scholarshipManager.accomodateManagementChanges(newScholarshipDetail);
	}
	
}
