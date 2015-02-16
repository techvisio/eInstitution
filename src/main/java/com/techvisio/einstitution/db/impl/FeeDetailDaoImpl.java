<<<<<<< HEAD
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
	
	
	
	
	public FeeDetail getFeeDetail(Long feeHeadId,Long course,Long branch) {
		String getFeeDetailQuery=feeQueryProps.getProperty("getFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FEE_HEAD_ID", feeHeadId)
											.addValue("BRANCH", branch).addValue("COURSE", course);
		FeeDetail feeDetails = getNamedParamJdbcTemplate().queryForObject(getFeeDetailQuery, namedParameter, new RowMapper<FeeDetail>(){

			public FeeDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				FeeDetail detail = new FeeDetail();
				detail.setBranch(CommonUtil.getLongValue(rs.getLong("BRANCH")));
				detail.setCourse(CommonUtil.getLongValue(rs.getLong("COURSE")));
				detail.setFeeAmount(rs.getDouble("FEE_AMOUNT"));
				detail.setFeeHeadId(CommonUtil.getLongValue(rs.getLong("FEE_HEAD_ID")));
				detail.setSemester(rs.getInt("SEMESTER"));
				return detail;
			}
			
		});
		
		
		return feeDetails;
		
	}
	


	public void addFeeDetail(FeeDetail feeDetail) {
		String addFeeDetailQuery=feeQueryProps.getProperty("addFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FEE_HEAD_ID",feeDetail.getFeeHeadId())
											.addValue("COURSE",feeDetail.getCourse())
											.addValue("SEMESTER", feeDetail.getSemester())
											.addValue("FEE_AMOUNT", feeDetail.getFeeAmount())
											.addValue("BRANCH", feeDetail.getBranch());
		getNamedParamJdbcTemplate().update(addFeeDetailQuery, namedParameter);
	}




	public void updateFeeDetail(FeeDetail feeDetail) {
		String updateFeeDetailQuery=feeQueryProps.getProperty("updateFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FEE_HEAD_ID",feeDetail.getFeeHeadId())
											.addValue("COURSE",feeDetail.getCourse())
											.addValue("SEMESTER", feeDetail.getSemester())
											.addValue("FEE_AMOUNT", feeDetail.getFeeAmount())
											.addValue("BRANCH", feeDetail.getBranch());
		getNamedParamJdbcTemplate().update(updateFeeDetailQuery, namedParameter);
	}




	public void deleteFeeDetail(Long feeHeadId,Long course,Long branch) {
		String deleteFeeDetailQuery=feeQueryProps.getProperty("deleteFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("FEE_HEAD_ID", feeHeadId)
										.addValue("BRANCH", branch).addValue("COURSE", course);
		
		getNamedParamJdbcTemplate().update(deleteFeeDetailQuery, namedParameter);
		
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
=======
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
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDetailDao;
import com.techvisio.einstitution.util.CommonUtil;

public class FeeDetailDaoImpl extends BaseDao implements FeeDetailDao{

	private Properties feeQueryProps;

	public void setFeeQueryProps(Properties feeQueryProps) {
		this.feeQueryProps = feeQueryProps;
	}

	public FeeDiscountHead getfeeDiscountHead(Long headId) {

		String getQuery = feeQueryProps.getProperty("getFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id", headId);

		FeeDiscountHead discountHead=getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<FeeDiscountHead>() {

			public FeeDiscountHead mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				FeeDiscountHead feeDiscountHead = new FeeDiscountHead();
				feeDiscountHead.setHeadId(CommonUtil.getLongValue(rs.getLong("Head_Id")));
				feeDiscountHead.setHead(rs.getString("Head"));
				feeDiscountHead.setParentId(CommonUtil.getLongValue(rs.getLong("Parent_Type_Id")));
				feeDiscountHead.setType(rs.getString("Type"));
				feeDiscountHead.setDiscountType(rs.getString("Discount_Type"));
				feeDiscountHead.setRefundType(rs.getString("Refund_Type"));

				return feeDiscountHead;
			}
		});

		return discountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		String addQuery = feeQueryProps.getProperty("addFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id", feeDiscountHead.getHeadId())
		.addValue("Parent_Type_Id", feeDiscountHead.getParentId())
		.addValue("Head", feeDiscountHead.getHead())
		.addValue("Type", feeDiscountHead.getType())
		.addValue("Discount_Type", feeDiscountHead.getDiscountType())
		.addValue("Refund_Type", feeDiscountHead.getRefundType());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		String updateQuery = feeQueryProps.getProperty("updateFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id", feeDiscountHead.getHeadId())
		.addValue("Parent_Type_Id", feeDiscountHead.getParentId())
		.addValue("Head", feeDiscountHead.getHead())
		.addValue("Type", feeDiscountHead.getType())
		.addValue("Discount_Type", feeDiscountHead.getDiscountType())
		.addValue("Refund_Type", feeDiscountHead.getRefundType());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}

	public void deleteFeeDiscountHead(Long headId) {

		String deleteQuery = feeQueryProps.getProperty("deleteFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id",headId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
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
>>>>>>> branch 'master' of https://github.com/techvisio/eInstitution.git
