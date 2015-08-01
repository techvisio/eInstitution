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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.beans.Workflow;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;

@Transactional(propagation=Propagation.SUPPORTS)
@Component
public class AdmissionDaoImpl extends BaseDao implements AdmissionDao {
	private static CustomLogger logger = CustomLogger
			.getLogger(AdmissionDaoImpl.class);

	@Autowired
	CacheManager cacheManager;

	@Autowired
	@Qualifier(value = "admissionQueryProps")
	private Properties admissionQueryProps;

	public void setAdmissionQueryProps(Properties admissionQueryProps) {
		this.admissionQueryProps = admissionQueryProps;
	}

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(SearchCriteria searchCriteria){
		 logger.info("{} : Getting Student detail bby searching criteria for enquiryId:{}",this.getClass().getName(), searchCriteria.getInquryId());		
		String getQuery = admissionQueryProps
				.getProperty("getStudentDtlDynamically");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Registration_No",StringUtils.isEmpty(searchCriteria.getRegistrationNo())?null:searchCriteria.getRegistrationNo())
		.addValue("Email_Id", StringUtils.isEmpty(searchCriteria.getEmailId())?null:searchCriteria.getEmailId())
		.addValue("Enroll_No", StringUtils.isEmpty(searchCriteria.getEnrollNo())?null:searchCriteria.getEnrollNo())
		.addValue("Uni_Enroll_No", StringUtils.isEmpty(searchCriteria.getUniEnrollNo())?null:searchCriteria.getUniEnrollNo())
		.addValue("First_Name", StringUtils.isEmpty(searchCriteria.getFirstName())?"%":searchCriteria.getFirstName()+"%")
		.addValue("Self_Mobile_No", StringUtils.isEmpty(searchCriteria.getMobileNo())?null:searchCriteria.getMobileNo())
		.addValue("Course_Id", searchCriteria.getCourseId())
		.addValue("Branch_Id", searchCriteria.getBranchId());
		
	List<StudentBasicInfo> studentBasicInfos=getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new StudentBasicInfoRowMaper());
		
