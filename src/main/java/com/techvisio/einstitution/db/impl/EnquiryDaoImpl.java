package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.util.CommonUtil;

public class EnquiryDaoImpl extends BaseDao implements EnquiryDao {

	private Properties enquiryQueryProps;

	public void setInquiryQueryProps(Properties inquiryQueryProps) {
		this.enquiryQueryProps = inquiryQueryProps;
	}

	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {

		String getQuery = enquiryQueryProps
				.getProperty("getAdmissionInquiryByTask_date");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Task_Date", taskDate);
		
		List<AdmissionEnquiry> admissionInquiries = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new AdmissionINquiryRowMapper());

		return admissionInquiries;
	}
	
	
	public AdmissionEnquiry getInquiry(Long inquiryId) {

		String getQuery = enquiryQueryProps
				.getProperty("getAdmissionInquiryByInquiryId");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Inquiry_Id", inquiryId);

		List<AdmissionEnquiry> admissionInquiries = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new AdmissionINquiryRowMapper());

		AdmissionEnquiry admissionInquiry=null;

		if(admissionInquiries != null && admissionInquiries.size()>0){
			
			admissionInquiry = admissionInquiries.get(0);
		}

		return admissionInquiry;
	}

	public void addInquiry(AdmissionEnquiry admissionInquiry) {

		String addQuery = enquiryQueryProps.getProperty("addAdmissionInquiry");
		SqlParameterSource namedParameters = getParameterMap(admissionInquiry);
		getNamedParamJdbcTemplate().update(addQuery, namedParameters);

	}


	private MapSqlParameterSource getParameterMap(
			AdmissionEnquiry admissionInquiry) {
		return new MapSqlParameterSource(
				"Inquiry_Id", admissionInquiry.getEnquiryId())
		.addValue("Name", admissionInquiry.getName())
		.addValue("Father_Name", admissionInquiry.getFatherName())
		.addValue("DOB", admissionInquiry.getDob())
		.addValue("Due_Date", admissionInquiry.getDueDate())
		.addValue("Branch_Id",
				admissionInquiry.getBranchId())
		.addValue("Course_Id",
				admissionInquiry.getCourseId())
		.addValue("Created_On", admissionInquiry.getCreatedDate())
		.addValue("Created_By", admissionInquiry.getCreateBy())
		.addValue("Updated_On", admissionInquiry.getUpdatedDate())
		.addValue("Updated_By", admissionInquiry.getUpdatedBy())
		.addValue("Contact_No", admissionInquiry.getContactNo())
		.addValue("FollowUp_Required",
				admissionInquiry.isFollowupRequired())
		.addValue("Application_Status",
				admissionInquiry.getApplicationStatus())
				.addValue("Remarks", admissionInquiry.getRemarks())
				.addValue("File_No", admissionInquiry.getFileNo())
				.addValue("Gender", admissionInquiry.getGender())
				.addValue("Lateral", admissionInquiry.isLateral())
				.addValue("Email_Id", admissionInquiry.getEmailId());
	}

	public void updateInquiry(AdmissionEnquiry admissionInquiry) {

		String updateQuery = enquiryQueryProps
				.getProperty("updateAdmissionInquiry");
		SqlParameterSource namedParameters = getParameterMap(admissionInquiry);
		getNamedParamJdbcTemplate().update(updateQuery, namedParameters);
	}

	public void deleteInquiry(Long inquiryId) {

		String deleteQuery = enquiryQueryProps
				.getProperty("deleteAdmissionInquiry");

		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"Inquiry_Id",inquiryId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameters);
	}

	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(
			SearchCriteria searchCriteria) {
		String getQuery = enquiryQueryProps
				.getProperty("searchInquiry");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Inquiry_Id", searchCriteria.getInquryId()==null?null:searchCriteria.getInquryId())
		.addValue("Email_Id", StringUtils.isEmpty(searchCriteria.getEmailId())?null:searchCriteria.getEmailId())
		.addValue("Name", StringUtils.isEmpty(searchCriteria.getName())?"%":searchCriteria.getName()+"%")
		.addValue("Phone_No", StringUtils.isEmpty(searchCriteria.getMobileNo())?null:searchCriteria.getMobileNo())
		.addValue("Course_Id", searchCriteria.getCourseId()==null?null:searchCriteria.getCourseId())
		.addValue("Branch_Id", searchCriteria.getBranchId()==null?null:searchCriteria.getBranchId())
		.addValue("status", searchCriteria.getStatus()==null?null:searchCriteria.getStatus());
		
		List<AdmissionEnquiry> admissionInquiries=getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new AdmissionINquiryRowMapper());
		
		    return admissionInquiries;
	}
	
	private class AdmissionINquiryRowMapper implements RowMapper<AdmissionEnquiry>
	{

		@Override
				public AdmissionEnquiry mapRow(ResultSet rs,
						int rowNum) throws SQLException {

					AdmissionEnquiry admissionInquiry = new AdmissionEnquiry();
					admissionInquiry.setEnquiryId(CommonUtil.getLongValue(rs
							.getLong("Inquiry_Id")));
					admissionInquiry.setName(rs.getString("Name"));
					admissionInquiry.setFatherName(rs
							.getString("Father_Name"));
					admissionInquiry.setDob(rs.getDate("DOB"));
					admissionInquiry.setContactNo(rs
							.getString("Contact_No"));
					admissionInquiry.setEmailId(rs.getString("Email_Id"));
					admissionInquiry.setApplicationStatus(rs
							.getString("Application_Status"));
					admissionInquiry.setDueDate(rs
							.getDate("Due_Date"));
					admissionInquiry.setCreateBy(rs
							.getString("Created_By"));
					admissionInquiry.setCreatedDate(rs
							.getDate("Created_On"));
					admissionInquiry.setUpdatedBy(rs
							.getString("Updated_By"));
					admissionInquiry.setUpdatedDate(rs
							.getDate("Updated_On"));
					admissionInquiry.setCourseId(CommonUtil.getLongValue(rs
							.getLong("Course_Id")));
					admissionInquiry.setBranchId(CommonUtil.getLongValue(rs
							.getLong("Branch_Id")));
					admissionInquiry.setFollowupRequired(rs
							.getBoolean("FollowUp_Required"));
					admissionInquiry.setFileNo(rs.getString("File_No"));
					admissionInquiry.setRemarks(rs.getString("Remarks"));
					admissionInquiry.setEmailId(rs.getString("Email_Id"));
					admissionInquiry.setLateral(rs.getBoolean("Lateral"));
					admissionInquiry.setGender(rs.getString("Gender"));
					return admissionInquiry;
		}
		
	}

	

}
