package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

public interface FeeDetailDao {
	
	public List<FeeDetail> getFeeDetail();
	
	public StudentFeeStaging getStudentFeeStaging(String studentFeeStaging);
	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	public void deleteStudentFeeStaging(String studentFeeStaging);

	public FeeTransaction getFeeTransaction(String feeTransaction);
	public void addFeeTransaction(FeeTransaction feeTransaction);
	
}
