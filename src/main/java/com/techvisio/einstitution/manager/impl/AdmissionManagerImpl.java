package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.SearchCriteria;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.factory.SequenceFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.CommonUtil;
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
		updateMissingInfowithDefaultValue(studentDetail);
		
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		
		String registrationNo=studentDetail.getRegistrationNo();
		if(registrationNo==null){
			registrationNo=identifierGenerator.getUniqueIdentifierForRegistration(studentDetail);
		}
		studentDetail.setRegistrationNo(registrationNo);
		
		
		admissionDao.addStudentDtl(studentDetail);

		return fileNo;
		
	}

	private void updateMissingInfowithDefaultValue(StudentDetail studentDetail) {
		DefaultManager defaultManager=new DefaultManagerImpl();
		if(CommonUtil.isNullLongValue(studentDetail.getBatchId())){
			Long defaultBatchId=defaultManager.getDefaultBatch(studentDetail.getCourseId());
			studentDetail.setBatchId(defaultBatchId);
		}
		if(CommonUtil.isNullLongValue(studentDetail.getSessionId())){
			Long sessionId=defaultManager.getDefaultSession(studentDetail.getCourseId());
			studentDetail.setSessionId(sessionId);
		}
		if(CommonUtil.isNullLongValue(studentDetail.getCentreId())){
			Long centreId=defaultManager.getDefaultCentre();
			studentDetail.setCentreId(centreId);
		}
		if(CommonUtil.isNullLongValue(studentDetail.getShiftId())){
			Long shiftId=defaultManager.getDefaultShift();
			studentDetail.setShiftId(shiftId);
		}
		if(StringUtils.isEmpty(studentDetail.getAcademicYear())){
			String academicyr=defaultManager.getDefaultAcademicYear().toString();
			studentDetail.setAcademicYear(academicyr);
		}
	}

	public Long updateStudentDtl(StudentDetail studentDetail) {

		Long fileNo=studentDetail.getFileNo();
		
		admissionDao.updateStudentDtl(studentDetail);
		
		return fileNo;
	}

	public void deleteSudentDtl(Long fileNo) {

		admissionDao.deleteSudentDtl(fileNo);
	}

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {

		List<StudentBasicInfo> studentBasicInfos = null;
		studentBasicInfos=admissionDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentBasicInfos;
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
