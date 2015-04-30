package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.ManagementAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
public interface ManagementWorkflowManager {

	public ManagementAdmissionBean getAdmissionManagementView(Long fileNo);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	public ManagementAdmissionBean updateManagementChanges(ManagementAdmissionBean admissionBean);

	public List<ApplicableFeeDetail> getApplicableFee(StudentBasicInfo basicInfo);

}
