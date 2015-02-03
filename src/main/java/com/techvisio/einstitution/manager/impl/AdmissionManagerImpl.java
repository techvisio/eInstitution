package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.factory.SequenceFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.util.ContextProvider;

public class AdmissionManagerImpl implements AdmissionManager {


	AdmissionDao admissionDao=ContextProvider.getContext().getBean(AdmissionDao.class);
	
	UniqueIdentifierGenerator identifierGenerator=UniqueIdentifierFactory.getGenerator();
	
	public StudentDetail getStudentDtl(String fileNo) {

		StudentDetail studentDetail=null;
		
		studentDetail= admissionDao.getStudentDtl(fileNo);
		
		return studentDetail;

	}

	public String addStudentDtl(StudentDetail studentDetail) {

		String fileNo=studentDetail.getFileNo();
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		
		admissionDao.addStudentDtl(studentDetail);

		return fileNo;
		
	}

	public String updateStudentDtl(StudentDetail studentDetail) {

		String fileNo=studentDetail.getFileNo();;
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		
		admissionDao.updateStudentDtl(studentDetail);
		
		return fileNo;
	}

	public void deleteSudentDtl(String fileNo) {

		admissionDao.deleteSudentDtl(fileNo);
	}

}
