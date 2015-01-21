package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.factory.SequenceFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
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

		if(studentDetail.getFileNo()==null){
			SequenceFactory sf=ContextProvider.getContext().getBean(SequenceFactory.class);
			studentDetail.setFileNo(sf.getSequence("ADMISSION").toString());
		}
		admissionDao.addStudentDtl(studentDetail);

	}

	public void updateStudentDtl(StudentDetail studentDetail) {

		admissionDao.updateStudentDtl(studentDetail);
	}

	public void deleteSudentDtl(String fileNo) {

		admissionDao.deleteSudentDtl(fileNo);
	}

}
