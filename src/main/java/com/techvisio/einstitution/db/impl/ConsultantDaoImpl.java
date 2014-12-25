package com.techvisio.einstitution.db.impl;

import java.util.Properties;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.db.ConsultantDao;

public class ConsultantDaoImpl extends BaseDao implements ConsultantDao {

	private Properties consultantQueryProps;
	
	public void setConsultantQueryProps(Properties consultantQueryProps) {
		this.consultantQueryProps = consultantQueryProps;
	}

	
	public void getConsultant(Consultant consultantMasterData) {
		// TODO Auto-generated method stub
		
	}

	public void addConsultant(Consultant consultantMasterData) {
		
		String addQuery = consultantQueryProps.getProperty("addConsultantMaster");
		
		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultantMasterData.getConsultantId())
		.addValue("Name", consultantMasterData.getName())
		.addValue("Primary_Contact_No", consultantMasterData.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultantMasterData.getSecondaryContactNo())
		.addValue("Address", consultantMasterData.getAddress())
		.addValue("Email_Id", consultantMasterData.getEmailId());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateConsultant(Consultant consultantMasterData) {
		
String updateQuery = consultantQueryProps.getProperty("updateConsultantMaster");
		
		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultantMasterData.getConsultantId())
		.addValue("Name", consultantMasterData.getName())
		.addValue("Primary_Contact_No", consultantMasterData.getPrimaryContactNo())
		.addValue("Secondary_contact_No", consultantMasterData.getSecondaryContactNo())
		.addValue("Address", consultantMasterData.getAddress())
		.addValue("Email_Id", consultantMasterData.getEmailId());
		
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}

	public void deleteConsultant(
			Consultant consultantMasterData) {
	
String deleteQuery = consultantQueryProps.getProperty("deleteConsultantMaster");
		
		SqlParameterSource namedParameter= new MapSqlParameterSource("Id", consultantMasterData.getConsultantId());
		
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		
	}

	public void getConsultantDtl(ConsultantDetail consultantDetail) {
		// TODO Auto-generated method stub
		
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

		
	}

	public void updateConsultantDtl(ConsultantDetail consultantDetail) {
		
String updateQuery = consultantQueryProps.getProperty("updateConsultantDtl");
		
		SqlParameterSource namedParameter =  new MapSqlParameterSource("Consultant_Id", consultantDetail.getConsultantId())
		.addValue("File_No", consultantDetail.getFileNo())
		.addValue("Consultancy_Agreed", consultantDetail.isConsultancyAgreed())
		.addValue("Payment_Mode", consultantDetail.getPaymentMode())
		.addValue("Amount_To_Pay", consultantDetail.getAmountToPay())
		.addValue("Due_Date", consultantDetail.getDueDate());
		
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		
	}

	public void deleteConsultantDtl(ConsultantDetail consultantDetail) {
		
String deleteQuery = consultantQueryProps.getProperty("deleteConsultantDtl");
		
		SqlParameterSource namedParameter =  new MapSqlParameterSource("Consultant_Id", consultantDetail.getConsultantId());
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		
	}

	public void getConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {
		// TODO Auto-generated method stub
		
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

	public void deleteConsultantPaymentDtl(ConsultantPaymentDtl consultantPaymentDtl) {
		
String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtl");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("FileNo", consultantPaymentDtl.getFileNo());
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}
	

}
