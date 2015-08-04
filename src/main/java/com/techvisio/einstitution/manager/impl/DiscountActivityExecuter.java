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
import com.techvisio.einstitution.manager.ActivityExecuter;
public class DiscountActivityExecuter implements ActivityExecuter{

	private static String DISCOUNT_ACTIVITY="DISCOUNT_ADJUSTMENT";
	
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
		Activity activity=new Activity();
		activity.setActivityName(DISCOUNT_ACTIVITY);
		studentActivity.setActivity(activity);
		admissionDao.saveStudentActivity(studentActivity);
	}

}
