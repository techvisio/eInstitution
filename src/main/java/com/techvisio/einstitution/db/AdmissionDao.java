package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentDocument;
@Component
public interface AdmissionDao {
	

	public void saveStudent(Student student);
	public Student getStudent(Long fileNo);

	public List<StudentAcademic> getAcademicDtl(Long fileNo);
	public void saveAcademicDtl(List<StudentAcademic> studentAcademics, Long fileNo);
	public void saveAcademicDtl(StudentAcademic studentAcademic);
	public void deleteAcademicDtlExclusion(List<StudentAcademic> studentAcademics, Long fileNo);
	
	public List<Address> getAddressDtl(Long fileNo);
	public void saveAddressDtl(List<Address> addresses, Long fileNo);
	public void saveAddressDtl(Address address);
	public void deleteAddressDtlExclusion(List<Address> addresses, Long fileNo);
	
	public List<AdmissionDiscount> getDiscountDtl(Long fileNo);
	public void saveDiscountDtl(List<AdmissionDiscount> admissionDiscounts, Long fileNo);
	public void saveDiscountDtl(AdmissionDiscount admissionDiscount);
	public void deleteDiscountDtlExclusion(List<AdmissionDiscount> admissionDiscounts, Long fileNo);

	public List<QualificationSubject> getQualificaionSubDtl(Long fileNo);
	public void saveQualificationSubDtl (List<QualificationSubject> qualificationSubjects, Long fileNo);
	public void saveQualificationSubDtl(QualificationSubject qualificationSubject);
	public void deleteQualificationSubDtlExclusion(List<QualificationSubject> qualificationSubjects, Long fileNo);
	
	public List<BranchPreference> getBranchPreference(Long fileNo);
	public void saveBranchPreference(List<BranchPreference> branchPreferences, Long fileNo);
	public void saveBranchPreference(BranchPreference branchPreference);
	public void deleteBranchPreferenceExclusion(List<BranchPreference> branchPreferences, Long fileNo);
	
	public List<Counselling> getCounsellingDtl(Long fileNo);
	public void saveCounsellingDtl(List<Counselling> counsellings, Long fileNo);
	public void saveCounsellingDtl(Counselling counselling);
	public void deleteCounsellingDtlExclusion(List<Counselling> counsellings, Long fileNo);
	
	public List<StudentDocument> getDocumentDtl(Long fileNo);
	public void saveDocumentDtl(List<StudentDocument> documents, Long fileNo);
	public void saveDocumentDtl(StudentDocument document);
	public void deleteDocumentDtlExclusion(List<StudentDocument> documents, Long fileNo);
	
	public List<Object[]> getStudentDocumentDtl();
	
	}
