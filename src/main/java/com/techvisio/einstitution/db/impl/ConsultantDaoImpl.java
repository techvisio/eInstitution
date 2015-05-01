package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
@Component
public class ConsultantDaoImpl extends BaseDao implements ConsultantDao {
	@Autowired
	private Properties consultantQueryProps;

	public void setConsultantQueryProps(Properties consultantQueryProps) {
		this.consultantQueryProps = consultantQueryProps;
	}

	@Autowired
	CacheManager cacheManager;
		
	
	public Consultant getConsultant(Long consultantId) {

		String getQuery = consultantQueryProps.getProperty("getConsultantById");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Id", consultantId);

		List<Consultant> consultants = getNamedParamJdbcTemplate().query(getQuery,namedParameter, new ConsultantRowMapper());
		Consultant consultant =  null;

		if(consultants != null && consultants.size()>0){

			consultant = consultants.get(0);		}

		return consultant;

	}



	public void saveConsultant(Consultant consultant) {

		String upsertQuery = consultantQueryProps.getProperty("upsertConsultant");
		
		if(consultant.getConsultantId() != null){
		
		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultant.getConsultantId())
		.addValue("Name", consultant.getName())
		.addValue("Primary_Contact_No", consultant.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultant.getSecondaryContactNo())
		.addValue("Address", consultant.getAddress())
		.addValue("Email_Id", consultant.getEmailId());

		getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);
		}
		
		//refresh cache
		//CacheManager cacheManager=new CacheManagerImpl();
		cacheManager.refreshCacheList(AppConstants.CONSULTANT);
	}

