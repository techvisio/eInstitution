package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeDetailWorkflowManager {
	
	public FeeDetail getFeeDetail(Long feeDetailFeeHeadId,Long feeDetailCourse,Long feeDetailBranch );
	public void addFeeDetail(FeeDetail feeDetail);
	public void updateFeeDetail(FeeDetail feeDetail);
	public void deleteFeeDetail(Long feeDetailFeeHeadId,Long feeDetailCourse,Long feeDetailBranch );
	
	public StudentFeeStaging getStudentFeeStaging(String studentFeeStaging);
	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void deleteStudentFeeStaging(String studentFeeStaging);

	public FeeTransaction getFeeTransaction(String feeTransaction);
	public void addFeeTransaction(FeeTransaction feeTransaction);

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);

	
	

}
