package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

import com.techvisio.einstitution.beans.AdmissionEnquiry;
//github.com/techvisio/eInstitution
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.EnquiryDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.impl.CacheManagerImpl;
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
		String queryString="FROM AdmissionEnquiry ae WHERE ae.inquiryId = "+inquiryId;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<AdmissionEnquiry> result= (List<AdmissionEnquiry>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	public void saveInquiry(AdmissionEnquiry admissionInquiry) {
		if(admissionInquiry.getEnquiryId()==null){
			getCurrentSession().persist(admissionInquiry);
		}
		else{
			getCurrentSession().update(admissionInquiry);
		}
	}
}
