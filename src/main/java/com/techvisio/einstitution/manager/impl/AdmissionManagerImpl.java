package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.util.ContextProvider;

public class AdmissionManagerImpl implements AdmissionManager {


	AdmissionDao admissionDao=ContextProvider.getContext().getBean(AdmissionDao.class);
	
	public StudentDetail getStudentDtl(String fileNo) {

		StudentDetail studentDetail=null;
		
		studentDetail= admissionDao.getStudentDtl(fileNo);
		
		return studentDetail;

	}

	public void addStudentDtl(StudentDetail studentDetail) {

		admissionDao.addStudentDtl(studentDetail);

	}

	public void updateStudentDtl(StudentDetail studentDetail) {

		admissionDao.updateStudentDtl(studentDetail);
	}

	public void deleteSudentDtl(String fileNo) {

		admissionDao.deleteSudentDtl(fileNo);
	}

}
