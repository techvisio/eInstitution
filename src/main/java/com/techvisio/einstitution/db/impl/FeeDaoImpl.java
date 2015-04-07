package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.impl.CacheManagerImpl;
import com.techvisio.einstitution.util.CommonUtil;

public class FeeDaoImpl extends BaseDao implements FeeDao{

	private Properties feeQueryProps;


	public void setFeeQueryProps(Properties feeQueryProps) {
		this.feeQueryProps = feeQueryProps;
	}

	CacheManager cacheManager =  new CacheManagerImpl(); 

	@Override
	public List<FeeAdmissionBean> getPendingfeeInfo(int limit) {
		//StudentfeeAdmissionBean info = new StudentfeeAdmissionBean();
		
		String getQuery = feeQueryProps.getProperty("getPendingFee");
		SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
		
		List<FeeAdmissionBean> pendingFeeInfos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new RowMapper<FeeAdmissionBean>() {

			@Override
			public FeeAdmissionBean mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				FeeAdmissionBean feeAdmissionBean = new FeeAdmissionBean();
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
			e.printStackTrace();
		}
		return previousBalance;
		
		
	}
	
	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetails(ApplicableFeeCriteria criteria) {
		String getFeeDetailQuery=feeQueryProps.getProperty("getFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("COURSE", criteria.getCourseId())
		.addValue("BRANCH", criteria.getBranchId())
		.addValue("SESSION_ID", criteria.getSessionId())
		.addValue("SHIFT_ID", criteria.getShiftId())
		.addValue("CENTRE_ID", criteria.getCentreId());
		List<ApplicableFeeDetail> feeDetails = getNamedParamJdbcTemplate().query(getFeeDetailQuery, namedParameter, new RowMapper<ApplicableFeeDetail>(){

			public ApplicableFeeDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ApplicableFeeDetail detail = new ApplicableFeeDetail();
				detail.setBranch(CommonUtil.getLongValue(rs.getLong("BRANCH")));
				detail.setCourse(CommonUtil.getLongValue(rs.getLong("COURSE")));
				detail.setFeeAmount(rs.getDouble("FEE_AMOUNT"));
				Long feeId=(CommonUtil.getLongValue(rs.getLong("FEE_HEAD_ID")));
				FeeDiscountHead feeDiscountHead=cacheManager.getFeeDiscountById(feeId);
				detail.setFeeDetail(feeDiscountHead);
				//detail.setSemester(rs.getInt("SEMESTER"));
				detail.setCentreId(CommonUtil.getLongValue(rs.getLong("Centre_Id")));
				detail.setShiftId(CommonUtil.getLongValue(rs.getLong("Shift_Id")));
				detail.setSessionId(CommonUtil.getLongValue(rs.getLong("Session_Id")));
		
				return detail;
			}

		});

		Iterator<ApplicableFeeDetail> feeItr=feeDetails.iterator();
		while(feeItr.hasNext()){
			ApplicableFeeDetail fee=feeItr.next();
			if(!criteria.isLateral() && "L".equalsIgnoreCase(fee.getFeeDetail().getTransactionType())){
				feeItr.remove();
			}
		}

		return feeDetails;

	}

	public void addFeeDetail(ApplicableFeeDetail feeDetail) {
		String addFeeDetailQuery=feeQueryProps.getProperty("addFeeDetailMaster");
		SqlParameterSource namedParameter = getParameterMap(feeDetail);
		getNamedParamJdbcTemplate().update(addFeeDetailQuery, namedParameter);
	}

