package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
//github.com/techvisio/eInstitution
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class EnquiryDaoImpl extends BaseDao implements EnquiryDao {

	private static CustomLogger logger = CustomLogger.getLogger(EnquiryDaoImpl.class);
	@Autowired
	CacheManager cacheManager;

	@Autowired @Qualifier(value="enquiryQueryProps")
	private Properties enquiryQueryProps;

	public void setEnquiryQueryProps(Properties inquiryQueryProps) {
		this.enquiryQueryProps = inquiryQueryProps;
	} 

//	public List<AdmissionEnquiry> getInquiryByTaskDate(Date taskDate) {
//		logger.info("{} : Getting enquiry by task date date:{}",this.getClass().getName(), taskDate);
//		String getQuery = enquiryQueryProps
//				.getProperty("getAdmissionInquiryByTask_date");
//
//		SqlParameterSource namedParameter = new MapSqlParameterSource(
//				"Task_Date", taskDate);
//
//		List<AdmissionEnquiry> admissionInquiries = getNamedParamJdbcTemplate()
//				.query(getQuery, namedParameter,
//						new AdmissionINquiryRowMapper());
//
//		return admissionInquiries;
//	}


	public AdmissionEnquiry getInquiry(Long inquiryId) {
		String queryString="FROM AdmissionEnquiry ae WHERE ae.enquiryId = "+inquiryId;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<AdmissionEnquiry> result= (List<AdmissionEnquiry>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	public Long saveInquiry(AdmissionEnquiry admissionInquiry) {
		if(admissionInquiry.getEnquiryId()==null){
			getCurrentSession().persist(admissionInquiry);
		}
		else{
			getCurrentSession().update(admissionInquiry);
		}
		
		getCurrentSession().flush();
		return admissionInquiry.getEnquiryId();
	}
	
	@Override
	public List<AdmissionEnquiry> searchInqByCriteria(SearchCriteria searchCriteria) {
		 logger.info("{} : Searching enquiry by searching criteria for Student{}, who has enquiry id{}  ",this.getClass().getName(), searchCriteria.getName(), searchCriteria.getInquryId());
		
		String getQuery = enquiryQueryProps
				.getProperty("searchInquiry");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Enquiry_Id", searchCriteria.getInquryId()==null?null:searchCriteria.getInquryId())
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
			 logger.info("{} : Setting values in setter of AdmissionEnquiry bean through rowmapper  ",this.getClass().getName());
					AdmissionEnquiry admissionInquiry = new AdmissionEnquiry();
					admissionInquiry.setEnquiryId(CommonUtil.getLongValue(rs
							.getLong("Enquiry_Id")));
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
					admissionInquiry.setCreatedBy(rs
							.getString("Created_By"));
					admissionInquiry.setCreatedOn(rs
							.getDate("Created_On"));
					admissionInquiry.setUpdatedBy(rs
							.getString("Updated_By"));
					admissionInquiry.setUpdatedOn(rs
							.getDate("Updated_On"));
					Long branchId=(CommonUtil.getLongValue(rs.getLong("Branch_Id")));
				    Branch branch=cacheManager.getBranchById(branchId);
					admissionInquiry.setBranch(branch);
					
					Long courseId=(CommonUtil.getLongValue(rs.getLong("Course_Id")));
				    Course course=cacheManager.getCourseById(courseId);
					admissionInquiry.setCourse(course);
					admissionInquiry.setFollowupRequired(rs
							.getBoolean("FollowUp_Required"));
					admissionInquiry.setFileNo(rs.getLong("File_No"));
					admissionInquiry.setRemarks(rs.getString("Remarks"));
					admissionInquiry.setEmailId(rs.getString("Email_Id"));
					admissionInquiry.setLateral(rs.getBoolean("Lateral"));
					admissionInquiry.setGender(rs.getString("Gender"));
					if(admissionInquiry.getConsultant()!=null){
					admissionInquiry.getConsultant().setConsultantId((CommonUtil.getLongValue(rs.getLong("Consultant_Id"))));}
					admissionInquiry.setReferredBy(rs.getString("Referred_By"));
					admissionInquiry.setAdmissionMode(rs.getString("Admission_Mode"));
					if(admissionInquiry.getCategory()!=null){
					admissionInquiry.getCategory().setCategoryId((CommonUtil.getLongValue(rs.getLong("Category_Id"))));}
					admissionInquiry.setRegistrationNo(rs.getString("Registration_No"));
					return admissionInquiry;
		}
		
	}
}
