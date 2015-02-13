package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;

import com.techvisio.einstitution.beans.FeeDetail;
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
	
}
