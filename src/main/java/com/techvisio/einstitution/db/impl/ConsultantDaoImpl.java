package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.util.CommonUtil;

public class ConsultantDaoImpl extends BaseDao implements ConsultantDao {

	private Properties consultantQueryProps;

	public void setConsultantQueryProps(Properties consultantQueryProps) {
		this.consultantQueryProps = consultantQueryProps;
	}


	public Consultant getConsultant(Long consultantId) {

		String getQuery = consultantQueryProps.getProperty("getConsultantById");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Id", consultantId);

		List<Consultant> consultants = getNamedParamJdbcTemplate().query(getQuery,namedParameter, new RowMapper<Consultant>() {

			public Consultant mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Consultant consultant = new Consultant();
				consultant.setConsultantId(CommonUtil.getLongValue(rs.getLong("Id")));
				consultant.setName(rs.getString("Name"));
				consultant.setPrimaryContactNo(rs.getString("Primary_Contact_No"));
				consultant.setSecondaryContactNo(rs.getString("Secondary_contact_No"));
				consultant.setAddress(rs.getString("Address"));
				consultant.setEmailId(rs.getString("Email_Id"));

				return consultant;			}
		});

		Consultant consultant =  null;

		if(consultants != null && consultants.size()>0){

			consultant = consultants.get(0);		}

