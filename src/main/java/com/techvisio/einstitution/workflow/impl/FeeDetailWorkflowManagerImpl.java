package com.techvisio.einstitution.workflow.impl;

import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.manager.FeeDetailManager;
import com.techvisio.einstitution.manager.impl.FeeDetailManagerImpl;
import com.techvisio.einstitution.workflow.FeeDetailWorkflowManager;

public class FeeDetailWorkflowManagerImpl implements FeeDetailWorkflowManager{

	
	FeeDetailManager manager=FeeDetailManagerImpl.getInstance();
	
	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		FeeDiscountHead feeDiscountHead = manager.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		manager.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		manager.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {

		manager.deleteFeeDiscountHead(headId);
	}

	
	
}
