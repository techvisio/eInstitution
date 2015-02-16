package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.FeeDiscountHead;

public interface FeeDetailManager {
	
	public FeeDiscountHead getfeeDiscountHead(Long headId);
	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead);
	public void deleteFeeDiscountHead(Long headId);

}
