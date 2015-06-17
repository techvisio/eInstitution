package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.SearchCriteria;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.factory.SequenceFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class AdmissionManagerImpl implements AdmissionManager {

	private static CustomLogger logger = CustomLogger.getLogger(AdmissionManagerImpl.class);
	@Autowired
	AdmissionDao admissionDao;
	
	@Autowired
	UniqueIdentifierGenerator identifierGenerator;
	
	@Autowired
	DefaultManager defaultManager;
	
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
	public Student getStudentDtl(Long fileNo) {
		logger.info("{} : calling getStudentDtl method by passing file no:{}",this.getClass().getName(), fileNo);
		Student studentDetail=null;
		
		studentDetail= admissionDao.getStudentDtl(fileNo);
		
		return studentDetail;

	}

	public Long addStudentDtl(Student studentDetail) {
		logger.info("{} : calling addStudentDtl method for Student:{}",this.getClass().getName(), studentDetail.getFirstName()+studentDetail.getLastName());
		Long fileNo=studentDetail.getFileNo();
		updateMissingInfowithDefaultValue(studentDetail);
		
		if(fileNo==null){
			fileNo=identifierGenerator.getUniqueIdentifierForAdmission();
		}
		studentDetail.setFileNo(fileNo);
		CommonUtil.propogateIdentifiertoAdmission(studentDetail);;
		
		String registrationNo=studentDetail.getRegistrationNo();
		if(registrationNo==null){
			registrationNo=identifierGenerator.getUniqueIdentifierForRegistration(studentDetail);
		}
		studentDetail.setRegistrationNo(registrationNo);
		
		
		admissionDao.addStudentDtl(studentDetail);

		return fileNo;
		
	}

	private void updateMissingInfowithDefaultValue(Student studentDetail) {
		logger.info("{} : Updating Missing Information with Default Value for Student:{}",this.getClass().getName(), studentDetail.getFirstName()+studentDetail.getLastName());		
		
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

	public Long updateStudentDtl(Student studentDetail) {
		logger.info("{} : calling updateStudentDtl method for Student:{}",this.getClass().getName(), studentDetail.getFirstName()+studentDetail.getLastName());		
		Long fileNo=studentDetail.getFileNo();
		
		CommonUtil.propogateIdentifiertoAdmission(studentDetail);
		
		admissionDao.updateStudentDtl(studentDetail);
		
		return fileNo;
	}

	public void deleteSudentDtl(Long fileNo) {
		logger.info("{} : calling deleteSudentDtl method by passing file no:{}",this.getClass().getName(), fileNo);
		admissionDao.deleteSudentDtl(fileNo);
	}

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria method for student:{}",this.getClass().getName(), searchCriteria.getFirstName());	
		List<StudentBasicInfo> studentBasicInfos = null;
		studentBasicInfos=admissionDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentBasicInfos;
	}
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		logger.info("{} : calling getStudentBsInfo method by passing file no:{}",this.getClass().getName(), fileNo);		
		StudentBasicInfo info = admissionDao.getStudentBsInfo(fileNo);
		
		return info;
	}

	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit) {
		logger.info("{} : calling getLatestAdmissionInfo method by passing limit:{}",this.getClass().getName(), limit);
		List<StudentBasicInfo> basicInfos = admissionDao.getLatestAdmissionInfo(limit);
		return basicInfos;
	}
	
	
	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit) {
		logger.info("{} : calling getUnapprovedAdmissions method by passing limit:{}",this.getClass().getName(), limit);
		List<StudentBasicInfo> basicInfos = admissionDao.getUnapprovedAdmissions(limit);
		return basicInfos;
	}

	@Override
	public Remark getRemarkByFileNo(Long fileNo) {
		logger.info("{} : calling getRemarkByFileNo method by passing file no:{}",this.getClass().getName(), fileNo);		
		return admissionDao.getRemarkByFileNo(fileNo);
	}

	@Override
	public void saveRemark(Remark remark) {
		logger.info("{} : calling saveRemark method for file no:{}",this.getClass().getName(), remark.getFileNo());
		admissionDao.saveRemark(remark);
	}

}
