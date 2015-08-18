package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
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
	public List<FeeAdmission> getPendingfeeInfo(int limit) {
		String getQuery = feeQueryProps.getProperty("getPendingFee");
		SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
		
		List<FeeAdmission> pendingFeeInfos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new RowMapper<FeeAdmission>() {

			@Override
			public FeeAdmission mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				FeeAdmission feeAdmissionBean = new FeeAdmission();
				feeAdmissionBean.setFirstName(rs.getString("First_Name"));
				feeAdmissionBean.setLastName(rs.getString("Last_Name"));
				feeAdmissionBean.setAcademicYear(rs.getString("Academic_Year"));
				feeAdmissionBean.setBranch(rs.getString("Branch"));
				feeAdmissionBean.setCourse(rs.getString("Course"));
				feeAdmissionBean.setDob(rs.getDate("DOB"));
				feeAdmissionBean.setEnrollmentNo(rs.getString("Enroll_No"));
				feeAdmissionBean.setFatherName(rs.getString("Father_Name"));
				feeAdmissionBean.setFileNo(rs.getLong("File_No"));
				feeAdmissionBean.setGender(rs.getString("Gender"));
				feeAdmissionBean.setSemester(rs.getString("Semester"));
                feeAdmissionBean.setPendingFee(rs.getDouble("Pending_Fee"));				
				return feeAdmissionBean;
			}
		});
		return pendingFeeInfos;
	}
	
	@Override
	public Double getPreviousSemBalance(Long fileNo){
		String getQuery = feeQueryProps.getProperty("getPreviousSemBalance");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo);
		Double previousBalance=0.0;
		try{
		previousBalance = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<Double>() {

			@Override
			public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getDouble("difference");
			}
		});
		}
		catch(EmptyResultDataAccessException e){
			System.out.println("No previous balance");
			logger.error("Error While{}",e);
		}
		return previousBalance;
	}
	
	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetails(ApplicableFeeCriteria criteria){
		String queryString="FROM ApplicableFeeDetail afd WHERE afd.course.courseId =" + criteria.getCourseId() +" AND afd.branch.branchId ="+criteria.getBranchId()+" AND afd.session.sessionId="+criteria.getSessionId()+" AND afd.shift.shiftId="+criteria.getShiftId()+" AND afd.centre.centreId=" + criteria.getCentreId();

		Query query=getCurrentSession().createQuery(queryString);
		List<ApplicableFeeDetail> result= query.list();
		return result;
	}	

	//FeeTransaction
	@Override
	public List<FeeTransactionDebit> getDebitedFeeTransaction(Long fileNo) {
		
			String queryString="FROM FeeTransactionDebit ftd WHERE ftd.fileNo = "+fileNo;
			Query query=getCurrentSession().createQuery(queryString);
			List<FeeTransactionDebit> result= query.list();
			return result;
		}

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
@Override
		public List<FeeTransactionCredit> getCreditedFeeTransaction(Long fileNo) {
			String queryString="FROM FeeTransactionCredit ftd WHERE ftd.fileNo = "+fileNo;
			Query query=getCurrentSession().createQuery(queryString);
			List<FeeTransactionCredit> result= query.list();
			return result;	
			}

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
