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

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class ConsultantDaoImpl extends BaseDao implements ConsultantDao {
	private static CustomLogger logger = CustomLogger.getLogger(ConsultantDaoImpl.class);

	@Autowired @Qualifier(value="consultantQueryProps")
	private Properties consultantQueryProps;

	public void setConsultantQueryProps(Properties consultantQueryProps) {
		this.consultantQueryProps = consultantQueryProps;
	}

	@Autowired
	CacheManager cacheManager;

	@Override
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo) {

		String queryString="FROM AdmissnConsltntDtl ac WHERE ac.fileNo = "+fileNo;
			Query query=getCurrentSession().createQuery(queryString);
			List<AdmissnConsltntDtl> result= query.list();
				return result;
	}

	@Override
	public void saveAdmissionConsultantDtl(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {

		if(admissnConsltntDtls!=null && admissnConsltntDtls.size()>0){
			deleteAdmissionConsultantDtlExclusion(admissnConsltntDtls, fileNo);
			for(AdmissnConsltntDtl admissnConsltntDtl:admissnConsltntDtls){
				saveAdmissionConsultantDtl(admissnConsltntDtl);
			}
		}
	}

	@Override
	public void saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl) {

		if(admissnConsltntDtl.getConsltantDtlId()==null){
			getCurrentSession().persist(admissnConsltntDtl);
		}
		else{
			getCurrentSession().update(admissnConsltntDtl);
			saveConsultantPayment(admissnConsltntDtl.getConsultantPaymentDetail(), admissnConsltntDtl.getFileNo());
			saveConsultantPaymentCriteria(admissnConsltntDtl.getConsultantPaymentCriterias(), admissnConsltntDtl.getFileNo());
		}
	}

	@Override
	public void deleteAdmissionConsultantDtlExclusion(List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {

		List<Long> consultantDtlIds = new ArrayList<Long>();
		if (admissnConsltntDtls != null) {
			for (AdmissnConsltntDtl admissnConsltntDtl : admissnConsltntDtls) {
				if(admissnConsltntDtl.getConsltantDtlId() != null){
					consultantDtlIds.add(admissnConsltntDtl.getConsltantDtlId());
				}
			}
			
			if (consultantDtlIds.size() == 0) {
				consultantDtlIds.add(-1L);
			}
		}

			String deletePaymntQuery = consultantQueryProps.getProperty("dltCnsltntPymntDtlByConsltantDtlId");

			SqlParameterSource namedParametr = namedParameterForDltConsultnt(
					fileNo, consultantDtlIds);

			getNamedParamJdbcTemplate().update(deletePaymntQuery, namedParametr);


			String deleteCriteriaQuery = consultantQueryProps.getProperty("dltCnsltantPymntCriteriaByConsltantDtlId");

			SqlParameterSource namedParam = namedParameterForDltConsultnt(
					fileNo, consultantDtlIds);

			getNamedParamJdbcTemplate().update(deleteCriteriaQuery, namedParam);


			String deleteQuery = consultantQueryProps.getProperty("deleteConsultantDtlExclusion");

			SqlParameterSource namedParameter = namedParameterForDltConsultnt(
					fileNo, consultantDtlIds);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	

	private SqlParameterSource namedParameterForDltConsultnt(Long fileNo,
			List<Long> consultantDtlIds) {
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Consultant_Dtl_Id", consultantDtlIds)
		.addValue("File_No", fileNo);
		return namedParameter;
	}

	@Override
public List<ConsultantPayment> getConsultantPayment(Long fileNo) {

	String queryString="FROM ConsultantPayment acp WHERE acp.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<ConsultantPayment> result= query.list();
			return result;
}

	@Override
	public void saveConsultantPayment(List<ConsultantPayment> consultantPayments, Long fileNo) {
		if(consultantPayments!=null && consultantPayments.size()>0){
			deleteConsultantPaymentExclusion(consultantPayments, fileNo);
			for(ConsultantPayment consultantPayment : consultantPayments){
				saveConsultantPayment(consultantPayment);
			}
		}
	}

	@Override
	public void saveConsultantPayment(ConsultantPayment consultantPayment) {

		if(consultantPayment.getConsltntPymntId()==null){
			getCurrentSession().persist(consultantPayment);
		}
		else
		{
			getCurrentSession().update(consultantPayment);
		}
	}

	@Override
	public void deleteConsultantPaymentExclusion(List<ConsultantPayment> consultantPayments, Long fileNo) {
		List<Long> consultantPymntIds = new ArrayList<Long>();
		if (consultantPayments != null) {
			for (ConsultantPayment consultantPayment : consultantPayments) {
				if(consultantPayment.getConsltntPymntId() != null){
					consultantPymntIds.add(consultantPayment.getConsltntPymntId());
				}
			}
			
			if (consultantPymntIds.size() == 0) {
				consultantPymntIds.add(-1L);
			}
		}

			String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Consltnt_Pymnt_Id", consultantPymntIds)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	

	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(Long fileNo) {
		String queryString="FROM ConsultantPaymentCriteria acpc WHERE acpc.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<ConsultantPaymentCriteria> result=query.list();
			return result;
	}
	
	@Override
	public void saveConsultantPaymentCriteria(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo) {

		if(consultantPaymentCriterias != null && consultantPaymentCriterias.size()>0){
			deleteConsultantPaymentCriteriaExclusion(consultantPaymentCriterias, fileNo);
			for(ConsultantPaymentCriteria consultantPaymentCriteria : consultantPaymentCriterias){
				saveConsultantPaymentCriteria(consultantPaymentCriteria);
			}
		}
	}

	@Override
	public void saveConsultantPaymentCriteria(ConsultantPaymentCriteria consultantPaymentCriteria) {

		if(consultantPaymentCriteria.getPymntCritriaId()==null){
			getCurrentSession().persist(consultantPaymentCriteria);
		}
		else
		{
			getCurrentSession().update(consultantPaymentCriteria);
		}
	}

	@Override
	public void deleteConsultantPaymentCriteriaExclusion(List<ConsultantPaymentCriteria> consultantPaymentCriterias, Long fileNo) {


		List<Long> Ids = new ArrayList<Long>();
		if (consultantPaymentCriterias != null) {
			for (ConsultantPaymentCriteria consultantPaymentCriteria : consultantPaymentCriterias) {
				if(consultantPaymentCriteria.getPymntCritriaId() != null){
					Ids.add(consultantPaymentCriteria.getPymntCritriaId());
				}
			}
			
			if (Ids.size() == 0) {
				Ids.add(-1L);
			}
		}

			String deleteQuery = consultantQueryProps.getProperty("deleteConsultantPaymentCriteriaExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Id", Ids)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}

	

}
