package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.AddressDeatil;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionDao {

	public void getStudentDtl(StudentDetail studentDtl);
	public void addStudentDtl(StudentDetail studentDtl);
	public void updateStudentDtl(StudentDetail studentDtl);
	public void deleteSudentDtl(StudentDetail studentDtl);
	
	public void getAcademicDtl(StudentAcademicDetail academicDtl);
	public void addAcademicDtl(StudentAcademicDetail academicDtl);
	public void updateAcademicDtl(StudentAcademicDetail academicDtl);
	public void deleteAcademicDtl(StudentAcademicDetail academicDtl);
	
	public void getAddressDtl(AddressDeatil addressDtl);
	public void addAddressDtl(AddressDeatil addressDtl);
	public void updateAddressDtl(AddressDeatil addressDtl);
	public void deleteAddressDtl(AddressDeatil addressDtl);
	
	public void getAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
	public void addAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
	public void updateAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
	public void deleteAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl);
	
	public void getQualificationDtl(QualificationSubjectDtl qualificationDtl);
	public void addQualificationDtl(QualificationSubjectDtl qualificationDtl);
	public void updateQualificationDtl(QualificationSubjectDtl qualificationDtl);
	public void deleteQualificationDtl(QualificationSubjectDtl qualificationDtl);
	
}
