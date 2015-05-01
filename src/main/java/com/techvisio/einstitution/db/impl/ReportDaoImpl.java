package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.w3c.dom.css.CSSRuleList;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.db.ReportDao;
import com.techvisio.einstitution.util.CommonUtil;
@Component
public class ReportDaoImpl extends BaseDao implements ReportDao {
	
	@Autowired
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
				cstntRpt.setBranchName(rs.getString("Branch"));
				cstntRpt.setConsultantId(CommonUtil.getLongValue(rs.getLong("Id")));
				cstntRpt.setConsultantName(rs.getString("Name"));
				cstntRpt.setCourse(rs.getString("Course"));
				cstntRpt.setDescription(rs.getString("Description"));
				cstntRpt.setDob(rs.getDate("DOB"));
				cstntRpt.setEmailId(rs.getString("Email_Id"));
				cstntRpt.setFatherName(rs.getString("Father_Name"));
				cstntRpt.setFeePaid(rs.getBoolean("Fee_Paid"));
				cstntRpt.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
				cstntRpt.setFirstName(rs.getString("First_Name"));
				cstntRpt.setGender(rs.getString("Gender"));
				cstntRpt.setLastName(rs.getString("Last_Name"));
				cstntRpt.setPrimaryContactNo(rs.getString("Primary_Contact_No"));
				cstntRpt.setSecondaryContactNo(rs.getString("Secondary_contact_No"));
				cstntRpt.setSelfMobileNo(rs.getString("Self_Mobile_No"));
			}
			return cstntRpt;
		}

	}
}
