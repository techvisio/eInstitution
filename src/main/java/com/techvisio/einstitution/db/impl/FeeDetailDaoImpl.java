package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDetailDao;
import com.techvisio.einstitution.util.CommonUtil;

public class FeeDetailDaoImpl extends BaseDao implements FeeDetailDao{

	private Properties feeQueryProps;
	
	
	public void setFeeQueryProps(Properties feeQueryProps) {
		this.feeQueryProps = feeQueryProps;
	}
	
	
	
	
	public List<FeeDetail> getFeeDetail() {
		String getFeeDetailQuery=feeQueryProps.getProperty("getFeeDetailMaster");

		List<FeeDetail> feeDetails =new ArrayList<FeeDetail>();
		
		feeDetails = getNamedParamJdbcTemplate().query(getFeeDetailQuery, new RowMapper<FeeDetail>(){

			public FeeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				FeeDetail detail = new FeeDetail();
				detail.setBranch(CommonUtil.getLongValue(rs.getLong("BRANCH")));
				detail.setCourse(CommonUtil.getLongValue(rs.getLong("COURSE")));
				detail.setFeeAmount(rs.getDouble("FEE_AMOUNT"));
				detail.setFeeHeadId(CommonUtil.getLongValue(rs.getLong("FEE_HEAD_ID")));
				detail.setSemester(CommonUtil.getLongValue(rs.getLong("SEMESTER")));
				return detail;
			}
			
			
		});
		
		return feeDetails;
		


	}

	


	public StudentFeeStaging getStudentFeeStaging(String fileNo) {
		String getQuery = feeQueryProps.getProperty("getStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FILE_NO", fileNo);
		
		List<StudentFeeStaging> feeStagings = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<StudentFeeStaging>(){

			public StudentFeeStaging mapRow(ResultSet rs, int rowNum)throws SQLException {
				StudentFeeStaging feeStaging = new StudentFeeStaging();
				feeStaging.setBranch(CommonUtil.getLongValue( rs.getInt("BRANCH")));
				feeStaging.setCourse(CommonUtil.getLongValue(rs.getInt("COURSE")));
				feeStaging.setFeeGenerated(rs.getBoolean("FEE_GENERATED"));
				feeStaging.setFileNo(rs.getString("FILE_NO"));
				feeStaging.setSemester(rs.getInt("SEMESTER"));
				
				return feeStaging;
			}
			
		});
		
		StudentFeeStaging feeStaging = null;
		if(feeStagings != null && feeStagings.size()>0){
			feeStaging = feeStagings.get(0);
			
		}
		return feeStaging;
	}

	



	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		String addQuery = feeQueryProps.getProperty("addStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FILE_NO", studentFeeStaging.getFileNo())
											.addValue("COURSE", studentFeeStaging.getCourse())
											.addValue("BRANCH", studentFeeStaging.getBranch())
											.addValue("SEMESTER",studentFeeStaging.getSemester())
											.addValue("FEE_GENERATED", studentFeeStaging.isFeeGenerated());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}




	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		String updateQuery = feeQueryProps.getProperty("updateStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FILE_NO", studentFeeStaging.getFileNo())
											.addValue("COURSE", studentFeeStaging.getCourse())
											.addValue("BRANCH", studentFeeStaging.getBranch())
											.addValue("SEMESTER",studentFeeStaging.getSemester())
											.addValue("FEE_GENERATED", studentFeeStaging.isFeeGenerated());
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		
	}




	public void deleteStudentFeeStaging(String fileNo) {
		String deleteQuery = feeQueryProps.getProperty("deleteStudentFeeStaging");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("FILE_NO", fileNo);
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}



	public FeeTransaction getFeeTransaction(String fileNo) {
		String getQuery = feeQueryProps.getProperty("getFeeTransaction");
		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_no", fileNo);
		
		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new RowMapper<FeeTransaction>(){

			public FeeTransaction mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeTransaction feeTransaction = new FeeTransaction();
				feeTransaction.setAmount(rs.getDouble("AMOUNT"));
				feeTransaction.setAmountTransactionType(rs.getString("AMOUNT_TRANSACTION_TYPE"));
				feeTransaction.setDate(rs.getDate("DATE"));
				feeTransaction.setFeeId(CommonUtil.getLongValue(rs.getLong("FEE_ID")));
				feeTransaction.setFileNo(rs.getString("File_no"));
				feeTransaction.setUser(rs.getString("USER"));
				return feeTransaction;
			}
			
		});
		
		FeeTransaction feeTransaction = null;
		if(feeTransactions != null && feeTransactions.size()>0){
			feeTransaction = feeTransactions.get(0);
		}
		return feeTransaction;
	}



	public void addFeeTransaction(FeeTransaction feeTransaction) {
		String addQuery = feeQueryProps.getProperty("addFeeTransaction");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_no", feeTransaction.getFileNo())
											.addValue("FEE_ID", feeTransaction.getFeeId())
											.addValue("DATE", feeTransaction.getDate())
											.addValue("USER", feeTransaction.getUser())
											.addValue("AMOUNT", feeTransaction.getAmount())
											.addValue("AMOUNT_TRANSACTION_TYPE", feeTransaction.getAmountTransactionType());
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		
	}
	
}