		    return studentBasicInfos;
	}
	@Override
	public Student getStudent(Long fileNo) {
		String queryString="FROM Student s WHERE s.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Student> result= (List<Student>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public Long saveStudent(Student student) {

		if(student.getFileNo()==null){
			Workflow wf=cacheManager.getNewAdmissionWorkFlow();
			if(wf != null){
				if(student.getStudentBasics() != null){
				student.getStudentBasics().setApplicationStatus(wf.getStepId());
				}
			}
			if(student.getStudentBasics() != null){
			student.getStudentBasics().setStudent(student);
			}
			if(student.getScholarship() != null){
				student.getScholarship().setStudent(student);
				}
			getCurrentSession().save(student);
		}
		else{
			getCurrentSession().update(student);
		}
		
		getCurrentSession().flush();
		return student.getFileNo();
	}

	@Override
	public StudentBasics getStudentBasics(Long fileNo) {
		String queryString="FROM StudentBasics sb WHERE sb.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<StudentBasics> result= (List<StudentBasics>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void saveStudentBasics(StudentBasics studentBasics) {

		if(studentBasics.getFileNo()==null){
			getCurrentSession().persist(studentBasics);
		}
		else{
			getCurrentSession().update(studentBasics);
		}
	}
	
	@Override
	public List<Address> getAddressDtl(Long fileNo) {

		String queryString="FROM Address stdntAdd WHERE stdntAdd.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<Address> result= query.list();
		return result;
	}

	@Override
	public void saveAddressDtl(List<Address> addresses, Long fileNo) {

		if(addresses != null && addresses.size()>0){
			for(Address address:addresses){
				saveAddressDtl(address);
			}
		}
	}

	@Override
	public void saveAddressDtl(Address address) {

		if(address.getAddressId()==null){
			getCurrentSession().persist(address);
		}
		else{
			getCurrentSession().update(address);
		}
	}

	@Override
	public List<AdmissionDiscount> getDiscountDtl(Long fileNo) {

		String queryString="FROM AdmissionDiscount ad WHERE ad.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<AdmissionDiscount> result= query.list();
		return result;
	}

	@Override
	public void saveDiscountDtl(List<AdmissionDiscount> admissionDiscounts, Long fileNo) {

		if(admissionDiscounts!=null && admissionDiscounts.size()>0){
			deleteDiscountDtlExclusion(admissionDiscounts,fileNo);
			for(AdmissionDiscount admissionDiscount : admissionDiscounts){
				admissionDiscount.setFileNo(fileNo);
				saveDiscountDtl(admissionDiscount);
			}
		}
	}

	@Override
	public void saveDiscountDtl(AdmissionDiscount admissionDiscount) {

		if(admissionDiscount.getDiscountDtlId() == null){
			getCurrentSession().persist(admissionDiscount);
		}
		else{
			getCurrentSession().update(admissionDiscount);
		}
	}

	@Override
	public void deleteDiscountDtlExclusion(
			List<AdmissionDiscount> admissionDiscounts, Long fileNo) {

		List<Long> discountId = new ArrayList<Long>();
		if (admissionDiscounts != null) {
			for (AdmissionDiscount admissionDiscount : admissionDiscounts) {
				if(admissionDiscount.getDiscountDtlId() != null){
					discountId.add(admissionDiscount.getDiscountDtlId());
				}
			}

			if (discountId.size() == 0) {
				discountId.add(-1L);
			}
		}

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAdmissionDisDtlExclusion");

		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameterList("Discount_Dtl_Id", discountId);
		query.executeUpdate();	}


	@Override
	public List<StudentAcademic> getAcademicDtl(Long fileNo) {

		String queryString="FROM StudentAcademic sa WHERE sa.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<StudentAcademic> result= (List<StudentAcademic>)query.list();
		return result;
	}

	@Override
	public void saveAcademicDtl(StudentAcademic studentAcademic) {

		if(studentAcademic.getStdntQualifctnId()==null){
			getCurrentSession().persist(studentAcademic);
		}
		else{
			getCurrentSession().update(studentAcademic);
			saveQualificationSubDtl(studentAcademic.getQualificationSubDtl(), studentAcademic.getFileNo(),studentAcademic.getStdntQualifctnId());
		}
	}

	@Override
	public void saveAcademicDtl(List<StudentAcademic> studentAcademics, Long fileNo) {
		if(studentAcademics!=null && studentAcademics.size()>0){
			deleteAcademicDtlExclusion(studentAcademics, fileNo);
			for(StudentAcademic studentAcademic : studentAcademics){
				saveAcademicDtl(studentAcademic);
			}
		}
	}

	@Override
	public void deleteAcademicDtlExclusion(List<StudentAcademic> studentAcademics, Long fileNo) {

		List<Long> stdntQualifctnId = new ArrayList<Long>();
		if (studentAcademics != null) {
			for (StudentAcademic studentAcademic : studentAcademics) {
				if(studentAcademic.getStdntQualifctnId() != null){
					stdntQualifctnId.add(studentAcademic.getStdntQualifctnId());
				}
			}

			if (stdntQualifctnId.size() == 0) {
				stdntQualifctnId.add(-1L);
			}
		}
		String dltQuery = admissionQueryProps.getProperty("deleteSubjectDtlExclusionByQualifctnStndtId");
		Query query1=getCurrentSession().createSQLQuery(dltQuery).setParameter("File_No", fileNo).setParameterList("Student_Qualification_Id", stdntQualifctnId);
		query1.executeUpdate();	

		String deleteQuery = admissionQueryProps.getProperty("deleteAcademicDtlExclusion");
		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameterList("Student_Qualification_Id", stdntQualifctnId);
		query.executeUpdate();	
	}

	@Override
	public List<QualificationSubject> getQualificaionSubDtl(Long fileNo) {

		String queryString="FROM QualificationSubject qs WHERE qs.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<QualificationSubject> result= query.list();
		return result;
	}

	@Override
	public void saveQualificationSubDtl(List<QualificationSubject> qualificationSubjects, Long fileNo,Long stdntQualifctnId) {
		if(qualificationSubjects!=null && qualificationSubjects.size()>0){
			deleteQualificationSubDtlExclusion(qualificationSubjects, fileNo,stdntQualifctnId);
			for(QualificationSubject qualificationSubject : qualificationSubjects){
				saveQualificationSubDtl(qualificationSubject);
			}
		}
	}

	@Override
	public void saveQualificationSubDtl(QualificationSubject qualificationSubject) {
		if(qualificationSubject==null){
			getCurrentSession().persist(qualificationSubject);
		}
		else{
			getCurrentSession().update(qualificationSubject);
		}
	}

	@Override
	public void deleteQualificationSubDtlExclusion(List<QualificationSubject> qualificationSubjects, Long fileNo, Long stdntQualifctnId) {

		List<Long> stdntSubjctId = new ArrayList<Long>();
		if (qualificationSubjects != null) {
			for (QualificationSubject qualificationSubject : qualificationSubjects) {
				if(qualificationSubject.getStdntSubjctId() != null){
					stdntSubjctId.add(qualificationSubject.getStdntSubjctId());
				}
			}

			if (stdntSubjctId.size() == 0) {
				stdntSubjctId.add(-1L);
			}
		}

		String deleteQuery = admissionQueryProps.getProperty("deleteSubjectDtlExclusion");
		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameter("Student_Qualification_Id", stdntQualifctnId).setParameterList("Stdnt_Subjct_Id", stdntSubjctId);
		query.executeUpdate();	
	}


	@Override
	public List<BranchPreference> getBranchPreference(Long fileNo) {

		String queryString="FROM BranchPreference bp WHERE bp.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<BranchPreference> result= query.list();
		return result;
	}

	@Override
	public void saveBranchPreference(List<BranchPreference> branchPreferences, Long fileNo) {

		if(branchPreferences!=null && branchPreferences.size()>0){
			deleteBranchPreferenceExclusion(branchPreferences, fileNo);
			for(BranchPreference branchPreference : branchPreferences){
				saveBranchPreference(branchPreference);
			}
		}		}

	@Override
	public void saveBranchPreference(BranchPreference branchPreference) {

		if(branchPreference.getBranchPreferenceId()==null){
			getCurrentSession().persist(branchPreference);
		}
		else{
			getCurrentSession().update(branchPreference);
		}
	}
	@Override
	public void deleteBranchPreferenceExclusion(List<BranchPreference> branchPreferences, Long fileNo) {

		List<Long> branchPreferenceId = new ArrayList<Long>();
		if (branchPreferences != null) {
			for (BranchPreference branchPreference : branchPreferences) {
				if(branchPreference.getBranchPreferenceId() != null){
					branchPreferenceId.add(branchPreference.getBranchPreferenceId());
				}
			}

			if (branchPreferenceId.size() == 0) {
				branchPreferenceId.add(-1L);
			}
		}

		String deleteQuery = admissionQueryProps
				.getProperty("deleteBranchPreferenceExclusion");

		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameterList("Branch_Preference_Id", branchPreferenceId);
		query.executeUpdate();
	}


	@Override
	public List<Counselling> getCounsellingDtl(Long fileNo) {

		String queryString="FROM Counselling c WHERE c.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<Counselling> result= query.list();
		return result;
	}

	@Override
	public void saveCounsellingDtl(List<Counselling> counsellings, Long fileNo) {

		if(counsellings!=null && counsellings.size()>0){
			deleteCounsellingDtlExclusion(counsellings, fileNo);
			for(Counselling counselling:counsellings){
				saveCounsellingDtl(counselling);
			}
		}
	}

	@Override
	public void saveCounsellingDtl(Counselling counselling) {
		if(counselling.getStdntCounsllingId()==null){
			getCurrentSession().persist(counselling);
		}
		else{
			getCurrentSession().update(counselling);
		}
	}

	@Override
	public void deleteCounsellingDtlExclusion(List<Counselling> counsellings, Long fileNo) {

		List<Long> stdntCounsllingId = new ArrayList<Long>();
		if (counsellings != null) {
			for (Counselling counselling : counsellings) {
				if(counselling.getStdntCounsllingId() != null){
					stdntCounsllingId.add(counselling.getStdntCounsllingId());
				}
			}

			if (stdntCounsllingId.size() == 0) {
				stdntCounsllingId.add(-1L);
			}
		}

		String deleteQuery = admissionQueryProps
				.getProperty("deleteCounsellingDetailExclusion");

		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameterList("Stdnt_Counslling_Id", stdntCounsllingId);
		query.executeUpdate();
	}

	@Override
	public List<StudentDocument> getDocumentDtl(Long fileNo) {
		String queryString="FROM StudentDocument s WHERE s.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<StudentDocument> result= query.list();
		return result;

	}

	@Override
	public void saveDocumentDtl(List<StudentDocument> documents, Long fileNo) {
		if(documents!=null && documents.size()>0){
			deleteDocumentDtlExclusion(documents, fileNo);
			for(StudentDocument document:documents){
				document.setFileNo(fileNo);
				saveDocumentDtl(document);
			}
		}
	}

	@Override
	public void saveDocumentDtl(StudentDocument document) {
		if(document.getStudentDocumentId()==null){
			getCurrentSession().persist(document);
		}
		else{
			getCurrentSession().update(document);
		}


	}

	@Override
	public void deleteDocumentDtlExclusion(List<StudentDocument> documents,
			Long fileNo) {
		List<Long> studentDocumentId = new ArrayList<Long>();
		if (documents != null) {
			for (StudentDocument studentDocument : documents) {
				if(studentDocument.getStudentDocumentId() != null){
					studentDocumentId.add(studentDocument.getStudentDocumentId());
				}
			}

			if (studentDocumentId.size() == 0) {
				studentDocumentId.add(-1L);
			}
		}
		String deleteQuery = admissionQueryProps
				.getProperty("deleteDocumentDtlExclusion");

		Query query=getCurrentSession().createSQLQuery(deleteQuery).setParameter("File_No", fileNo).setParameterList("Student_Doc_Id", studentDocumentId);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getStudentDocumentDtl() {
		String queryString="Select stdoc.File_No, docm.Document_Id as DocId,docm.Document_Name as DocName,  (case when stdoc.File_No is null then 0 else 1end) as Received" +
				"FROM Document as docm left join StudentDocument as stdoc" ;
		Query query=getCurrentSession().createQuery(queryString);
		List<Object[]> studentDocuments = query.list();
		return studentDocuments;
	}

	class StudentBasicInfoRowMaper implements RowMapper<StudentBasicInfo>{

		public StudentBasicInfo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			logger.info("{} : Putting value in setter of studentBasicInfo bean  : {}",this.getClass().getName());
			StudentBasicInfo basicInfo = new StudentBasicInfo();
			basicInfo.setFirstName(rs.getString("First_Name"));
			basicInfo.setLastName(rs.getString("Last_Name"));
			basicInfo.setAcademicYear(rs.getString("Academic_Year"));
			Long branchId=(CommonUtil.getLongValue(rs.getLong("Branch_Id")));
		    Branch branch=cacheManager.getBranchById(branchId);
			basicInfo.setBranch(branch); 
			Long courseId=(CommonUtil.getLongValue(rs.getLong("Course_Id")));
		    Course course=cacheManager.getCourseById(courseId);
			basicInfo.setCourse(course);
			Long categoryId=(CommonUtil.getLongValue(rs.getLong("Category_Id")));
		    CasteCategory category=cacheManager.getCategoryId(categoryId);
			basicInfo.setCasteCategory(category);
			basicInfo.setDob(rs.getDate("DOB"));
			basicInfo.setEnrollmentNo(rs.getString("Enroll_No"));
			basicInfo.setFatherName(rs.getString("Father_Name"));
			basicInfo.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
			basicInfo.setGender(rs.getString("Gender"));
			basicInfo.setModifiedDate(rs.getDate("Updated_On"));
			basicInfo.setSemester(rs.getString("Semester"));
			Long sessionId=(CommonUtil.getLongValue(rs.getLong("Session_Id")));
		    Session session=cacheManager.getSessionBySessionId(sessionId);
			basicInfo.setSession(session);
			Long batchId=(CommonUtil.getLongValue(rs.getLong("Batch_Id")));
		    Batch batch=cacheManager.getBatchByBatchId(batchId);
			basicInfo.setBatch(batch);
			basicInfo.setRegistrationNo(rs.getString("Registration_No"));
			
			Long centreId=(CommonUtil.getLongValue(rs.getLong("Centre_id")));
		    Centre centre=cacheManager.getCentreByCentreId(centreId);
			basicInfo.setCentre(centre);
			
			Long shiftId=(CommonUtil.getLongValue(rs.getLong("Shift_Id")));
		    Shift shift=cacheManager.getShiftByShiftId(shiftId);
			basicInfo.setShift(shift);
			
			Long sectionId=(CommonUtil.getLongValue(rs.getLong("Section_Id")));
			Section section = cacheManager.getSectionBySectionId(sectionId);
			basicInfo.setSection(section);
			
			return basicInfo;
		}
	}
}