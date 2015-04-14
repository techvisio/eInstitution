package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.db.ReportDao;

public class ReportDaoImpl extends BaseDao implements ReportDao {

	private Properties reportProperties;

	public void setReportProperties(Properties reportProperties) {
		this.reportProperties = reportProperties;
	}

	@Override
	public List<ConsultantReport> getConsultantReport() {
		
		String getQuery = reportProperties.getProperty("getConsultantReport");
		return getJdbcTemplate().query(getQuery,new ConsultantRowMapper());
	}

	private class ConsultantRowMapper implements RowMapper<ConsultantReport> {

		@Override
		public ConsultantReport mapRow(ResultSet rs, int arg1)
				throws SQLException {
			ConsultantReport cstntRpt = new ConsultantReport();
			if(rs!=null){
				// TODO Map the data here.
			}
			return cstntRpt;
		}

	}
}
