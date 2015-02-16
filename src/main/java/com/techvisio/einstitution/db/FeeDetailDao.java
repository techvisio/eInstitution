package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeDetailDao {
	
	public FeeDetail getFeeDetail(Long feeDetail,Long feeDetail1,Long feeDetail2 );
	public void addFeeDetail(FeeDetail feeDetail);
	public void updateFeeDetail(FeeDetail feeDetail);
	public void deleteFeeDetail(Long feeDetail,Long feeDetail1,Long feeDetail2 );
	
	
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