		return consultant;

	}



	public void addConsultant(Consultant consultant) {

		String addQuery = consultantQueryProps.getProperty("addConsultant");

		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultant.getConsultantId())
		.addValue("Name", consultant.getName())
		.addValue("Primary_Contact_No", consultant.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultant.getSecondaryContactNo())
		.addValue("Address", consultant.getAddress())
		.addValue("Email_Id", consultant.getEmailId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateConsultant(Consultant consultant) {

		String updateQuery = consultantQueryProps.getProperty("updateConsultant");

		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultant.getConsultantId())
		.addValue("Name", consultant.getName())
		.addValue("Primary_Contact_No", consultant.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultant.getSecondaryContactNo())
		.addValue("Address", consultant.getAddress())
		.addValue("Email_Id", consultant.getEmailId());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}

	public void deleteConsultant(
			String fileNo) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultant");

		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", fileNo);


		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public List<ConsultantDetail> getConsultantDtl(final String fileNo) {

		String getQuery = consultantQueryProps.getProperty("getConsultantDtlByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<ConsultantDetail> consultantDetails  = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantDetail>() {

			public ConsultantDetail mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ConsultantDetail consultantDetail = new ConsultantDetail();

				consultantDetail.setConsultantId(CommonUtil.getLongValue(rs.getLong("Consultant_Id")));
				consultantDetail.setFileNo(rs.getString("File_No"));
				consultantDetail.setAmountToPay(rs.getDouble("Amount_To_Pay"));
				consultantDetail.setConsultancyAgreed(rs.getBoolean("Consultancy_Agreed"));
				consultantDetail.setPaymentMode(rs.getString("Payment_Mode"));
				consultantDetail.setDueDate(rs.getDate("Due_Date"));
				consultantDetail.setRemarks(rs.getString("Remarks"));
				
                Long consultantId = consultantDetail.getConsultantId();
				List<ConsultantPaymentDtl> consultantPaymentDtls = getConsultantPaymentDtl(fileNo,consultantId);
				consultantDetail.setConsultantPaymentDetail(consultantPaymentDtls);
            
				List<ConsultantPaymentCriteria> consultantPaymentCriterias = getConsultantPaymentCriteria(fileNo, consultantId);
				consultantDetail.setConsultantPaymentCriterias(consultantPaymentCriterias);
				return consultantDetail;			}
		});

		return consultantDetails;
	}


	public void addConsultantDtl(ConsultantDetail consultantDetail) {

		String upsertQuery = consultantQueryProps.getProperty("upsertConsultantDtl");
		if(consultantDetail.getConsultantId()!=null){
			SqlParameterSource namedParameter =  new MapSqlParameterSource("Consultant_Id", consultantDetail.getConsultantId())
			.addValue("File_No", consultantDetail.getFileNo())
			.addValue("Consultancy_Agreed", consultantDetail.isConsultancyAgreed())
			.addValue("Payment_Mode", consultantDetail.getPaymentMode())
			.addValue("Amount_To_Pay", consultantDetail.getAmountToPay())
			.addValue("Due_Date", consultantDetail.getDueDate())
			.addValue("Remarks", consultantDetail.getRemarks());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

			if (consultantDetail.getConsultantPaymentDetail() != null) {
				for (ConsultantPaymentDtl consultantPaymentDtl:consultantDetail
						.getConsultantPaymentDetail()) {
					addConsultantPaymentDtl(consultantPaymentDtl);
				}
			}
			
			if(consultantDetail.getConsultantPaymentCriterias() != null){
				
				for(ConsultantPaymentCriteria consultantPaymentCriteria : consultantDetail.getConsultantPaymentCriterias()){
					
					addConsultantPaymentCriteria(consultantPaymentCriteria);
				}
			}
		}
	}


	//TODO: WE NEED TO CONSULTANT PAYMENT WORK   		


	public void deleteConsultantDtl(List<ConsultantDetail> consultantDetails) {

		List<Long> consultantIds=new ArrayList<Long>();
		String fileNo=null;
		if(consultantDetails != null){
			for(ConsultantDetail consultantDetail:consultantDetails){
				consultantIds.add(consultantDetail.getConsultantId());
				fileNo=consultantDetail.getFileNo();
		
				deleteConsultantPaymentDtl(fileNo, consultantDetail.getConsultantId());

				deleteConsultantPaymentCriteria(fileNo, consultantDetail.getConsultantId());

			}
		
			  if(consultantIds.size()==0){
					
				  consultantIds.add(-1L);
			     }

		}
				
		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantDtl");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantIds);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public List<ConsultantPaymentDtl> getConsultantPaymentDtl(String fileNo, Long consultantId) {

		String getQuery= consultantQueryProps.getProperty("getConsultantPaymentDtlByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);


		List<ConsultantPaymentDtl> consultantPaymentDtls = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantPaymentDtl>() {

			public ConsultantPaymentDtl mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ConsultantPaymentDtl consultantPaymentDtl=  new ConsultantPaymentDtl();

				consultantPaymentDtl.setAmount(rs.getDouble("Amount"));
				consultantPaymentDtl.setFileNo(rs.getString("File_No"));
				consultantPaymentDtl.setPayDate(rs.getDate("Pay_Date"));

				return consultantPaymentDtl;
			}
		});

		return consultantPaymentDtls;
	}

	public void addConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {

		String addQuery = consultantQueryProps.getProperty("addConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("FileNo", consultantPaymentDtl.getFileNo())
		.addValue("Amount", consultantPaymentDtl.getAmount())
		.addValue("Pay_Date", consultantPaymentDtl.getPayDate());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	public void updateConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {

		String updateQuery = consultantQueryProps.getProperty("updateConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("FileNo", consultantPaymentDtl.getFileNo())
		.addValue("Amount", consultantPaymentDtl.getAmount())
		.addValue("Pay_Date", consultantPaymentDtl.getPayDate());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);


	}

	public void deleteConsultantPaymentDtl(String fileNo, Long consultantId) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}


	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(String fileNo, Long consultantId) {

		String getQuery = consultantQueryProps.getProperty("getConsultantPaymentCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo)
		.addValue("Consultant_Id", consultantId);

		List<ConsultantPaymentCriteria> consultantPaymentCriterias= getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<ConsultantPaymentCriteria>() {

			@Override
			public ConsultantPaymentCriteria mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				ConsultantPaymentCriteria consultantPaymentCriteria = new ConsultantPaymentCriteria();

				consultantPaymentCriteria.setFileNo(rs.getString("File_No"));
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
	public void deleteConsultantPaymentCriteria(String fileNo, Long consultantId) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo)
		.addValue("Consultant_Id",consultantId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

}
