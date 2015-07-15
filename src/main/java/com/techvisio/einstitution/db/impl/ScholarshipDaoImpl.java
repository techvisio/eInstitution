package com.techvisio.einstitution.db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.ScholarshipPayment;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.db.ScholarshipDao;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class ScholarshipDaoImpl extends BaseDao implements ScholarshipDao{
	private static CustomLogger logger = CustomLogger.getLogger(ScholarshipDaoImpl.class);	
	@Autowired @Qualifier(value="scholarshipQueryProps")
	private Properties scholarshipQueryProps;

	public void setScholarshipQueryProps(Properties schsolarshipQueryProps) {
		this.scholarshipQueryProps = scholarshipQueryProps;
	}

	@Override
	public List<Scholarship> getScholarship(Long fileNo) {
		String queryString="FROM Scholarship sch WHERE sch.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Scholarship> result= (List<Scholarship>)query.list();
		return result;
	}

	@Override
	public void saveScholarship(List<Scholarship> scholarship) {
		
	}
	
	@Override
	public void saveScholarship(Scholarship scholarship) {

		if(scholarship.getStdntSchlarshpId()==null){
			getCurrentSession().persist(scholarship);
		}
		else{
			getCurrentSession().update(scholarship);
			saveScholarshipPaymentDtl(scholarship.getScholarshipPaymentDetail(), scholarship.getFileNo());
		}
	}

	@Override
	public void deleteScholarshipExlusion(List<Scholarship> scholarships , Long fileNo){
		
		List<Long> scholarshipId = new ArrayList<Long>();
		if (scholarships != null) {
			for (Scholarship scholarship : scholarships) {
				if(scholarship.getStdntSchlarshpId() != null){
				scholarshipId.add(scholarship.getStdntSchlarshpId());
				}
			}
			
			if (scholarshipId.size() == 0) {
				scholarshipId.add(-1L);
			}
		}
		String deleteQuery = scholarshipQueryProps
				.getProperty("deleteScholarshipDetailExclusion");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Stdnt_Schlarshp_Id", scholarshipId)
		.addValue("File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}
	
	@Override
	public List<ScholarshipPayment> getScholarshipPayments(Long fileNo) {

		String queryString="FROM ScholarshipPayment schp WHERE schp.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<ScholarshipPayment> result= query.list();
			return result;
	}

	@Override
	public void saveScholarshipPaymentDtl(List<ScholarshipPayment> scholarshipPaymentDetails, Long fileNo) {

		if(scholarshipPaymentDetails!=null && scholarshipPaymentDetails.size()>0){
			deleteScholarshipPaymentDetailExclusion(scholarshipPaymentDetails, fileNo);
			for(ScholarshipPayment scholarshipPayment : scholarshipPaymentDetails){
				saveScholarshipPaymentDetail(scholarshipPayment);
			}
		}
	}

	@Override
	public void saveScholarshipPaymentDetail(ScholarshipPayment scholarshipPayment) {

		if(scholarshipPayment.getSchlarshpPaymntId()==null){
			getCurrentSession().persist(scholarshipPayment);
		}
		else{
			getCurrentSession().update(scholarshipPayment);
		}
	}

	@Override
	public void deleteScholarshipPaymentDetailExclusion(List<ScholarshipPayment> scholarshipPayments, Long fileNo) {

		List<Long> schlrshpPymntIds = new ArrayList<Long>();
		if (scholarshipPayments != null) {
			for (ScholarshipPayment scholarshipPayment : scholarshipPayments) {
				if(scholarshipPayment.getSchlarshpPaymntId() != null){
					schlrshpPymntIds.add(scholarshipPayment.getSchlarshpPaymntId());
				}
			}
			
			if (schlrshpPymntIds.size() == 0) {
				schlrshpPymntIds.add(-1L);
			}
		}			String deleteQuery = scholarshipQueryProps
					.getProperty("deleteScholarshipPaymentDetailExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Schlarshp_Paymnt_Id", schlrshpPymntIds)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	

}
