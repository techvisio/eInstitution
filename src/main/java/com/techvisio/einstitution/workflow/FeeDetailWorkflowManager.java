package com.techvisio.einstitution.workflow;

import com.techvisio.einstitution.beans.FeeDiscountHead;

public interface FeeDetailWorkflowManager {
	

	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);


}
