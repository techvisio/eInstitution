package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantPayment;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.db.ConsultantDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class ConsultantDaoImpl extends BaseDao implements ConsultantDao {
	private static CustomLogger logger = CustomLogger
			.getLogger(ConsultantDaoImpl.class);

	@Autowired
	@Qualifier(value = "consultantQueryProps")
	private Properties consultantQueryProps;

	public void setConsultantQueryProps(Properties consultantQueryProps) {
		this.consultantQueryProps = consultantQueryProps;
	}

	@Autowired
	CacheManager cacheManager;

	@Override
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(Long fileNo) {

		String queryString = "FROM AdmissnConsltntDtl ac WHERE ac.fileNo = "
				+ fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		List<AdmissnConsltntDtl> result = query.list();
		return result;
	}

	@Override
	public void saveAdmissionConsultantDtl(
			List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {

		if (admissnConsltntDtls != null && admissnConsltntDtls.size() > 0) {
			deleteAdmissionConsultantDtlExclusion(admissnConsltntDtls, fileNo);
			for (AdmissnConsltntDtl admissnConsltntDtl : admissnConsltntDtls) {
				admissnConsltntDtl.setFileNo(fileNo);
				saveAdmissionConsultantDtl(admissnConsltntDtl);
			}
		}
	}

	@Override
	public Long saveAdmissionConsultantDtl(AdmissnConsltntDtl admissnConsltntDtl) {

		if (admissnConsltntDtl.getConsltantDtlId() == null) {
			getCurrentSession().persist(admissnConsltntDtl);
		} else {
			getCurrentSession().update(admissnConsltntDtl);
			saveConsultantPayment(
					admissnConsltntDtl.getConsultantPaymentDetail(),
					admissnConsltntDtl.getFileNo());
			saveConsultantPaymentCriteria(
					admissnConsltntDtl.getConsultantPaymentCriterias(),
					admissnConsltntDtl.getFileNo());
		}
		
		getCurrentSession().flush();
		return admissnConsltntDtl.getFileNo();
	}

	@Override
	public void deleteAdmissionConsultantDtlExclusion(
			List<AdmissnConsltntDtl> admissnConsltntDtls, Long fileNo) {

		List<Long> consultantDtlIds = new ArrayList<Long>();
		if (admissnConsltntDtls != null) {
			for (AdmissnConsltntDtl admissnConsltntDtl : admissnConsltntDtls) {
				if (admissnConsltntDtl.getConsltantDtlId() != null) {
					consultantDtlIds
					.add(admissnConsltntDtl.getConsltantDtlId());
				}
			}

			if (consultantDtlIds.size() == 0) {
				consultantDtlIds.add(-1L);
			}
		}

		String deletePaymntQuery = consultantQueryProps
				.getProperty("dltCnsltntPymntDtlByConsltantDtlId");

		Query query = getCurrentSession().createSQLQuery(deletePaymntQuery)
				.setParameter("File_No", fileNo)
				.setParameterList("Consultant_Dtl_Id", consultantDtlIds);
		query.executeUpdate();

		String deleteCriteriaQuery = consultantQueryProps
				.getProperty("dltCnsltantPymntCriteriaByConsltantDtlId");

		Query query1 = getCurrentSession().createSQLQuery(deleteCriteriaQuery)
				.setParameter("File_No", fileNo)
				.setParameterList("Consultant_Dtl_Id", consultantDtlIds);
		query1.executeUpdate();

		String deleteConsltntDtl = consultantQueryProps
				.getProperty("deleteConsultantDtlExclusion");

		Query query2 = getCurrentSession().createSQLQuery(deleteConsltntDtl)
				.setParameter("File_No", fileNo)
				.setParameterList("Consultant_Dtl_Id", consultantDtlIds);
		query2.executeUpdate();
	}

	@Override
	public List<ConsultantPayment> getConsultantPayment(Long fileNo) {

		String queryString = "FROM ConsultantPayment acp WHERE acp.fileNo = "
				+ fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		List<ConsultantPayment> result = query.list();
		return result;
	}

	@Override
	public void saveConsultantPayment(
			List<ConsultantPayment> consultantPayments, Long fileNo) {
		if (consultantPayments != null && consultantPayments.size() > 0) {
			deleteConsultantPaymentExclusion(consultantPayments, fileNo);
			for (ConsultantPayment consultantPayment : consultantPayments) {
				saveConsultantPayment(consultantPayment);
			}
		}
	}

	@Override
	public void saveConsultantPayment(ConsultantPayment consultantPayment) {

		if (consultantPayment.getConsltntPymntId() == null) {
			getCurrentSession().persist(consultantPayment);
		} else {
			getCurrentSession().update(consultantPayment);
		}
	}

	@Override
	public void deleteConsultantPaymentExclusion(
			List<ConsultantPayment> consultantPayments, Long fileNo) {
		List<Long> consultantPymntIds = new ArrayList<Long>();
		if (consultantPayments != null) {
			for (ConsultantPayment consultantPayment : consultantPayments) {
				if (consultantPayment.getConsltntPymntId() != null) {
					consultantPymntIds.add(consultantPayment
							.getConsltntPymntId());
				}
			}

			if (consultantPymntIds.size() == 0) {
				consultantPymntIds.add(-1L);
			}
		}
		String deleteQuery = consultantQueryProps
				.getProperty("deleteConsultantPaymentDtlExclusion");

		Query query = getCurrentSession().createSQLQuery(deleteQuery)
				.setParameter("File_No", fileNo)
				.setParameterList("Consltnt_Pymnt_Id", consultantPymntIds);
		query.executeUpdate();
	}

	@Override
	public List<ConsultantPaymentCriteria> getConsultantPaymentCriteria(
			Long fileNo) {
		String queryString = "FROM ConsultantPaymentCriteria acpc WHERE acpc.fileNo = "
				+ fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		List<ConsultantPaymentCriteria> result = query.list();
		return result;
	}

	@Override
	public void saveConsultantPaymentCriteria(
			List<ConsultantPaymentCriteria> consultantPaymentCriterias,
			Long fileNo) {

		if (consultantPaymentCriterias != null
				&& consultantPaymentCriterias.size() > 0) {
			deleteConsultantPaymentCriteriaExclusion(
					consultantPaymentCriterias, fileNo);
			for (ConsultantPaymentCriteria consultantPaymentCriteria : consultantPaymentCriterias) {
				saveConsultantPaymentCriteria(consultantPaymentCriteria);
			}
		}
	}

	@Override
	public void saveConsultantPaymentCriteria(
			ConsultantPaymentCriteria consultantPaymentCriteria) {

		if (consultantPaymentCriteria.getPymntCritriaId() == null) {
			getCurrentSession().persist(consultantPaymentCriteria);
		} else {
			getCurrentSession().update(consultantPaymentCriteria);
		}
	}

	@Override
	public void deleteConsultantPaymentCriteriaExclusion(
			List<ConsultantPaymentCriteria> consultantPaymentCriterias,
			Long fileNo) {
		List<Long> Ids = new ArrayList<Long>();
		if (consultantPaymentCriterias != null) {
			for (ConsultantPaymentCriteria consultantPaymentCriteria : consultantPaymentCriterias) {
				if (consultantPaymentCriteria.getPymntCritriaId() != null) {
					Ids.add(consultantPaymentCriteria.getPymntCritriaId());
				}
			}

			if (Ids.size() == 0) {
				Ids.add(-1L);
			}
		}
		String deleteQuery = consultantQueryProps
				.getProperty("deleteConsultantPaymentCriteriaExclusion");

		Query query = getCurrentSession().createSQLQuery(deleteQuery)
				.setParameter("File_No", fileNo)
				.setParameterList("Pymnt_Critria_Id", Ids);
		query.executeUpdate();
	}

	@Override
	public void saveConsultantAdmissionDetail(
			ConsultantAdmissionDetail consultantAdmissionDetail) {
		logger.info("{} : Adding Student{}'s consultant detail", this
				.getClass().getName(), consultantAdmissionDetail.getBasicInfo()
				.getFirstName()
				+ consultantAdmissionDetail.getBasicInfo().getLastName());
		List<AdmissnConsltntDtl> consultantDetails = consultantAdmissionDetail
				.getConsultantDetails();
		for (AdmissnConsltntDtl consultantDetail : consultantDetails) {
			saveAdmissionConsultantDtl(consultantDetail);
		}
	}

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		logger.info(
				"{} : Getting Student detail bby searching criteria for enquiryId:{}",
				this.getClass().getName(), searchCriteria.getInquryId());
		String getQuery = consultantQueryProps
				.getProperty("getStudentDtlForConsultant");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Registration_No", StringUtils.isEmpty(searchCriteria
						.getRegistrationNo()) ? null
								: searchCriteria.getRegistrationNo())
		.addValue(
				"Email_Id",
				StringUtils.isEmpty(searchCriteria.getEmailId()) ? null
						: searchCriteria.getEmailId())
						.addValue(
								"Enroll_No",
								StringUtils.isEmpty(searchCriteria.getEnrollNo()) ? null
										: searchCriteria.getEnrollNo())
										.addValue(
												"Uni_Enroll_No",
												StringUtils.isEmpty(searchCriteria.getUniEnrollNo()) ? null
														: searchCriteria.getUniEnrollNo())
														.addValue(
																"First_Name",
																StringUtils.isEmpty(searchCriteria.getFirstName()) ? "%"
																		: searchCriteria.getFirstName() + "%")
																		.addValue(
																				"Self_Mobile_No",
																				StringUtils.isEmpty(searchCriteria.getMobileNo()) ? null
																						: searchCriteria.getMobileNo())
																						.addValue("Course_Id", searchCriteria.getCourseId())
																						.addValue("File_No", searchCriteria.getFileNo())
																						.addValue("Branch_Id", searchCriteria.getBranchId());

		List<StudentBasicInfo> studentBasicInfos = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter, new StudentBasicInfoRowMaper());

		return studentBasicInfos;
	}

	class StudentBasicInfoRowMaper implements RowMapper<StudentBasicInfo> {

		public StudentBasicInfo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			logger.info(
					"{} : Putting value in setter of studentBasicInfo bean  : {}",
					this.getClass().getName());
			StudentBasicInfo basicInfo = new StudentBasicInfo();
			basicInfo.setFirstName(rs.getString("First_Name"));
			basicInfo.setLastName(rs.getString("Last_Name"));
			basicInfo.setAcademicYear(rs.getString("Academic_Year"));
			Long branchId = (CommonUtil.getLongValue(rs.getLong("Branch_Id")));
			Branch branch = cacheManager.getBranchById(branchId);
			basicInfo.setBranch(branch);
			Long courseId = (CommonUtil.getLongValue(rs.getLong("Course_Id")));
			Course course = cacheManager.getCourseById(courseId);
			basicInfo.setCourse(course);
			Long categoryId = (CommonUtil.getLongValue(rs
					.getLong("Category_Id")));
			CasteCategory category = cacheManager.getCategoryId(categoryId);
			basicInfo.setCasteCategory(category);
			basicInfo.setDob(rs.getDate("DOB"));
			basicInfo.setEnrollmentNo(rs.getString("Enroll_No"));
			basicInfo.setFatherName(rs.getString("Father_Name"));
			basicInfo.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
			basicInfo.setGender(rs.getString("Gender"));
			basicInfo.setModifiedDate(rs.getDate("Updated_On"));
			basicInfo.setSemester(rs.getString("Semester"));
			Long sessionId = (CommonUtil.getLongValue(rs.getLong("Session_Id")));
			Session session = cacheManager.getSessionBySessionId(sessionId);
			basicInfo.setSession(session);
			Long batchId = (CommonUtil.getLongValue(rs.getLong("Batch_Id")));
			Batch batch = cacheManager.getBatchByBatchId(batchId);
			basicInfo.setBatch(batch);
			basicInfo.setRegistrationNo(rs.getString("Registration_No"));

			Long centreId = (CommonUtil.getLongValue(rs.getLong("Centre_id")));
			Centre centre = cacheManager.getCentreByCentreId(centreId);
			basicInfo.setCentre(centre);

			Long shiftId = (CommonUtil.getLongValue(rs.getLong("Shift_Id")));
			Shift shift = cacheManager.getShiftByShiftId(shiftId);
			basicInfo.setShift(shift);

			Long sectionId = (CommonUtil.getLongValue(rs.getLong("Section_Id")));
			Section section = cacheManager.getSectionBySectionId(sectionId);
			basicInfo.setSection(section);

			return basicInfo;
		}
	}
	
	@Override
	public Long saveConsultant(Consultant consultant) {
		 if(consultant.getConsultantId()==null){
				getCurrentSession().persist(consultant);
			}
			else{
				getCurrentSession().update(consultant);
			}
		//refresh cache
		//CacheManager cacheManager=new CacheManagerImpl();
		cacheManager.refreshCacheList(AppConstants.CONSULTANT);
		
		return consultant.getConsultantId();
	}
	
	@Override
	public Consultant getConsultant(Long consultantId) {
		String queryString="FROM Consultant c WHERE c.consultantId = "+consultantId;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Consultant> result= (List<Consultant>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public List<Consultant> getConsultantBySearchCriteria(SearchCriteria searchCriteria){
		 logger.info("{} : getting consultant by searching criteria : Consultant name:{}",this.getClass().getName(), searchCriteria.getName());
		String getQuery = consultantQueryProps
				.getProperty("getCosultantBySearchCriteria");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Consultant_Id", searchCriteria.getConsultantId())
		.addValue("Name", StringUtils.isEmpty(searchCriteria.getName())?"%":searchCriteria.getName()+"%")
		.addValue("Primary_Contact_No", StringUtils.isEmpty(searchCriteria.getPrimaryContactNo())?null:searchCriteria.getPrimaryContactNo())
		.addValue("Secondary_Contact_No", StringUtils.isEmpty(searchCriteria.getSecondaryNo())?null:searchCriteria.getSecondaryNo())
		.addValue("Email_Id", StringUtils.isEmpty(searchCriteria.getEmailId())?null:searchCriteria.getEmailId());
		
	    List<Consultant> consultants=getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new ConsultantRowMapper());
		
		    return consultants;
	}

	class ConsultantRowMapper implements RowMapper<Consultant>{

		public Consultant mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			 logger.info("{}Putting values in particular field by getting from user through rowmapper : ",this.getClass().getName());
			Consultant consultant = new Consultant();
			consultant.setConsultantId(CommonUtil.getLongValue(rs.getLong("Consultant_Id")));
			consultant.setName(rs.getString("Name"));
			consultant.setPrimaryContactNo(rs.getString("Primary_Contact_No"));
			consultant.setSecondaryContactNo(rs.getString("Secondary_contact_No"));
			consultant.setAddress(rs.getString("Address"));
			consultant.setEmailId(rs.getString("Email_Id"));

			return consultant;			
		}
	}

	@Override
	public AdmissnConsltntDtl getAdmissionConsltntDtl(Long fileNo) {
		
		String queryString="FROM AdmissnConsltntDtl ac WHERE ac.fileNo = "
				+ fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<AdmissnConsltntDtl> result= (List<AdmissnConsltntDtl>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

}
