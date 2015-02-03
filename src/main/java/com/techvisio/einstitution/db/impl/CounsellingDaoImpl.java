package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.db.CounsellingDao;

public class CounsellingDaoImpl extends BaseDao implements CounsellingDao{

	private Properties counsellingQueryProps;

	public void setCounsellingQueryProps(Properties counsellingQueryProps) {
		this.counsellingQueryProps = counsellingQueryProps ;
	}
	
	
	public CounsellingDetail getCounsellingDetail(String fileNo) {
		String getQuery = counsellingQueryProps.getProperty("getcounsellingDetailByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<CounsellingDetail> counsellingDetails  = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<CounsellingDetail>() {

			public CounsellingDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CounsellingDetail counsellingDetail = new CounsellingDetail();

				counsellingDetail.setFileNo(rs.getString("File_No"));
				counsellingDetail.setCounsellingId(rs.getLong("Counselling_Id"));
				counsellingDetail.setRoll_No(rs.getString("Roll_No"));
				counsellingDetail.setRank(rs.getLong("Rank"));
				counsellingDetail.setCategory_Rank(rs.getLong("Category_Rank"));
				counsellingDetail.setPercentile(rs.getFloat("Percentile"));

				return counsellingDetail;			}
		});

		CounsellingDetail counsellingDetail =  null;

		if(counsellingDetails != null && counsellingDetails.size()>0){

			counsellingDetail = counsellingDetails.get(0);

			}
		
		return counsellingDetail;
		}

	public void addCounsellingDetail(CounsellingDetail counsellingDetail) {

		String addQuery = counsellingQueryProps.getProperty("addCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", counsellingDetail.getFileNo())
		.addValue("Counselling_Id", counsellingDetail.getCounsellingId())
		.addValue("Roll_No", counsellingDetail.getRoll_No())
		.addValue("Rank", counsellingDetail.getRank())
		.addValue("Category_Rank", counsellingDetail.getCategory_Rank())
		.addValue("Percentile", counsellingDetail.getPercentile());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		
		}


	public void updateCounsellingDetail(CounsellingDetail counsellingDetail) {

		String updateQuery = counsellingQueryProps.getProperty("updateCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", counsellingDetail.getFileNo())
		.addValue("Counselling_Id", counsellingDetail.getCounsellingId())
		.addValue("Roll_No", counsellingDetail.getRoll_No())
		.addValue("Rank", counsellingDetail.getRank())
		.addValue("Category_Rank", counsellingDetail.getCategory_Rank())
		.addValue("Percentile", counsellingDetail.getPercentile());
		
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		
	}

	public void deleteConsultantDtl(String fileNo) {

		String deleteQuery = counsellingQueryProps.getProperty("deleteCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		
	}

}
