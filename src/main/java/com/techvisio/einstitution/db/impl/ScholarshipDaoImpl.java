package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.ScholarshipPaymentDetail;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.util.CommonUtil;

public class ScholarshipDaoImpl extends BaseDao implements ScholarshipDao{

	private Properties scholarshipQueryProps;

	public void setScholarshipQueryProps(Properties scholarshipQueryProps) {
		this.scholarshipQueryProps = scholarshipQueryProps;
	}

	
	public ScholarshipDetail getScholarshipDetail(Long fileNo) {

		ScholarshipDetail scholarshipDetail = null;
		
		try{
		String getQuery = scholarshipQueryProps.getProperty("getScholarshipDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		scholarshipDetail = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<ScholarshipDetail>() {

			public ScholarshipDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ScholarshipDetail scholarshipDetail = new ScholarshipDetail();
				scholarshipDetail.setFileNo(rs.getLong("File_No"));
				scholarshipDetail.setAmount(rs.getDouble("Amount"));
				scholarshipDetail.setStateId(CommonUtil.getLongValue(rs.getLong("State_Id")));
				scholarshipDetail.setCreateDate(rs.getDate("Created_Date"));
				scholarshipDetail.setRemark(rs.getString("Remark"));
				scholarshipDetail.setApproved(rs.getBoolean("Approved"));
			    scholarshipDetail.setReoccurring(rs.getBoolean("Is_Reoccurring"));
				return scholarshipDetail;			
				
			}
		});

			List<ScholarshipPaymentDetail> scholarshipDetails = getScholarshipPaymentDetail(fileNo);
			scholarshipDetail.setScholarshipPaymentDetail(scholarshipDetails);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return scholarshipDetail;

	}

	public void addScholarDetail(ScholarshipDetail scholarshipDetail) {
		
		String upsertQuery = scholarshipQueryProps.getProperty("upsertScholarshipDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", scholarshipDetail.getFileNo())
		.addValue("Amount", scholarshipDetail.getAmount())
		.addValue("State_Id", scholarshipDetail.getStateId())
		.addValue("Created_Date", scholarshipDetail.getCreateDate())
		.addValue("Remark", scholarshipDetail.getRemark())
		.addValue("Approved", scholarshipDetail.isApproved())
		.addValue("Is_Reoccurring", scholarshipDetail.isReoccurring())
		.addValue("Parent_Income", scholarshipDetail.getParentIncome());
		
		
		getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

		if (scholarshipDetail.getScholarshipPaymentDetail() != null) {
			for (ScholarshipPaymentDetail scholarshipPaymentDetail:scholarshipDetail
					.getScholarshipPaymentDetail()) {
				addScholarshipPaymentDetail(scholarshipPaymentDetail);
			}
		}

	}

	
	public void deleteScholarshipDetail(Long fileNo) {
		
         deleteScholarshipPaymentDetail(fileNo);
		
		String deleteQuery = scholarshipQueryProps.getProperty("deleteScholarshipDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

		
	}

	public List<ScholarshipPaymentDetail> getScholarshipPaymentDetail(Long fileNo) {

		String getQuery= scholarshipQueryProps.getProperty("getScholarshipPaymentDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);


		List<ScholarshipPaymentDetail> scholarshipPaymentDetails = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ScholarshipPaymentDetail>() {

			public ScholarshipPaymentDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ScholarshipPaymentDetail scholarshipPaymentDetail=  new ScholarshipPaymentDetail();

				scholarshipPaymentDetail.setFileNo(rs.getLong("File_No"));
				scholarshipPaymentDetail.setAmountReceived(rs.getDouble("Amount_Received"));
				scholarshipPaymentDetail.setReceivingDate(rs.getDate("Receiving_Date"));
				return scholarshipPaymentDetail;
			}
		});

		return scholarshipPaymentDetails;


	}

	public void addScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail) {
		
		String addQuery = scholarshipQueryProps.getProperty("addScholarshipPaymentDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", scholarshipPaymentDetail.getFileNo())
		.addValue("Amount_Received", scholarshipPaymentDetail.getAmountReceived())
		.addValue("Receiving_Date", scholarshipPaymentDetail.getReceivingDate());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		
	}

	public void updateScholarshipPaymentDetail(ScholarshipPaymentDetail scholarshipPaymentDetail) {

		
		String updateQuery = scholarshipQueryProps.getProperty("updateScholarshipPaymentDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", scholarshipPaymentDetail.getFileNo())
		.addValue("Amount_Received", scholarshipPaymentDetail.getAmountReceived())
		.addValue("Receiving_Date", scholarshipPaymentDetail.getReceivingDate());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);


		
	}

	public void deleteScholarshipPaymentDetail(Long fileNo) {
		
		String deleteQuery = scholarshipQueryProps.getProperty("deleteScholarshipPaymentDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

		
	}

}
