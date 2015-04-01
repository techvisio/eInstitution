package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.SearchCriteria;

import java.util.List;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;

public interface AdmissionManager {

	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public StudentDetail getStudentDtl(Long fileNo);
	public Long addStudentDtl(StudentDetail studentDetail);
	public Long updateStudentDtl(StudentDetail studentDtl);
	public void deleteSudentDtl(Long fileNo);
	
	public StudentBasicInfo getStudentBsInfo(Long fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

}
