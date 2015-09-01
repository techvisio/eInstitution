package com.techvisio.einstitution.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.techvisio.einstitution.beans.Activity;
import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.beans.Workflow;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.CommonUtil;
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

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria method for student:{}",this.getClass().getName(), searchCriteria.getFirstName());	
		List<StudentBasicInfo> studentBasicInfos = null;
		studentBasicInfos=admissionDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return studentBasicInfos;
	}
	
	@Override
	public Long saveStudent(Student student) {
		String registrationNo=student.getRegistrationNo();
		if(registrationNo==null){
			registrationNo=identifierGenerator.getUniqueIdentifierForRegistration(student);
		}
		student.setRegistrationNo(registrationNo);
		Long fileNo=admissionDao.saveStudent(student);
		return fileNo;
	}

	@Override
	public StudentBasics getStudentBasics(Long fileNo) {
		
		StudentBasics studentBasics = admissionDao.getStudentBasics(fileNo);
		return studentBasics;
	}

	@Override
	public void saveStudentBasics(StudentBasics studentBasics) {
		admissionDao.saveStudentBasics(studentBasics);
	}
	
	@Override
	public void saveAcademicDtl(List<StudentAcademic> studentAcademics, Long fileNo) {
		admissionDao.saveAcademicDtl(studentAcademics, fileNo);
	}

	@Override
	public void saveAcademicDtl(StudentAcademic studentAcademic) {

		admissionDao.saveAcademicDtl(studentAcademic);
	}

	@Override
	public void deleteAcademicDtlExclusion(List<StudentAcademic> studentAcademics, Long fileNo) {

		admissionDao.deleteAcademicDtlExclusion(studentAcademics, fileNo);
	}

	@Override
	public void saveAddressDtl(List<Address> addresses, Long fileNo) {

		admissionDao.saveAddressDtl(addresses, fileNo);
	}

	@Override
	public void saveAddressDtl(Address address) {

		admissionDao.saveAddressDtl(address);
	}

	@Override
	public void deleteAddressDtlExclusion(List<Address> addresses, Long fileNo) {

		deleteAddressDtlExclusion(addresses, fileNo);
	}

	@Override
	public void saveDiscountDtl(List<AdmissionDiscount> admissionDiscounts,
			Long fileNo) {

		admissionDao.saveDiscountDtl(admissionDiscounts, fileNo);
	}

	@Override
	public void saveDiscountDtl(AdmissionDiscount admissionDiscount) {

		admissionDao.saveDiscountDtl(admissionDiscount);
	}

	@Override
	public void deleteDiscountDtlExclusion(
			List<AdmissionDiscount> admissionDiscounts, Long fileNo) {

		admissionDao.deleteDiscountDtlExclusion(admissionDiscounts, fileNo);
	}

	@Override
	public void saveQualificationSubDtl (List<QualificationSubject> qualificationSubjects, Long fileNo, Long stdntQualifctnId){

		admissionDao.saveQualificationSubDtl(qualificationSubjects, fileNo,stdntQualifctnId);
	}

	@Override
	public void saveQualificationSubDtl(
			QualificationSubject qualificationSubject) {

		admissionDao.saveQualificationSubDtl(qualificationSubject);
	}

	@Override
	public void deleteQualificationSubDtlExclusion(List<QualificationSubject> qualificationSubjects, Long fileNo, Long stdntQualifctnId){

		admissionDao.deleteQualificationSubDtlExclusion(qualificationSubjects, fileNo,stdntQualifctnId);
	}

	@Override
	public void saveBranchPreference(List<BranchPreference> branchPreferences,
			Long fileNo) {

		admissionDao.saveBranchPreference(branchPreferences, fileNo);
	}

	@Override
	public void saveBranchPreference(BranchPreference branchPreference) {

		admissionDao.saveBranchPreference(branchPreference);
	}

	@Override
	public void deleteBranchPreferenceExclusion(
			List<BranchPreference> branchPreferences, Long fileNo) {

		admissionDao.deleteBranchPreferenceExclusion(branchPreferences, fileNo);
	}

	@Override
	public void saveCounsellingDtl(List<Counselling> counsellings, Long fileNo) {

		admissionDao.saveCounsellingDtl(counsellings, fileNo);
	}

	@Override
	public void saveCounsellingDtl(Counselling counselling) {

		admissionDao.saveCounsellingDtl(counselling);
	}

	@Override
	public void deleteCounsellingDtlExclusion(List<Counselling> counsellings,
			Long fileNo) {

		admissionDao.deleteCounsellingDtlExclusion(counsellings, fileNo);
	}

	@Override
	public Student getStudent(Long fileNo) {

		Student student = admissionDao.getStudent(fileNo);
		return student;
	}

	@Override
	public List<StudentAcademic> getAcademicDtl(Long fileNo) {

		List<StudentAcademic> studentAcademics = admissionDao.getAcademicDtl(fileNo);
		return studentAcademics;
	}

	@Override
	public List<Address> getAddressDtl(Long fileNo) {
        List<Address> addresses = admissionDao.getAddressDtl(fileNo);
		return addresses;
	}

	@Override
	public List<AdmissionDiscount> getDiscountDtl(Long fileNo) {
		List<AdmissionDiscount> admissionDiscounts = admissionDao.getDiscountDtl(fileNo);
		return admissionDiscounts;
	}

	@Override
	public List<QualificationSubject> getQualificaionSubDtl(Long fileNo) {
		List<QualificationSubject> qualificationSubjects = admissionDao.getQualificaionSubDtl(fileNo);
		return qualificationSubjects;
	}

	@Override
	public List<BranchPreference> getBranchPreference(Long fileNo) {
		List<BranchPreference> branchPreferences = admissionDao.getBranchPreference(fileNo);
		return branchPreferences;
	}

	@Override
	public List<Counselling> getCounsellingDtl(Long fileNo) {
		List<Counselling> counsellings = admissionDao.getCounsellingDtl(fileNo);
		return counsellings;
	}

	@Override
	public List<StudentDocument> getDocumentDtl(Long fileNo) {
	List<StudentDocument> studentDocuments = admissionDao.getDocumentDtl(fileNo); 
		return studentDocuments;
	}

	@Override
	public void saveDocumentDtl(List<StudentDocument> documents, Long fileNo) {
		admissionDao.saveDocumentDtl(documents, fileNo);
		
	}

	@Override
	public void saveDocumentDtl(StudentDocument document) {
		admissionDao.saveDocumentDtl(document);
		
	}

	@Override
	public void deleteDocumentDtlExclusion(List<StudentDocument> documents,
			Long fileNo) {

		admissionDao.deleteDocumentDtlExclusion(documents, fileNo);
	}

	@Override
	public Map<String,List<List<StudentDocument>>> getStudentDocumentForUI(Long fileNo) {
		Map<String,List<List<StudentDocument>>> studentDocuments = admissionDao.getStudentDocumentForUI(fileNo);
		return studentDocuments;
	}

	@Override
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {

		StudentBasicInfo basicInfo = admissionDao.getStudentBsInfo(fileNo);
		return basicInfo;
	}
	
}
