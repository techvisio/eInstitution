package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.w3c.dom.css.CSSRuleList;

import com.techvisio.einstitution.beans.AdmissionReport;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;
import com.techvisio.einstitution.db.ReportDao;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class ReportDaoImpl extends BaseDao implements ReportDao {
	private static CustomLogger logger = CustomLogger.getLogger(ReportDaoImpl.class);
	@Autowired @Qualifier(value="reportQueryProp")
	private Properties reportProperties;

	public void setReportProperties(Properties reportProperties) {
		this.reportProperties = reportProperties;
	}

	@Override
	public List<ConsultantReport> getConsultantReport() {
		logger.info("{} : Get consultant report ",this.getClass().getName());		
		String getQuery = reportProperties.getProperty("getConsultantReport");
		return getJdbcTemplate().query(getQuery,new ConsultantRowMapper());
	}

	private class ConsultantRowMapper implements RowMapper<ConsultantReport> {
		
		@Override
		public ConsultantReport mapRow(ResultSet rs, int arg1)
				throws SQLException {
			logger.info("{} : Putting values in the setter of ConsultantReport bean through rowmapper",this.getClass().getName());	
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

	
	@Override
	public List<EnquiryReport> getEnquiryReportByEnquiryReportCriteria(EnquiryReportCriteria enquiryreportCriteria) {
		logger.info("{} : Get enquiry report by enquiry report criteria ",this.getClass().getName());		
		Date dateFrom = new Date();
		dateFrom = enquiryreportCriteria.getDateFrom(CommonUtil.removeTimeFromDate(dateFrom));
		Date dateTo = new Date();
		dateTo = enquiryreportCriteria.getDateTo(CommonUtil.removeTimeFromDate(dateTo));
		String getQuery = reportProperties.getProperty("getEnquiryReport");
		SqlParameterSource namedParameter = new MapSqlParameterSource("date_From", dateFrom)
											.addValue("date_To",dateTo);
		
		List<EnquiryReport> enquiryReports = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<EnquiryReport>(){

			@Override
			public EnquiryReport mapRow(ResultSet rs, int arg1)
					throws SQLException {
				EnquiryReport enquiryReport = new EnquiryReport();
				if(rs != null){
					enquiryReport.setApplicationStatus(rs.getString("Application_Status"));
					enquiryReport.setBranch(rs.getString("Branch"));
					enquiryReport.setContactNo(rs.getString("Contact_No"));
					enquiryReport.setCourse(rs.getString("Course"));
					enquiryReport.setCreateBy(rs.getString("Created_By"));
					enquiryReport.setCreatedDate(rs.getDate("created_on"));
					enquiryReport.setEmailId(rs.getString("Email_Id"));
					enquiryReport.setEnquiryId(CommonUtil.getLongValue(rs.getLong("Inquiry_Id")));
					enquiryReport.setFatherName(rs.getString("Father_Name"));
					enquiryReport.setName(rs.getString("Name"));
					enquiryReport.setRemarks(rs.getString("Remarks"));
					enquiryReport.setUpdatedBy(rs.getString("Updated_By"));
					enquiryReport.setUpdatedDate(rs.getDate("updated_on"));
				}
				return enquiryReport;
			}
					});
		return enquiryReports;
}

	@Override
	public List<AdmissionReport> getAdmissionReportByReportCriteria(EnquiryReportCriteria enquiryReportCriteria) {
		logger.info("{} : Get admission report by report criteria ",this.getClass().getName());		
		Date dateFrom = new Date();
		dateFrom = enquiryReportCriteria.getDateFrom(CommonUtil.removeTimeFromDate(dateFrom));
		Date dateTo = new Date();
		dateTo = enquiryReportCriteria.getDateTo(CommonUtil.removeTimeFromDate(dateTo));
		String getQuery = reportProperties.getProperty("getAdmissionReport");
		SqlParameterSource namedParameter = new MapSqlParameterSource("date_From", dateFrom)
											.addValue("date_To",dateTo);
		
		List<AdmissionReport> admissionReports = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<AdmissionReport>(){

			@Override
			public AdmissionReport mapRow(ResultSet rs, int arg1)
					throws SQLException {
				AdmissionReport report = new AdmissionReport();
				report.setApplicationStatus(rs.getString("Application_Status"));
				report.setBranch(rs.getString("Branch"));
				report.setConsultantName(rs.getString("consultant_name"));
				report.setCourse(rs.getString("Course"));
				report.setCreatedBy(rs.getString("Created_By"));
				report.setCreatedOn(rs.getDate("created_on"));
				report.setEmailId(rs.getString("Email_Id"));
				report.setFatherName(rs.getString("Father_Name"));
				report.setFirstName(rs.getString("First_Name"));
				report.setGender(rs.getString("Gender"));
				report.setLastName(rs.getString("Last_Name"));
				report.setReferredBy(rs.getString("Referred_By"));
				report.setRegistrationNo(rs.getString("Registration_No"));
				report.setRemarks(rs.getString("Remarks"));
				report.setSelfMobileNo(rs.getString("Self_Mobile_No"));
				report.setDiscountAmount(rs.getDouble("discount_Amount"));
				report.setFeeDeposite(rs.getDouble("Fee_deposite"));
				report.setConsultantPayment(rs.getDouble("consultant_payment"));
				
				return report;
			}
			
			
			
		});
		
		return admissionReports;
	}
}
