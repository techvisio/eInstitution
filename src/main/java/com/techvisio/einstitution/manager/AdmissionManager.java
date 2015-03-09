package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.SearchCriteria;
import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionManager {

	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public StudentDetail getStudentDtl(String fileNo);
	public String addStudentDtl(StudentDetail studentDetail);
	public String updateStudentDtl(StudentDetail studentDtl);
	public void deleteSudentDtl(String fileNo);
	
	public StudentBasicInfo getStudentBsInfo(String fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

}
