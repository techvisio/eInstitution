package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Activity;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.StudentActivity;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.factory.ActivityType;
import com.techvisio.einstitution.manager.ActivityExecuter;

public class ScholarshipActivityExecuter implements ActivityExecuter{


	@Autowired
	FeeDao feeDao;

	@Autowired
	AdmissionDao admissionDao; 

	@Autowired
	ScholarshipDao scholarshipDao;

	@Override
	public void execute(StudentBasics studentBasics) {

		Scholarship scholarship = scholarshipDao.getScholarship(studentBasics.getFileNo());

		if(scholarship.isApproved()){

			FeeTransactionCredit feeTransactionCredit = new FeeTransactionCredit();
			feeTransactionCredit.setBatch(studentBasics.getBatch());
			feeTransactionCredit.setSession(studentBasics.getSession());
			feeTransactionCredit.setFileNo(studentBasics.getFileNo());
			feeTransactionCredit.setAmount(scholarship.getAmount());

			feeDao.addFeeTransactionCredit(feeTransactionCredit);
		}

		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setFileNo(studentBasics.getFileNo());
		Activity activity=new Activity();
		activity.setActivityName(ActivityType.SCHOLARSHIP_ADJUSTMENT.toString());
		studentActivity.setActivity(activity);
		admissionDao.saveStudentActivity(studentActivity);
	}

}
