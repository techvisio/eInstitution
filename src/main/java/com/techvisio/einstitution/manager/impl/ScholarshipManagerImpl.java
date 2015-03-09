package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.manager.ScholarshipManager;
import com.techvisio.einstitution.util.ContextProvider;

public class ScholarshipManagerImpl implements ScholarshipManager {

	ScholarshipDao scholarshipDao = ContextProvider.getContext().getBean(
			ScholarshipDao.class);

	private static ScholarshipManagerImpl instance=null;
	public static synchronized ScholarshipManagerImpl getInstance()
	{
		if(instance == null){
			instance=new ScholarshipManagerImpl();
		}
		
		return instance;
	}
	
	private ScholarshipManagerImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public ScholarshipDetail getScholarshipDetail(String fileNo) {

		ScholarshipDetail scholarshipDetail = null;
		scholarshipDetail = scholarshipDao.getScholarshipDetail(fileNo);
		
		return scholarshipDetail;
	
		
	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {

		scholarshipDao.addScholarDetail(scholarshipDetail);
	}

	public void updateScholarDetail(ScholarshipDetail scholarshipDetail) {

		scholarshipDao.updateScholarDetail(scholarshipDetail);
	}

	public void deleteScholarshipDetail(String fileNo) {

		scholarshipDao.deleteScholarshipDetail(fileNo);
	}

}
