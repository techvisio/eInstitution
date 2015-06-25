package com.techvisio.einstitution.workflow.impl;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
@Component
public class ManagementWorkflowManagerImpl implements ManagementWorkflowManager{
//	private static CustomLogger logger=CustomLogger.getLogger(ManagementWorkflowManagerImpl.class);
//	@Autowired
//	AdmissionWorkflowManager admissionWorkFlow;
//	
//	@Autowired
//	FeeWorkflowManager feeworkFlow;
//	
//	@Autowired
//	ScholarshipWorkflowManager schlarshipWorkFlow;
//	
//	@Override
//	public ManagementAdmission getAdmissionManagementView(Long fileNo) {
//		logger.info("{} : getAdmissionManagementView for fileno:{} ",this.getClass().getName(),fileNo);
//		ManagementAdmission admissionBean=new ManagementAdmission();
//		StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
//		admissionBean.setBasicInfo(basicInfo);
//		if(basicInfo!=null){
//			List<StudentFeeStaging> staggingFee=feeworkFlow.getStudentFeeStaging(fileNo,null);
//			admissionBean.setStagingFee(staggingFee);
//			
//			List<ApplicableFeeDetail> applicableFee = getApplicableFee(basicInfo);
//			
//			admissionBean.setApplicableFeeDetails(applicableFee);
//			Scholarship scholarshipDetail=schlarshipWorkFlow.getScholarshipDetail(fileNo);
//			admissionBean.setScholarship(scholarshipDetail);
//				}
//			return admissionBean;
//
//	}
//
//	@Override
//	public List<ApplicableFeeDetail> getApplicableFee(StudentBasicInfo basicInfo) {
//		logger.info("{} : getApplicableFeeDetail for Student:{} ",this.getClass().getName(),basicInfo.getFirstName()+basicInfo.getLastName());
//		ApplicableFeeCriteria criteria = CommonUtil.getApplicableFeeCriteriaFromStudentBasicInfo(basicInfo);
//		List<ApplicableFeeDetail> applicableFee=feeworkFlow.getApplicableFeeDetail(criteria);
//		return applicableFee;
//	}
//
//
//	public List<StudentBasicInfo> getUnapprovedAdmissions(int limit){
//		logger.info("{} :calling getUnapprovedAdmissions by passing limit:{} ",this.getClass().getName(),limit);		
//	    List<StudentBasicInfo> basicInfo = admissionWorkFlow.getUnapprovedAdmissions(limit);
//		
//		return basicInfo;
//		
//	}
//
//	@Override
//	public ManagementAdmission updateManagementChanges(ManagementAdmission admissionBean) {
//		logger.info("{} :updateManagementChanges for Student:{} ",this.getClass().getName(),admissionBean.getBasicInfo().getFirstName()+admissionBean.getBasicInfo().getLastName());
//		StudentBasicInfo basicInfo = admissionBean.getBasicInfo();
//		CommonUtil.propogateFileNoTofeeStaging(basicInfo.getFileNo(), admissionBean.getStagingFee());
//
//		Remark remark = basicInfo.getRemark();
//		admissionWorkFlow.saveRemark(remark);
//		
//		//handling scholarship 
//	    
//		Scholarship scholarshipDetail= admissionBean.getScholarship();
//	    
//		if(scholarshipDetail!=null){
//		scholarshipDetail.setFileNo(basicInfo.getFileNo());
//		schlarshipWorkFlow.accomodateManagementChanges(basicInfo,scholarshipDetail);
//		}
//	    //handling discounts , base applicable fee and other ameneties charges
//		List<StudentFeeStaging> feeStagings = admissionBean.getStagingFee();
//		feeworkFlow.handleManagementChangesforDiscounts(basicInfo, feeStagings);
//		
//		return getAdmissionManagementView(basicInfo.getFileNo());
//	}
}