	private MapSqlParameterSource getParameterMap(ApplicableFeeDetail feeDetail){
		return new MapSqlParameterSource("FEE_HEAD_ID",feeDetail.getFeeDetail().getHeadId())
		.addValue("COURSE",feeDetail.getCourse())
		//.addValue("SEMESTER", feeDetail.getSemester())
		.addValue("FEE_AMOUNT", feeDetail.getFeeAmount())
		.addValue("BRANCH", feeDetail.getBranch())
		.addValue("Session_Id", feeDetail.getSessionId())
		.addValue("Shift_Id", feeDetail.getShiftId())
		.addValue("Centre_Id", feeDetail.getCentreId());
		
	}
	public void updateFeeDetail(ApplicableFeeDetail feeDetail) {
		String updateFeeDetailQuery=feeQueryProps.getProperty("updateFeeDetailMaster");
		SqlParameterSource namedParameter = getParameterMap(feeDetail);
		getNamedParamJdbcTemplate().update(updateFeeDetailQuery, namedParameter);
	}


	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId) {
		String deleteFeeDetailQuery=feeQueryProps.getProperty("deleteFeeDetailMaster");
		SqlParameterSource namedParameter = new MapSqlParameterSource("COURSE", course)
		.addValue("BRANCH", branch).addValue("FEE_HEAD_ID", feeHeadId);

		getNamedParamJdbcTemplate().update(deleteFeeDetailQuery, namedParameter);

	}

	//	StudentFeeStaging

	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId) {

		String getQuery = feeQueryProps.getProperty("getStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo).addValue("FeeHead_Id", feeHeadId);

		List<StudentFeeStaging> feeStagings = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<StudentFeeStaging>(){
			public StudentFeeStaging mapRow(ResultSet rs, int rowNum)throws SQLException {
				StudentFeeStaging feeStaging = new StudentFeeStaging();
				feeStaging.setFeeGenerated(rs.getBoolean("Fee_Generated"));
				feeStaging.setFileNo(rs.getLong("File_No"));
				feeStaging.setAmount(rs.getDouble("Amount"));
				feeStaging.setApproved(rs.getBoolean("Approved"));
				feeStaging.setCreatedBy(rs.getString("Created_By"));
				feeStaging.setCreatedDate(rs.getDate("Created_Date"));
				feeStaging.setUpdatedBy(rs.getString("Updated_By"));
				feeStaging.setModifiedDate(rs.getDate("Modified_Date"));
				Long feeId=(CommonUtil.getLongValue(rs.getLong("FeeHead_Id")));
			    FeeDiscountHead feeDiscountHead=cacheManager.getFeeDiscountById(feeId);
				feeStaging.setDiscountHead(feeDiscountHead);
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
		.addValue("FeeHead_Id", studentFeeStaging.getDiscountHead().getHeadId())
		.addValue("Amount", studentFeeStaging.getAmount())
		.addValue("Fee_Generated", studentFeeStaging.isFeeGenerated())
		.addValue("Approved", studentFeeStaging.isApproved());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateStudentFeeStaging(StudentFeeStaging studentFeeStaging) {
		String updateQuery = feeQueryProps.getProperty("updateStudentFeeStaging");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", studentFeeStaging.getFileNo())
		.addValue("FeeHead_Id", studentFeeStaging.getDiscountHead().getHeadId())
		.addValue("Amount", studentFeeStaging.getAmount())
		.addValue("Updated_By", studentFeeStaging.getUpdatedBy())
		.addValue("Fee_Generated", studentFeeStaging.isFeeGenerated())
		.addValue("Approved", studentFeeStaging.isApproved());
	
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	public void updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings) {
		
		if(studentFeeStagings != null){
		
		for(StudentFeeStaging feeStaging : studentFeeStagings){
			updateStudentFeeStaging(feeStaging);
		}
		}
	}


	public void deleteStudentFeeStaging(StudentFeeStaging feeStaging) {
		
		
		String deleteQuery = feeQueryProps.getProperty("deleteStudentFeeStaging");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", feeStaging.getFileNo())
											.addValue("FeeHead_Id", feeStaging.getDiscountHead().getHeadId());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	

	//FeeTransaction
	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo) {
		String getQuery = feeQueryProps.getProperty("getFeeTransactionDebit");
		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_No", fileNo);

		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new RowMapper<FeeTransaction>(){

			public FeeTransaction mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeTransaction feeTransaction = new FeeTransaction();
				feeTransaction.setFileNo(rs.getLong("File_No"));
				feeTransaction.setUser(rs.getString("User"));
				feeTransaction.setBatchId(CommonUtil.getLongValue(rs.getLong("Batch_Id")));
				feeTransaction.setSessionId(CommonUtil.getLongValue(rs.getLong("Session_Id")));
				feeTransaction.setMode(rs.getString("Mode"));
				Long componentId=(CommonUtil.getLongValue(rs.getLong("Component_Id")));
				FeeDiscountHead feeDiscountHead = cacheManager.getFeeDiscountById(componentId);
				feeTransaction.setFeeDiscountHead(feeDiscountHead);
				feeTransaction.setRemark(rs.getString("Remark"));
				feeTransaction.setAmount(rs.getDouble("Amount"));
				return feeTransaction;
			}
			
			});
			
	
			return feeTransactions ;
		
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		String addQuery = feeQueryProps.getProperty("addFeeTransactionDebit");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", feeTransaction.getFileNo())
		.addValue("User", feeTransaction.getUser())
		.addValue("Batch_Id", feeTransaction.getBatchId())
		.addValue("Session_Id", feeTransaction.getSessionId() )
		.addValue("Component_Id", feeTransaction.getFeeDiscountHead().getHeadId())
		.addValue("Mode", feeTransaction.getMode())
		.addValue("Remark", feeTransaction.getRemark())
		.addValue("Amount", feeTransaction.getAmount());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
				
	}

	
	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {
		String getQuery = feeQueryProps.getProperty("getFeeTransactionCredit");
		SqlParameterSource namedSqlParameter = new MapSqlParameterSource("File_No", fileNo);

		List<FeeTransaction> feeTransactions = getNamedParamJdbcTemplate().query(getQuery, namedSqlParameter, new RowMapper<FeeTransaction>(){

			public FeeTransaction mapRow(ResultSet rs, int rowNum)throws SQLException {
				FeeTransaction feeTransaction = new FeeTransaction();
				feeTransaction.setFileNo(rs.getLong("File_No"));
				feeTransaction.setUser(rs.getString("User"));
				feeTransaction.setBatchId(CommonUtil.getLongValue(rs.getLong("Batch_Id")));
				feeTransaction.setSessionId(CommonUtil.getLongValue(rs.getLong("Session_Id")));
				feeTransaction.setMode(rs.getString("Mode"));
				Long componentId=(CommonUtil.getLongValue(rs.getLong("Component_Id")));
				FeeDiscountHead feeDiscountHead = cacheManager.getFeeDiscountById(componentId);		
				feeTransaction.setFeeDiscountHead(feeDiscountHead);
				feeTransaction.setCreatedDate(rs.getDate("Created_Date"));
				feeTransaction.setRemark(rs.getString("Remark"));
				feeTransaction.setAmount(rs.getDouble("Amount"));
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
		.addValue("Batch_Id",feeTransaction.getBatchId())
		.addValue("Session_Id", feeTransaction.getSessionId())
		.addValue("Component_Id", feeTransaction.getFeeDiscountHead().getHeadId())
		.addValue("Mode", feeTransaction.getMode())
		.addValue("Remark", feeTransaction.getRemark())
		.addValue("Amount", feeTransaction.getAmount());

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
				feeDiscountHead.setTransactionType(rs.getString("Transaction_Type"));
				feeDiscountHead.setReoccurring(rs.getBoolean("isReoccurring"));
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
		.addValue("Transaction_Type", feeDiscountHead.getTransactionType())
		.addValue("isReoccurring", feeDiscountHead.isReoccurring()) 
		.addValue("Refund_Type", feeDiscountHead.getRefundType());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {

		String updateQuery = feeQueryProps.getProperty("updateFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id", feeDiscountHead.getHeadId())
		.addValue("Parent_Type_Id", feeDiscountHead.getParentId())
		.addValue("Head", feeDiscountHead.getHead())
		.addValue("Transaction_Type", feeDiscountHead.getTransactionType())
		.addValue("isReoccurring", feeDiscountHead.isReoccurring())
		.addValue("Refund_Type", feeDiscountHead.getRefundType());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}

	public void deleteFeeDiscountHead(Long headId) {

		String deleteQuery = feeQueryProps.getProperty("deleteFeeDiscountHead");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Head_Id",headId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

}
