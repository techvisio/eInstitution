package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionDao {

	public StudentDetail getStudentDtl(String fileNo);
	public void addStudentDtl(StudentDetail studentDetail);
	public void updateStudentDtl(StudentDetail studentDetail);
	public void deleteSudentDtl(String fileNo);
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
	


}
