package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.ManagementAdmissionBean;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

public class ManagementWorkflowManagerImpl implements ManagementWorkflowManager{

	@Override
	public ManagementAdmissionBean getAdmissionManagementView(Long fileNo) {

		AdmissionWorkflowManager admissionWorkFlow=new AdmissionWorkflowManagerImpl();
		FeeWorkflowManager feeworkFlow= new FeeWorkflowManagerImpl();
		ScholarshipWorkflowManager schlarshipWorkFlow=new ScholarshipWorkflowManagerImpl();

		ManagementAdmissionBean admissionBean=new ManagementAdmissionBean();
		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
		admissionBean.setBasicInfo(basicInfo);
		if(basicInfo!=null){
			List<StudentFeeStaging> staggingFee=feeworkFlow.getStudentFeeStaging(fileNo,null);
			admissionBean.setStagingFee(staggingFee);
			
			ApplicableFeeCriteria criteria=new ApplicableFeeCriteria();
			criteria.setBranchId(basicInfo.getBranch().getId());
			criteria.setCentreId(basicInfo.getCentreId());
			criteria.setCourseId(basicInfo.getCourse().getId());
			criteria.setLateral(basicInfo.isLateral());
			criteria.setSessionId(basicInfo.getSession().getSessionId());
			criteria.setShiftId(basicInfo.getShiftId());
			
			List<ApplicableFeeDetail> applicableFee=feeworkFlow.getApplicableFeeDetail(criteria);
			admissionBean.setApplicableFeeDetails(applicableFee);
			
			ScholarshipDetail scholarshipDetail=schlarshipWorkFlow.getScholarshipDetail(fileNo);
			admissionBean.setScholarship(scholarshipDetail);

		}
		return admissionBean;

	}

	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit){
		
		AdmissionWorkflowManager manager = new AdmissionWorkflowManagerImpl();
	    List<StudentBasicInfo> basicInfo = manager.getUnapprovedAdmissions(limit);
		
		return basicInfo;
		
	}

	@Override
	public ManagementAdmissionBean updateManagementChanges(ManagementAdmissionBean admissionBean) {

	    ScholarshipWorkflowManager scholarshipWorkflowManager = new ScholarshipWorkflowManagerImpl();
	    ScholarshipDetail scholarshipDetail= admissionBean.getScholarship();
	    scholarshipWorkflowManager.addScholarDetail(scholarshipDetail);
	    admissionBean.getScholarship();
	    
		FeeWorkflowManager feeWorkflowManager = new FeeWorkflowManagerImpl();
		List<StudentFeeStaging> feeStagings = admissionBean.getStagingFee();
		feeWorkflowManager.updateStudentFeeStaging(feeStagings);
	    admissionBean.getStagingFee();
		
		return admissionBean;
	}
}


