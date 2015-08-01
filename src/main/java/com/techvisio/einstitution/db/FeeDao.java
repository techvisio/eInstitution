package com.techvisio.einstitution.db;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;

@Component
public interface FeeDao {

	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit);

	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit);
		
}
