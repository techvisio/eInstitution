package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionManager {

	
	public StudentDetail getStudentDtl(String fileNo);
	public String addStudentDtl(StudentDetail studentDetail);
	public void updateStudentDtl(StudentDetail studentDtl);
	public void deleteSudentDtl(String fileNo);
	


}
