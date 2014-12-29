package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.util.ContextProvider;

public class AdmissionManagerImpl implements AdmissionManager {


	AdmissionDao admissionDao=ContextProvider.getContext().getBean(AdmissionDao.class);
	
	public void getStudentDetail(StudentDetail studentDetail) {

		admissionDao.getStudentDtl(studentDetail);

	}

	public void addStudentDetail(StudentDetail studentDetail) {

		admissionDao.addStudentDtl(studentDetail);

	}

	public void updateStudentDetail(StudentDetail studentDetail) {

		admissionDao.updateStudentDtl(studentDetail);
	}

	public void deleteStudentDetail(StudentDetail studentDetail) {

		admissionDao.deleteSudentDtl(studentDetail);
	}

}
