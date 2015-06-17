package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
@Component
public interface FeeWorkflowManager {
	
	public void addFeeDetail(ApplicableFeeDetail feeDetail);
	public void updateFeeDetail(ApplicableFeeDetail feeDetail);
	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId );
	
	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo,Long feeHeadId);
	public void saveStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings);
	public void deleteStudentFeeStagingbyfileNo(StudentFeeStaging feeStaging);
	public void saveStudentFeeStaging(StudentFeeStaging studentFeeStaging);
	
	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo);
	public void addFeeTransactionDebit(FeeTransaction feeTransaction);
	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo);
	public void addFeeTransactionCredit(FeeTransaction feeTransaction);

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);

	public void generateStudentFeeStaging(Long fileNo);
	FeeTransactionAdmission getFeeTransactionDetail(Long fileNo);
	List<FeeAdmission> getPendingfeeInfo(int limit);
	List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria);
	
	public Boolean isManagementApproved(Long fileNo);
	void handleManagementChangesforDiscounts(StudentBasicInfo basicInfo,List<StudentFeeStaging> newStaggingDiscounts);
	
	public void saveAmenities(StudentFeeStaging studentFeeStaging);

}
