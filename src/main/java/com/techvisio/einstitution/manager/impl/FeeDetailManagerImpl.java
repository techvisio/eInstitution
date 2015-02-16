package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.db.FeeDetailDao;
import com.techvisio.einstitution.manager.FeeDetailManager;
import com.techvisio.einstitution.util.ContextProvider;

public class FeeDetailManagerImpl implements FeeDetailManager{

	FeeDetailDao feeDetailDao = ContextProvider.getContext().getBean(
			FeeDetailDao.class);

	
	private static FeeDetailManagerImpl instance=null;
	public static synchronized FeeDetailManagerImpl getInstance()
	{
		if(instance == null){
			instance=new FeeDetailManagerImpl();
		}
		
		return instance;
	}
	
	private FeeDetailManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		FeeDiscountHead feeDiscountHead =  null;
		feeDiscountHead = feeDetailDao.getfeeDiscountHead(headId);
		
		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeDetailDao.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		feeDetailDao.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {

		feeDetailDao.deleteFeeDiscountHead(headId);
	}

}