/*	public void updateConsultant(Consultant consultant) {

		String updateQuery = consultantQueryProps.getProperty("updateConsultant");

		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultant.getConsultantId())
		.addValue("Name", consultant.getName())
		.addValue("Primary_Contact_No", consultant.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultant.getSecondaryContactNo())
		.addValue("Address", consultant.getAddress())
		.addValue("Email_Id", consultant.getEmailId());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}
*/
	public void deleteConsultant(
			Long consultantId) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultant");

		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultantId);


		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public List<ConsultantDetail> getConsultantDtl(final Long fileNo) {

		String getQuery = consultantQueryProps.getProperty("getConsultantDtlByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<ConsultantDetail> consultantDetails  = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantDetail>() {

			public ConsultantDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ConsultantDetail consultantDetail = new ConsultantDetail();

				Long consultantId=(CommonUtil.getLongValue(rs.getLong("Consultant_Id")));
				Consultant consultant= cacheManager.getConsultantId(consultantId);
                consultantDetail.setConsultant(consultant);
				consultantDetail.setFileNo(rs.getLong("File_No"));
				consultantDetail.setAmountToPay(rs.getDouble("Amount_To_Pay"));
				consultantDetail.setConsultancyAgreed(rs.getBoolean("Consultancy_Agreed"));
				consultantDetail.setPaymentMode(rs.getString("Payment_Mode"));
				consultantDetail.setDueDate(rs.getDate("Due_Date"));
				consultantDetail.setRemarks(rs.getString("Remarks"));
				
                consultantId = consultantDetail.getConsultant().getConsultantId();
				List<ConsultantPaymentDtl> consultantPaymentDtls = getConsultantPaymentDtl(fileNo,consultantId);
				consultantDetail.setConsultantPaymentDetail(consultantPaymentDtls);
            
				List<ConsultantPaymentCriteria> consultantPaymentCriterias = getConsultantPaymentCriteria(fileNo, consultantId);
				consultantDetail.setConsultantPaymentCriterias(consultantPaymentCriterias);
				return consultantDetail;			}
		});

		return consultantDetails;
	}

	public void saveConsultantDetail(List<ConsultantDetail> consultantDetails){
		
		if(consultantDetails != null){
		for(ConsultantDetail consultantDetail:consultantDetails){
	
			Long fileNo = consultantDetail.getFileNo();
			deleteConsultantDtlExclusion(fileNo, consultantDetails);
		
			addConsultantDtl(consultantDetail);
		}
	}
	}
	private void addConsultantDtl(ConsultantDetail consultantDetail) {
		
		String upsertQuery = consultantQueryProps.getProperty("upsertConsultantDtl");
		if(consultantDetail.getConsultant()!=null && consultantDetail.getConsultant().getConsultantId()!=null){
			SqlParameterSource namedParameter =  new MapSqlParameterSource("Consultant_Id", consultantDetail.getConsultant().getConsultantId())
			.addValue("File_No", consultantDetail.getFileNo())
			.addValue("Consultancy_Agreed", consultantDetail.isConsultancyAgreed())
			.addValue("Payment_Mode", consultantDetail.getPaymentMode())
			.addValue("Amount_To_Pay", consultantDetail.getAmountToPay())
			.addValue("Due_Date", consultantDetail.getDueDate())
			.addValue("Remarks", consultantDetail.getRemarks());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

			if (consultantDetail.getConsultantPaymentDetail() != null) {
				for (ConsultantPaymentDtl consultantPaymentDtl:consultantDetail.getConsultantPaymentDetail()) {
			         consultantPaymentDtl.setFileNo(consultantDetail.getFileNo());
			         consultantPaymentDtl.setConsultantId(consultantDetail.getConsultant().getConsultantId());
					 deleteConsultantPaymentCriteria(consultantDetail.getFileNo(), consultantDetail.getConsultant().getConsultantId());
			         addConsultantPaymentDtl(consultantPaymentDtl);
				}
			}
			
			if(consultantDetail.getConsultantPaymentCriterias() != null){
				
				for(ConsultantPaymentCriteria consultantPaymentCriteria : consultantDetail.getConsultantPaymentCriterias()){
					
					consultantPaymentCriteria.setConsultantId(consultantDetail.getConsultant().getConsultantId());
					consultantPaymentCriteria.setFileNo(consultantDetail.getFileNo());
					deleteConsultantPaymentCriteria(consultantDetail.getFileNo(), consultantDetail.getConsultant().getConsultantId());
					addConsultantPaymentCriteria(consultantPaymentCriteria);
				}
			}
		}
	}


	public void deleteConsultantDtlExclusion(Long fileNo, List<ConsultantDetail> consultantDetails) {
	
		List<Long> consultantIds=new ArrayList<Long>();
		
		if(consultantDetails != null){
			for(ConsultantDetail consultantDetail:consultantDetails){
				if(consultantDetail.getConsultant() !=null && consultantDetail.getConsultant().getConsultantId()!=null){
				consultantIds.add(consultantDetail.getConsultant().getConsultantId());
		
				deleteConsultantPaymentDtl(fileNo, consultantDetail.getConsultant().getConsultantId());

				deleteConsultantPaymentCriteria(fileNo, consultantDetail.getConsultant().getConsultantId());

				}
			}
		
		}
			
		if(consultantIds == null || consultantIds.size()==0){
				consultantIds.add(-1L);
		}
		
				
		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantDtl");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantIds);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public List<ConsultantPaymentDtl> getConsultantPaymentDtl(Long fileNo, Long consultantId) {

		String getQuery= consultantQueryProps.getProperty("getConsultantPaymentDtlByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);


		List<ConsultantPaymentDtl> consultantPaymentDtls = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantPaymentDtl>() {

			public ConsultantPaymentDtl mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ConsultantPaymentDtl consultantPaymentDtl=  new ConsultantPaymentDtl();

				consultantPaymentDtl.setAmount(rs.getDouble("Amount"));
				consultantPaymentDtl.setFileNo(rs.getLong("File_No"));
				consultantPaymentDtl.setPayDate(rs.getDate("Pay_Date"));

				return consultantPaymentDtl;
			}
		});

		return consultantPaymentDtls;
	}

	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {

		String addQuery = consultantQueryProps.getProperty("addConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", consultantPaymentDtl.getFileNo())
		.addValue("Amount", consultantPaymentDtl.getAmount())
		.addValue("Pay_Date", consultantPaymentDtl.getPayDate())
		.addValue("Consultant_Id", consultantPaymentDtl.getConsultantId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {

			deleteConsultantPaymentDtl(consultantPaymentDtl.getFileNo(), consultantPaymentDtl.getConsultantId());
			addConsultantPaymentDtl(consultantPaymentDtl);
//		String updateQuery = consultantQueryProps.getProperty("updateConsultantPaymentDtl");
//
//		SqlParameterSource namedParameter = new MapSqlParameterSource("FileNo", consultantPaymentDtl.getFileNo())
//		.addValue("Amount", consultantPaymentDtl.getAmount())
//		.addValue("Pay_Date", consultantPaymentDtl.getPayDate());
//
//		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
//

	}


	public void deleteConsultantPaymentDtl(Long fileNo, Long consultantId) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	public void deleteConsultantPaymentDtlExclusion(Long fileNo, List<Long> consultantIds) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtlExclusion");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}


	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo, Long consultantId) {

		String getQuery = consultantQueryProps.getProperty("getConsultantPaymentCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);

		List<ConsultantPaymentCriteria> consultantPaymentCriterias= getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantPaymentCriteria>() {

			@Override
			public ConsultantPaymentCriteria mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				ConsultantPaymentCriteria consultantPaymentCriteria = new ConsultantPaymentCriteria();

				consultantPaymentCriteria.setFileNo(rs.getLong("File_No"));
				consultantPaymentCriteria.setConsultantId(CommonUtil.getLongValue(rs.getLong("Consultant_Id")));
				consultantPaymentCriteria.setFeeReceived(rs.getDouble("Fee_Received"));
				consultantPaymentCriteria.setAmountToBePaid(rs.getDouble("Amount_To_Be_Paid"));
				consultantPaymentCriteria.setFeeDueDate(rs.getDate("Fee_Due_Date"));
				consultantPaymentCriteria.setApproved(rs.getBoolean("Approved"));
				consultantPaymentCriteria.setPaid(rs.getBoolean("Paid"));
				consultantPaymentCriteria.setTriggered(rs.getBoolean("Triggered"));

				return consultantPaymentCriteria;
			}
		});

		return consultantPaymentCriterias;
	}


	@Override
	public void addConsultantPaymentCriteria(
			ConsultantPaymentCriteria consultantPaymentCriteria) {

		String addQuery = consultantQueryProps.getProperty("addConsultantPaymentCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", consultantPaymentCriteria.getFileNo())
		.addValue("Consultant_Id", consultantPaymentCriteria.getConsultantId())
		.addValue("Fee_Received", consultantPaymentCriteria.getFeeReceived())
		.addValue("Amount_To_Be_Paid", consultantPaymentCriteria.getAmountToBePaid())
		.addValue("Fee_Due_Date", consultantPaymentCriteria.getFeeDueDate())
		.addValue("Approved", consultantPaymentCriteria.isApproved())
		.addValue("Paid", consultantPaymentCriteria.isPaid())
		.addValue("Triggered", consultantPaymentCriteria.isTriggered());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	@Override
	public void updateConsultantPaymentCriteria(
			ConsultantPaymentCriteria consultantPaymentCriteria) {

		deleteConsultantPaymentCriteria(consultantPaymentCriteria.getFileNo(), consultantPaymentCriteria.getConsultantId());
		addConsultantPaymentCriteria(consultantPaymentCriteria);
	}


	@Override
	public void deleteConsultantPaymentCriteriaExclusion(Long fileNo, List<Long> consultantIds) {

		
		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentCriteriaExclusion");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo)
		.addValue("Consultant_Id",consultantIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	
   public void deleteConsultantPaymentCriteria(Long fileNo, Long consultantId) {

		
		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo)
		.addValue("Consultant_Id",consultantId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	
	@Override
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria){

		String getQuery = consultantQueryProps
				.getProperty("getCosultantBySearchCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Id",searchCriteria.getConsultantId())
		.addValue("Name", StringUtils.isEmpty(searchCriteria.getFirstName())?"%":searchCriteria.getName()+"%")
		.addValue("Primary_Contact_No", StringUtils.isEmpty(searchCriteria.getPrimaryContactNo())?null:searchCriteria.getPrimaryContactNo())
		.addValue("Primary_Contact_No", StringUtils.isEmpty(searchCriteria.getSecondaryNo())?null:searchCriteria.getSecondaryNo());
		
	    List<Consultant> consultants=getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new ConsultantRowMapper());
		
		    return consultants;
	}

	class ConsultantRowMapper implements RowMapper<Consultant>{

		public Consultant mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Consultant consultant = new Consultant();
			consultant.setConsultantId(CommonUtil.getLongValue(rs.getLong("Id")));
			consultant.setName(rs.getString("Name"));
			consultant.setPrimaryContactNo(rs.getString("Primary_Contact_No"));
			consultant.setSecondaryContactNo(rs.getString("Secondary_contact_No"));
			consultant.setAddress(rs.getString("Address"));
			consultant.setEmailId(rs.getString("Email_Id"));

			return consultant;			
		}
	}

	public void saveConsultantAdmissionDetail(ConsultantAdmissionDetail consultantAdmissionDetail){
		
		List<ConsultantDetail> consultantDetails = consultantAdmissionDetail.getConsultantDetails();
		for(ConsultantDetail consultantDetail : consultantDetails){
			addConsultantDtl(consultantDetail);
		}
	}
	
}
