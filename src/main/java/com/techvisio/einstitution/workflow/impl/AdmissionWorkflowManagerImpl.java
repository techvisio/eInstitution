package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.beans.Workflow;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;

@Component
@Transactional
public class AdmissionWorkflowManagerImpl implements AdmissionWorkflowManager{

	private static CustomLogger logger=CustomLogger.getLogger(AdmissionWorkflowManagerImpl.class);
	@Autowired
	AdmissionManager admissionManager;

	@Autowired
	FeeManager feeManager;

	@Autowired
	FeeWorkflowManager feeWorkflowManager;
	
	@Autowired
	CacheManager cacheManager;

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria) {
		logger.info("{} : calling getStudentDtlBySearchCriteria for Student:{}",this.getClass().getName(), searchCriteria.getFirstName());		
		List<StudentBasicInfo> studentBasicInfos = admissionManager.getStudentDtlBySearchCriteria(searchCriteria);
		return studentBasicInfos;
	}
	
	@Override
	public Long saveStudent(Student student) {
	Long fileNo=admissionManager.saveStudent(student);
		return fileNo;
			}

	@Override
	public StudentBasics getStudentBasics(Long fileNo) {
		StudentBasics studentBasics = admissionManager.getStudentBasics(fileNo);
		return studentBasics;
	}

	@Override
	public void saveStudentBasics(StudentBasics studentBasics) {
		admissionManager.saveStudentBasics(studentBasics);		
	}

	@Override
	public void saveAcademicDtl(List<StudentAcademic> studentAcademics,Long fileNo) {
		admissionManager.saveAcademicDtl(studentAcademics, fileNo);
	}

	@Override
	public void saveAcademicDtl(StudentAcademic studentAcademic) {
		admissionManager.saveAcademicDtl(studentAcademic);
	}

	@Override
	public void deleteAcademicDtlExclusion(List<StudentAcademic> studentAcademics, Long fileNo) {
		admissionManager.deleteAcademicDtlExclusion(studentAcademics, fileNo);
	}

	@Override
	public void saveAddressDtl(List<Address> addresses, Long fileNo) {
		admissionManager.saveAddressDtl(addresses, fileNo);
	}

	@Override
	public void saveAddressDtl(Address address) {
		admissionManager.saveAddressDtl(address);
	}

	@Override
	public void deleteAddressDtlExclusion(List<Address> addresses, Long fileNo) {
		admissionManager.deleteAddressDtlExclusion(addresses, fileNo);
	}

	@Override
	public void saveDiscountDtl(List<AdmissionDiscount> admissionDiscounts,
			Long fileNo) {
		admissionManager.saveDiscountDtl(admissionDiscounts, fileNo);
	}

	@Override
	public void saveDiscountDtl(AdmissionDiscount admissionDiscount) {
		admissionManager.saveDiscountDtl(admissionDiscount);
	}

	@Override
	public void deleteDiscountDtlExclusion(
			List<AdmissionDiscount> admissionDiscounts, Long fileNo) {
		admissionManager.deleteDiscountDtlExclusion(admissionDiscounts, fileNo);

	}

	@Override
	public void saveQualificationSubDtl (List<QualificationSubject> qualificationSubjects, Long fileNo, Long stdntQualifctnId){
		admissionManager.saveQualificationSubDtl(qualificationSubjects, fileNo,stdntQualifctnId);
	}

	@Override
	public void saveQualificationSubDtl(
			QualificationSubject qualificationSubject) {
		admissionManager.saveQualificationSubDtl(qualificationSubject);
	}

	@Override
	public void deleteQualificationSubDtlExclusion(List<QualificationSubject> qualificationSubjects, Long fileNo, Long stdntQualifctnId){
		admissionManager.deleteQualificationSubDtlExclusion(qualificationSubjects, fileNo, stdntQualifctnId);
	}

	@Override
	public void saveBranchPreference(List<BranchPreference> branchPreferences,
			Long fileNo) {
		admissionManager.saveBranchPreference(branchPreferences, fileNo);
	}

	@Override
	public void saveBranchPreference(BranchPreference branchPreference) {
		admissionManager.saveBranchPreference(branchPreference);
	}

	@Override
	public void deleteBranchPreferenceExclusion(
			List<BranchPreference> branchPreferences, Long fileNo) {
		admissionManager.deleteBranchPreferenceExclusion(branchPreferences, fileNo);
	}

	@Override
	public void saveCounsellingDtl(List<Counselling> counsellings, Long fileNo) {
		admissionManager.saveCounsellingDtl(counsellings, fileNo);
	}

	@Override
	public void saveCounsellingDtl(Counselling counselling) {
		admissionManager.saveCounsellingDtl(counselling);
	}

	@Override
	public void deleteCounsellingDtlExclusion(List<Counselling> counsellings,
			Long fileNo) {
		admissionManager.deleteCounsellingDtlExclusion(counsellings, fileNo);

	}

	@Override
	public Student getStudent(Long fileNo) {
		Student student = admissionManager.getStudent(fileNo);
		return student;
	}

	@Override
	public List<StudentAcademic> getAcademicDtl(Long fileNo) {
		List<StudentAcademic> studentAcademics = admissionManager.getAcademicDtl(fileNo);
		return studentAcademics;
	}

	@Override
	public List<Address> getAddressDtl(Long fileNo) {
		List<Address> addresses = admissionManager.getAddressDtl(fileNo);
		return addresses;
	}

	@Override
	public List<AdmissionDiscount> getDiscountDtl(Long fileNo) {
		List<AdmissionDiscount> admissionDiscounts = admissionManager.getDiscountDtl(fileNo);
		return admissionDiscounts;
	}

	@Override
	public List<QualificationSubject> getQualificaionSubDtl(Long fileNo) {
		List<QualificationSubject> qualificationSubjects = admissionManager.getQualificaionSubDtl(fileNo);
		return qualificationSubjects;
	}

	@Override
	public List<BranchPreference> getBranchPreference(Long fileNo) {
		List<BranchPreference> branchPreferences = admissionManager.getBranchPreference(fileNo);
		return branchPreferences;
	}

	@Override
	public List<Counselling> getCounsellingDtl(Long fileNo) {
		List<Counselling> counsellings = admissionManager.getCounsellingDtl(fileNo);
		return counsellings;
	}

	@Override
	public List<StudentDocument> getDocumentDtl(Long fileNo) {
		List<StudentDocument> studentDocuments = admissionManager.getDocumentDtl(fileNo);
		return studentDocuments;
	}

	@Override
	public void saveDocumentDtl(List<StudentDocument> documents, Long fileNo) {
		admissionManager.saveDocumentDtl(documents, fileNo);
	}

	@Override
	public void saveDocumentDtl(StudentDocument document) {
		admissionManager.saveDocumentDtl(document);
	}

	@Override
	public void deleteDocumentDtlExclusion(List<StudentDocument> documents,
			Long fileNo) {
		admissionManager.deleteDocumentDtlExclusion(documents, fileNo);		
	}

	@Override
	public List<Object[]> getStudentDocumentDtl() {
		List<Object[]> studentDocuments = admissionManager.getStudentDocumentDtl();
		return studentDocuments;
	}
	
	@Override
	public Workflow getNewAdmissionWorkFlow(){
		return cacheManager.getNewAdmissionWorkFlow();
	}

}
