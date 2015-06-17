package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.ManagementAdmission;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
@Component
public class ManagementWorkflowManagerImpl implements ManagementWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(ManagementWorkflowManagerImpl.class);
	@Autowired
	AdmissionWorkflowManager admissionWorkFlow;
	
	@Autowired
	FeeWorkflowManager feeworkFlow;
	
	@Autowired
	ScholarshipWorkflowManager schlarshipWorkFlow;
	
	@Override
	public ManagementAdmission getAdmissionManagementView(Long fileNo) {
		logger.info("{} : getAdmissionManagementView for fileno:{} ",this.getClass().getName(),fileNo);
		ManagementAdmission admissionBean=new ManagementAdmission();
		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
		admissionBean.setBasicInfo(basicInfo);
		if(basicInfo!=null){
			List<StudentFeeStaging> staggingFee=feeworkFlow.getStudentFeeStaging(fileNo,null);
			admissionBean.setStagingFee(staggingFee);
			
			List<ApplicableFeeDetail> applicableFee = getApplicableFee(basicInfo);
			
			admissionBean.setApplicableFeeDetails(applicableFee);
			Scholarship scholarshipDetail=schlarshipWorkFlow.getScholarshipDetail(fileNo);
			admissionBean.setScholarship(scholarshipDetail);
				}
			return admissionBean;

	}

	@Override
	public List<ApplicableFeeDetail> getApplicableFee(StudentBasicInfo basicInfo) {
		logger.info("{} : getApplicableFeeDetail for Student:{} ",this.getClass().getName(),basicInfo.getFirstName()+basicInfo.getLastName());
		ApplicableFeeCriteria criteria = CommonUtil.getApplicableFeeCriteriaFromStudentBasicInfo(basicInfo);
		List<ApplicableFeeDetail> applicableFee=feeworkFlow.getApplicableFeeDetail(criteria);
		return applicableFee;
	}


	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit){
		logger.info("{} :calling getUnapprovedAdmissions by passing limit:{} ",this.getClass().getName(),limit);		
	    List<StudentBasicInfo> basicInfo = admissionWorkFlow.getUnapprovedAdmissions(limit);
		
		return basicInfo;
		
	}

	@Override
	public ManagementAdmission updateManagementChanges(ManagementAdmission admissionBean) {
		logger.info("{} :updateManagementChanges for Student:{} ",this.getClass().getName(),admissionBean.getBasicInfo().getFirstName()+admissionBean.getBasicInfo().getLastName());
		StudentBasicInfo basicInfo = admissionBean.getBasicInfo();
		CommonUtil.propogateFileNoTofeeStaging(basicInfo.getFileNo(), admissionBean.getStagingFee());

		Remark remark = basicInfo.getRemark();
		admissionWorkFlow.saveRemark(remark);
		
		//handling scholarship 
	    
		Scholarship scholarshipDetail= admissionBean.getScholarship();
	    
		if(scholarshipDetail!=null){
		scholarshipDetail.setFileNo(basicInfo.getFileNo());
		schlarshipWorkFlow.accomodateManagementChanges(basicInfo,scholarshipDetail);
		}
	    //handling discounts , base applicable fee and other ameneties charges
		List<StudentFeeStaging> feeStagings = admissionBean.getStagingFee();
		feeworkFlow.handleManagementChangesforDiscounts(basicInfo, feeStagings);
		
		return getAdmissionManagementView(basicInfo.getFileNo());
	}
}


