package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.ManagementAdmission;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
public interface ManagementWorkflowManager {

	public ManagementAdmission getAdmissionManagementView(Long fileNo);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	public ManagementAdmission updateManagementChanges(ManagementAdmission admissionBean);

	public List<ApplicableFeeDetail> getApplicableFee(StudentBasicInfo basicInfo);

}
