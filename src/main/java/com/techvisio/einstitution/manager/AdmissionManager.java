package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;

@Component
public interface AdmissionManager {

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public Student getStudentDtl(Long fileNo);
	public Long addStudentDtl(Student studentDetail);
	public Long updateStudentDtl(Student studentDtl);
	public void deleteSudentDtl(Long fileNo);
	
	public StudentBasicInfo getStudentBsInfo(Long fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	public Remark getRemarkByFileNo(Long fileNo);
	public void saveRemark(Remark remark);

}
