package com.techvisio.einstitution.db.impl;

import java.util.Properties;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.db.InquiryDao;

public class InquiryDaoImpl extends BaseDao implements InquiryDao {

	
	private Properties inquiryQueryProps;
	
	
	public void setInquiryQueryProps(Properties inquiryQueryProps) {
		this.inquiryQueryProps = inquiryQueryProps;
	}


	public void getInquiry(AdmissionEnquiry admissionInquiry) {

		
		 
	
	
	} 
	

	public void addInquiry(AdmissionEnquiry admissionInquiry) {
		
		String addQuery = inquiryQueryProps.getProperty("addAdmissionInquiry");	
		 
		 SqlParameterSource namedParameters=new MapSqlParameterSource("Inquiry_Id",admissionInquiry.getEnquiryId())
		  .addValue("Name",admissionInquiry.getName())
		  .addValue("Father_Name",admissionInquiry.getFatherName())
		  .addValue("DOB",admissionInquiry.getDob())
		  .addValue("Due_Date",admissionInquiry.getDueDate())
		  .addValue("Intrested_Branch_Id",admissionInquiry.getIntrestedBranchId())
		  .addValue("Intrested_Course_Id",admissionInquiry.getIntrestedCourseId())
		  .addValue("Created_On",admissionInquiry.getCreatedDate())
		  .addValue("Created_By",admissionInquiry.getCreateBy())
		  .addValue("Updated_On",admissionInquiry.getUpdatedDate())
		  .addValue("Updated_By",admissionInquiry.getUpdatedBy())
		  .addValue("Contact_No",admissionInquiry.getContactNo())
		  .addValue("FollowUp_Rquired",admissionInquiry.getFollowupRequired())
		  .addValue("Application_Status", admissionInquiry.getApplicationStatus());
		  	 
		  getNamedParamJdbcTemplate().update(addQuery, namedParameters);
	
	
	}

	public void updateInquiry(AdmissionEnquiry admissionInquiry) {
		
		String updateQuery = inquiryQueryProps.getProperty("updateAdmissionInquiry");	
	    
		 SqlParameterSource namedParameters=new MapSqlParameterSource("Inquiry_Id",admissionInquiry.getEnquiryId())
		  .addValue("Name",admissionInquiry.getName())
		  .addValue("Father_Name",admissionInquiry.getFatherName())
		  .addValue("DOB",admissionInquiry.getDob())
		  .addValue("Due_Date",admissionInquiry.getDueDate())
		  .addValue("Intrested_Branch_Id",admissionInquiry.getIntrestedBranchId())
		  .addValue("Intrested_Course_Id",admissionInquiry.getIntrestedCourseId())
		  .addValue("Created_On",admissionInquiry.getCreatedDate())
		  .addValue("Created_By",admissionInquiry.getCreateBy())
		  .addValue("Updated_On",admissionInquiry.getUpdatedDate())
		  .addValue("Updated_By",admissionInquiry.getUpdatedBy())
		  .addValue("Contact_No",admissionInquiry.getContactNo())
		  .addValue("FollowUp_Rquired",admissionInquiry.getFollowupRequired())
		  .addValue("Application_Status", admissionInquiry.getApplicationStatus());
		 
		 getNamedParamJdbcTemplate().update(updateQuery, namedParameters);
	}

	public void deleteInquiry(AdmissionEnquiry admissionInquiry) {

		String deleteQuery = inquiryQueryProps.getProperty("deleteAdmissionInquiry");	
	    
		 SqlParameterSource namedParameters=new MapSqlParameterSource("Inquiry_Id",admissionInquiry.getEnquiryId());
	
		 getNamedParamJdbcTemplate().update(deleteQuery, namedParameters);		
	}

}


