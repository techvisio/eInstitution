package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.ManagementAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;

public interface ManagementWorkflowManager {

	public ManagementAdmissionBean getAdmissionManagementView(Long fileNo);

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit);

	public ManagementAdmissionBean updateManagementChanges(ManagementAdmissionBean admissionBean);

}
