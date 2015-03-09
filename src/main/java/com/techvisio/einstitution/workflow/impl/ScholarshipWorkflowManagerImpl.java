package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.manager.impl.ScholarshipManagerImpl;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

public class ScholarshipWorkflowManagerImpl implements ScholarshipWorkflowManager {

	ScholarshipManager scholarshipManager=ScholarshipManagerImpl.getInstance();
	
	public ScholarshipDetail getScholarshipDetail(String fileNo) {

		ScholarshipDetail scholarshipDetail = scholarshipManager.getScholarshipDetail(fileNo);
		return scholarshipDetail;
	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {

		scholarshipManager.addScholarDetail(scholarshipDetail);
	}

	public void updateScholarDetail(ScholarshipDetail scholarshipDetail) {

		scholarshipManager.updateScholarDetail(scholarshipDetail);
	}

	public void deleteScholarshipDetail(String fileNo) {

		scholarshipManager.deleteScholarshipDetail(fileNo);
	}

}
