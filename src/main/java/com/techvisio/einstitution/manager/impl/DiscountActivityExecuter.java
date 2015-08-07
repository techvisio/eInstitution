package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Activity;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.StudentActivity;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.factory.ActivityType;
import com.techvisio.einstitution.manager.ActivityExecuter;
public class DiscountActivityExecuter implements ActivityExecuter{

	@Autowired
	FeeDao feeDao;
	
	@Autowired
	AdmissionDao admissionDao; 
	
	@Override
	public void execute(StudentBasics studentBasics) {
		
List<AdmissionDiscount> admissionDiscounts = admissionDao.getDiscountDtl(studentBasics.getFileNo());


		for(AdmissionDiscount admissionDiscount : admissionDiscounts){
			
			if(admissionDiscount.isApproved()){
				FeeTransactionCredit feeTransactionCredit = new FeeTransactionCredit();
				feeTransactionCredit.setBatch(studentBasics.getBatch());
				feeTransactionCredit.setFileNo(studentBasics.getFileNo());
				feeTransactionCredit.setSession(studentBasics.getSession());
				feeTransactionCredit.setFeeDiscountHead(admissionDiscount.getDiscountHead());
				feeTransactionCredit.setAmount(admissionDiscount.getAmount());
			    feeDao.addFeeTransactionCredit(feeTransactionCredit);
			}
		}

		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setFileNo(studentBasics.getFileNo());
		Activity activity=new Activity();
		activity.setActivityName(ActivityType.DISCOUNT_ADJUSTMENT.toString());
		studentActivity.setActivity(activity);
		admissionDao.saveStudentActivity(studentActivity);
	}

}
