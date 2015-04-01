package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.SearchCriteria;

import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
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
	
	private static AdmissionManagerImpl instance=null;
	public static synchronized AdmissionManagerImpl getInstance()
	{
		if(instance == null){
			instance=new AdmissionManagerImpl();
		}
		
		return instance;
	}
	
	private AdmissionManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	public StudentDetail getStudentDtl(Long fileNo) {

		StudentDetail studentDetail=null;
		
		studentDetail= admissionDao.getStudentDtl(fileNo);
		
		return studentDetail;

	}

	public Long addStudentDtl(StudentDetail studentDetail) {

		Long fileNo=studentDetail.getFileNo();
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		
		admissionDao.addStudentDtl(studentDetail);

		return fileNo;
		
	}

	public Long updateStudentDtl(StudentDetail studentDetail) {

		Long fileNo=studentDetail.getFileNo();;
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		
		admissionDao.updateStudentDtl(studentDetail);
		
		return fileNo;
	}

	public void deleteSudentDtl(Long fileNo) {

		admissionDao.deleteSudentDtl(fileNo);
	}

	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {

		StudentDetail studentDetail = null;
		studentDetail=admissionDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentDetail;
	}
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		StudentBasicInfo info = admissionDao.getStudentBsInfo(fileNo);
		
		return info;
	}

	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit) {
		List<StudentBasicInfo> basicInfos = admissionDao.getLatestAdmissionInfo(limit);
		return basicInfos;
	}
	
	
	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit) {
		List<StudentBasicInfo> basicInfos = admissionDao.getUnapprovedAdmissions(limit);
		return basicInfos;
	}

}
