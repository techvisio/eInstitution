package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Activity;
import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentActivity;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.factory.ActivityType;
import com.techvisio.einstitution.manager.ActivityExecuter;
import com.techvisio.einstitution.util.CommonUtil;

public class FeeActivityExecuter implements ActivityExecuter{

	@Autowired
	FeeDao feeDao;

	@Autowired
	AdmissionDao admissionDao; 

	@Override
	public void execute(StudentBasics studentBasics) {

		
		ApplicableFeeCriteria criteria = null;
		StudentBasicInfo basicInfo = admissionDao.getStudentBsInfo(studentBasics.getFileNo());
		if(basicInfo != null){
		criteria = CommonUtil.getApplicableFeeCriteriaFromStudentBasicInfo(basicInfo);
		}
		
		List<ApplicableFeeDetail> applicableFeeDetails = feeDao.getApplicableFeeDetails(criteria);
		
		for(ApplicableFeeDetail applicableFeeDetail : applicableFeeDetails){
		FeeTransactionDebit feeTransactionDebit = new FeeTransactionDebit();
		feeTransactionDebit.setBatch(studentBasics.getBatch());
		feeTransactionDebit.setSession(studentBasics.getSession());
		feeTransactionDebit.setFileNo(studentBasics.getFileNo());
		feeTransactionDebit.setAmount(applicableFeeDetail.getFeeAmount());
		feeTransactionDebit.setFeeDiscountHead(applicableFeeDetail.getFeeDetail());

		feeDao.addFeeTransactionDebit(feeTransactionDebit);
		}
		
		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setFileNo(studentBasics.getFileNo());
		Activity activity=new Activity();
		activity.setActivityName(ActivityType.FEE_GENERATION.name());
		studentActivity.setActivity(activity);
		admissionDao.saveStudentActivity(studentActivity);
	}

}
