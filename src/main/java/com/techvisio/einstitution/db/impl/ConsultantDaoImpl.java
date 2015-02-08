package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
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

	public ConsultantDetail getConsultantDtl(String fileNo) {

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

				return consultantDetail;			}
		});

		ConsultantDetail consultantDetail =  null;

		if(consultantDetails != null && consultantDetails.size()>0){

			consultantDetail = consultantDetails.get(0);

			List<ConsultantPaymentDtl> consultantPaymentDtls = getConsultantPaymentDtl(fileNo);

			consultantDetail.setConsultantPaymentDetail(consultantPaymentDtls);
		}

		return consultantDetail;
	}


	public void addConsultantDtl(ConsultantDetail consultantDetail) {

		String addQuery = consultantQueryProps.getProperty("addConsultantDtl");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("Consultant_Id", consultantDetail.getConsultantId())
		.addValue("File_No", consultantDetail.getFileNo())
		.addValue("Consultancy_Agreed", consultantDetail.isConsultancyAgreed())
		.addValue("Payment_Mode", consultantDetail.getPaymentMode())
		.addValue("Amount_To_Pay", consultantDetail.getAmountToPay())
		.addValue("Due_Date", consultantDetail.getDueDate());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		if (consultantDetail.getConsultantPaymentDetail() != null) {
			for (ConsultantPaymentDtl consultantPaymentDtl:consultantDetail
					.getConsultantPaymentDetail()) {
				addConsultantPaymentDtl(consultantPaymentDtl);
			}
		}

	}

	public void updateConsultantDtl(ConsultantDetail consultantDetail) {

		String updateQuery = consultantQueryProps.getProperty("updateConsultantDtl");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", consultantDetail.getFileNo())
		.addValue("Consultant_Id", consultantDetail.getConsultantId())
		.addValue("Consultancy_Agreed", consultantDetail.isConsultancyAgreed())
		.addValue("Payment_Mode", consultantDetail.getPaymentMode())
		.addValue("Amount_To_Pay", consultantDetail.getAmountToPay())
		.addValue("Due_Date", consultantDetail.getDueDate());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

		if (consultantDetail.getConsultantPaymentDetail() != null) {
			for (ConsultantPaymentDtl consultantPaymentDtl:consultantDetail
					.getConsultantPaymentDetail()) {
				updateConsultantPaymentDtl(consultantPaymentDtl);
			}
		}

		
		
	}

	public void deleteConsultantDtl(String fileNo) {


		deleteConsultantPaymentDtl(fileNo);
		
		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantDtl");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public List<ConsultantPaymentDtl> getConsultantPaymentDtl(String fileNo) {

		String getQuery= consultantQueryProps.getProperty("getConsultantPaymentDtlByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);


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

	public void deleteConsultantPaymentDtl(String fileNo) {

		String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}


}
