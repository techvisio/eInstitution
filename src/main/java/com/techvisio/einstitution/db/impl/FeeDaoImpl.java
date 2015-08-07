package com.techvisio.einstitution.db.impl;

import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
@Transactional
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

	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetails(ApplicableFeeCriteria criteria){
		String queryString="FROM ApplicableFeeDetail afd WHERE afd.course.courseId =" + criteria.getCourseId() +" AND afd.branch.branchId ="+criteria.getBranchId()+" AND afd.session.sessionId="+criteria.getSessionId()+" AND afd.shift.shiftId="+criteria.getShiftId()+" AND afd.centre.centreId=" + criteria.getCentreId();

		Query query=getCurrentSession().createQuery(queryString);
		List<ApplicableFeeDetail> result= query.list();
		return result;
	}	
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
