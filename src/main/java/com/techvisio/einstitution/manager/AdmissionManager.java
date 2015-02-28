package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionManager {

	
	public StudentDetail getStudentDtl(String fileNo);
	public String addStudentDtl(StudentDetail studentDetail);
	public String updateStudentDtl(StudentDetail studentDtl);
	public void deleteSudentDtl(String fileNo);
	
	public StudentBasicInfo getStudentBsInfo(String fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

}
