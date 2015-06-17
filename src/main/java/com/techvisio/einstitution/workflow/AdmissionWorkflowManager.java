package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;

@Component
public interface AdmissionWorkflowManager {

	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria);
	
	public Long addStudentDetails(Student studentDetail);
	public Long updateStudentDetails(Student studentDetail);
	public Student getStudentDetails(Long fileNo);
	public void deleteStudentDetails(Long fileNo);
	//public void updateWorkflowStatus(String fileNo);
	
	public StudentBasicInfo getStudentBsInfo(Long fileNo);
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	public Long moveAdmissiontoNextStep(Student studentDetail,String status);

	public Remark getRemarkByFileNo(Long fileNo);
	public void saveRemark(Remark remark);

    }
