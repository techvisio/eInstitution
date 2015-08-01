package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.ActivityExecuter;

public class ScholarshipActivityExecuter implements ActivityExecuter{

	@Autowired
	FeeDao feeDao;
	
	@Override
	public void execute(Student student) {

		Scholarship scholarship = student.getScholarship();
		if(scholarship.isApproved()){
			
			FeeTransactionCredit feeTransactionCredit = new FeeTransactionCredit();
			feeTransactionCredit.setBatch(student.getStudentBasics().getBatch());
			feeTransactionCredit.setSession(student.getStudentBasics().getSession());
			feeTransactionCredit.setFileNo(student.getFileNo());
			feeTransactionCredit.setAmount(scholarship.getAmount());

			 feeDao.addFeeTransactionCredit(feeTransactionCredit);
					}
	}

}
