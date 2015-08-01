package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentActivity;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.ActivityExecuter;

public class DiscountActivityExecuter implements ActivityExecuter{

	@Autowired
	FeeDao feeDao;
	
	@Override
	public void execute(Student student) {
		
		List<AdmissionDiscount> admissionDiscounts = student.getDiscountDtl();
		
		for(AdmissionDiscount admissionDiscount : admissionDiscounts){
			
			if(admissionDiscount.isApproved()){
				FeeTransactionCredit feeTransactionCredit = new FeeTransactionCredit();
				feeTransactionCredit.setBatch(student.getStudentBasics().getBatch());
				feeTransactionCredit.setFileNo(student.getFileNo());
				feeTransactionCredit.setSession(student.getStudentBasics().getSession());
				feeTransactionCredit.setFeeDiscountHead(admissionDiscount.getDiscountHead());
				feeTransactionCredit.setAmount(admissionDiscount.getAmount());
			    feeDao.addFeeTransactionCredit(feeTransactionCredit);
			}
		}
		
	}

}
