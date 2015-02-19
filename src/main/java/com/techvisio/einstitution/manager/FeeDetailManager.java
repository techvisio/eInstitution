package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeDetailManager {
	
	public List<FeeDetail> getFeeDetail(Long course,Long branch, Integer semester );
	public void addFeeDetail(FeeDetail feeDetail);
	public void updateFeeDetail(FeeDetail feeDetail);
	public void deleteFeeDetail(Long course,Long branch, Integer semester );
	
	
	public StudentFeeStaging getStudentFeeStaging(String fileNo);
	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void deleteStudentFeeStaging(String fileNo);

	public FeeTransaction getFeeTransaction(String fileNo);
	public void addFeeTransaction(FeeTransaction feeTransaction);

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);

}
