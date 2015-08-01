package com.techvisio.einstitution.db.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class FeeDaoImpl extends BaseDao implements FeeDao{
	private static CustomLogger logger = CustomLogger.getLogger(FeeDaoImpl.class);

	@Autowired @Qualifier(value="feeQueryProps")
	private Properties feeQueryProps;

	public void setFeeQueryProps(Properties feeQueryProps) {
		this.feeQueryProps = feeQueryProps;
	}
	
	@Autowired
	CacheManager cacheManager ; 
	
	@Autowired
	DefaultManager defaultManager;
	
//	//FeeTransaction
//	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo) {
//		logger.info("{} : Get debited fee transaction for file no:{} ",this.getClass().getName(),fileNo);		
//	
//		String queryString="FROM FeeTransactionDebit ftd WHERE ftd.fileNo = "+fileNo;
//		Query query=getCurrentSession().createQuery(queryString);
//		List<FeeTransactionDebit> result= query.list();
//		return result;
//	}

	@Override
	public void addFeeTransactionDebit(FeeTransactionDebit feeTransactionDebit) {
		logger.info("{} : Add fee transaction debit for file no :{} ",this.getClass().getName());
		if(feeTransactionDebit.getTransactionId()==null){
			getCurrentSession().persist(feeTransactionDebit);
		}
		else{
			getCurrentSession().update(feeTransactionDebit);
		}	
		}

//	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {
//		logger.info("{} : Get credited fee transaction for file no:{} ",this.getClass().getName(),fileNo);
//		String getQuery = feeQueryProps.getProperty("getFeeTransactionCredit");
//		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_No", fileNo);
//
//		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new FeeTransactionRowMapper());
//
//		return feeTransactions;
//	}

	@Override
	public void addFeeTransactionCredit(FeeTransactionCredit feeTransactionCredit) {
		logger.info("{} : Add credit fee transaction for file no:{} ",this.getClass().getName());		
		
		if(feeTransactionCredit.getTransactionId()==null){
			getCurrentSession().persist(feeTransactionCredit);
		}
		else{
			getCurrentSession().update(feeTransactionCredit);
		}	}

}
