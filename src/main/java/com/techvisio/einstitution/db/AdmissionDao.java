package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.beans.QuotaCode;

import com.techvisio.einstitution.beans.SearchCriteria;

import com.techvisio.einstitution.beans.StudentBasicInfo;

import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionDao {

	
	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public StudentDetail getStudentDtl(Long fileNo);
	public void addStudentDtl(StudentDetail studentDetail);
	public void updateStudentDtl(StudentDetail studentDetail);
	public void deleteSudentDtl(Long fileNo);
	
	public StudentBasicInfo getStudentBsInfo(Long fileNo);
	
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	
//	
//	public List<StudentAcademicDetail> getAcademicDtl(String fileNo);
//	//public void addAcademicDtl(StudentAcademicDetail academicDtl);
//	//public void updateAcademicDtl(StudentAcademicDetail academicDtl);
//	//public void deleteAcademicDtl(StudentAcademicDetail academicDtl);
//	
//	public List<AddressDetail> getAddressDtl(String fileNo);
//	//public void addAddressDtl(AddressDeatil addressDtl);
//	//public void updateAddressDtl(AddressDeatil addressDtl);
//	//public void deleteAddressDtl(AddressDeatil addressDtl);
//	
//	public List<AdmissionDiscountDtl> getAdmissionDisDtl(String fileNo) ;
//	//public void addAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
//	//public void updateAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
//	//public void deleteAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
//	
//	public List<QualificationSubjectDtl> getQualificationDtl(String fileNo);
////	public void addQualificationDtl(QualificationSubjectDtl qualificationDtl);
////	public void updateQualificationDtl(QualificationSubjectDtl qualificationDtl);
////	public void deleteQualificationDtl(QualificationSubjectDtl qualificationDtl);
//	
//	public List<BranchPreference> getBranchPreference(String fileNo);
////	public void addBranchPreference(BranchPreference branchPreference);
////	public void updateBranchPreference(BranchPreference branchPreference);
////	public void deleteBranchPreference(BranchPreference branchPreference);
	
//	public List<CounsellingDetail> getCounsellingDetail(String fileNo);
//	public void addCounsellingDetail(CounsellingDetail counsellingDetail);
//	public void updateCounsellingDetail(CounsellingDetail counsellingDetail);
//	public void deleteCounsellingDetail(String fileNo);
	

}
