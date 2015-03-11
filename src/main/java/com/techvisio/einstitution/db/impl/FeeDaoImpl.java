package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.util.CommonUtil;

public class FeeDaoImpl extends BaseDao implements FeeDao{

	private Properties feeQueryProps;


	public void setFeeQueryProps(Properties feeQueryProps) {
		this.feeQueryProps = feeQueryProps;
	}


	public List<FeeDetail> getFeeDetail(Long course, Long branch) {
		String getFeeDetailQuery=feeQueryProps.getProperty("getFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("COURSE", course)
		.addValue("BRANCH", branch);
		List<FeeDetail> feeDetails = getNamedParamJdbcTemplate().query(getFeeDetailQuery, namedParameter, new RowMapper<FeeDetail>(){

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


	public void deleteFeeDetail(Long course, Long branch, Integer semester) {
		String deleteFeeDetailQuery=feeQueryProps.getProperty("deleteFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("COURSE", course)
		.addValue("BRANCH", branch).addValue("SEMESTER", semester);

		getNamedParamJdbcTemplate().update(deleteFeeDetailQuery, namedParameter);

	}

	//	StudentFeeStaging

	public List<StudentFeeStaging> getStudentFeeStaging(String fileNo) {
		String getQuery = feeQueryProps.getProperty("getStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);

		List<StudentFeeStaging> feeStagings = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<StudentFeeStaging>(){
			public StudentFeeStaging mapRow(ResultSet rs, int rowNum)throws SQLException {
				StudentFeeStaging feeStaging = new StudentFeeStaging();
				feeStaging.setFeeGenerated(rs.getBoolean("Fee_Generated"));
				feeStaging.setFileNo(rs.getString("File_No"));
				feeStaging.setSemester(rs.getInt("Semester"));
				feeStaging.setAcademicYear(rs.getString("Academic_Year"));
				feeStaging.setAmount(rs.getDouble("Amount"));
				feeStaging.setApproved(rs.getBoolean("Approved"));
				feeStaging.setCreatedBy(rs.getString("Created_By"));
				feeStaging.setCreatedDate(rs.getDate("Created_Date"));
				feeStaging.setUpdatedBy(rs.getString("Updated_By"));
				feeStaging.setModifiedDate(rs.getDate("Modified_Date"));
				feeStaging.setFeeHeadId(CommonUtil.getLongValue(rs.getLong("FeeHead_Id")));
				return feeStaging;
			}

		});

		return feeStagings;
	}

	public void generateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		String addQuery = feeQueryProps.getProperty("generateStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", studentFeeStaging.getFileNo())
		.addValue("Created_By", studentFeeStaging.getCreatedBy());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void addStudentFeeStaging(StudentFeeStaging studentFeeStaging){
		String addQuery = feeQueryProps.getProperty("addStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", studentFeeStaging.getFileNo())
		.addValue("Created_By", studentFeeStaging.getCreatedBy())
		.addValue("FeeHead_Id", studentFeeStaging.getFeeHeadId())
		.addValue("Amount", studentFeeStaging.getAmount());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		String updateQuery = feeQueryProps.getProperty("updateStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", studentFeeStaging.getFileNo())
		.addValue("FeeHead_Id", studentFeeStaging.getFeeHeadId())
		.addValue("Amount", studentFeeStaging.getAmount())
		.addValue("Updated_By", studentFeeStaging.getUpdatedBy());
	
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}


	public void deleteStudentFeeStaging(StudentFeeStaging feeStaging) {
		
		
		String deleteQuery = feeQueryProps.getProperty("deleteStudentFeeStaging");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", feeStaging.getFileNo())
											.addValue("FeeHead_Id", feeStaging.getFeeHeadId());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	

	//FeeTransaction
	public List<FeeTransaction> getDebitedFeeTransaction(String fileNo) {
		String getQuery = feeQueryProps.getProperty("getFeeTransactionDebit");
		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_No", fileNo);

		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new RowMapper<FeeTransaction>(){

			public FeeTransaction mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeTransaction feeTransaction = new FeeTransaction();
				feeTransaction.setFileNo(rs.getString("File_No"));
				feeTransaction.setUser(rs.getString("User"));
				feeTransaction.setSemester(rs.getInt("Semester"));
				feeTransaction.setMode(rs.getString("Mode"));
				feeTransaction.setComponentId(CommonUtil.getLongValue(rs.getLong("Component_Id")));
				feeTransaction.setRemark(rs.getString("Remark"));
				return feeTransaction;
			}
			
			});
			
			
			return feeTransactions ;
		
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		String addQuery = feeQueryProps.getProperty("addFeeTransactionDebit");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", feeTransaction.getFileNo())
		.addValue("User", feeTransaction.getUser())
		.addValue("Semester", feeTransaction.getSemester())
		.addValue("Component_Id", feeTransaction.getComponentId())
		.addValue("Mode", feeTransaction.getMode())
		.addValue("Remark", feeTransaction.getRemark());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	
	public  List<FeeTransaction> getCreditedFeeTransaction(String fileNo) {
		String getQuery = feeQueryProps.getProperty("getFeeTransactionCredit");
		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_No", fileNo);

		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new RowMapper<FeeTransaction>(){

			public FeeTransaction mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeTransaction feeTransaction = new FeeTransaction();
				feeTransaction.setFileNo(rs.getString("File_No"));
				feeTransaction.setUser(rs.getString("User"));
				feeTransaction.setSemester(rs.getInt("Semester"));
				feeTransaction.setMode(rs.getString("Mode"));
				feeTransaction.setComponentId(CommonUtil.getLongValue(rs.getLong("Component_Id")));
				feeTransaction.setCreatedDate(rs.getDate("Created_Date"));
				feeTransaction.setRemark(rs.getString("Remark"));
				return feeTransaction;
			}

		});

		return feeTransactions;
	}


	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {
		String addQuery = feeQueryProps.getProperty("addFeeTransactionCredit");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", feeTransaction.getFileNo())
		.addValue("User", feeTransaction.getUser())
		.addValue("Created_Date", feeTransaction.getCreatedDate())
		.addValue("Semester", feeTransaction.getSemester())
		.addValue("Component_Id", feeTransaction.getComponentId())
		.addValue("Mode", feeTransaction.getMode())
		.addValue("Remark", feeTransaction.getRemark());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	//FeeDiscountHead

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


}
