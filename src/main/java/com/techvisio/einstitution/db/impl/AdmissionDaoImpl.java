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
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;

@Transactional
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
	public void saveStudent(Student student) {

		if(student.getFileNo()==null){
			getCurrentSession().persist(student);
		}
		else{
			getCurrentSession().update(student);
			saveAcademicDtl(student.getAcademicDtl(), student.getFileNo());
			saveDiscountDtl(student.getDiscountDtl(),student.getFileNo());
			saveAddressDtl(student.getAddressDtl(),student.getFileNo());
			saveBranchPreference(student.getBranchPreference(),student.getFileNo());
			saveCounsellingDtl(student.getCounsellingDtl(),student.getFileNo());
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
			deleteAddressDtlExclusion(addresses, fileNo);
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
	public void deleteAddressDtlExclusion(List<Address> addresses, Long fileNo) {

		List<Long> addressId = new ArrayList<Long>();
			if (addresses != null) {
				for (Address address : addresses) {
					if(address.getAddressId() != null){
					addressId.add(address.getAddressId());
					}
				}
				
				if (addressId.size() == 0) {
					addressId.add(-1L);
				}
			}
			String deleteQuery = admissionQueryProps
					.getProperty("deleteAddressDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Address_Id", addressId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
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

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Discount_Dtl_Id", discountId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	

	@Override
	public List<StudentAcademic> getAcademicDtl(Long fileNo) {

		String queryString="FROM StudentAcademic sa WHERE s.fileNo = "+fileNo;
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
			saveQualificationSubDtl(studentAcademic.getQualificationSubDtl(), studentAcademic.getFileNo());
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
			String dltQuery = admissionQueryProps.getProperty("deleteSubjectDtlExclusion");
			SqlParameterSource namedParam = new MapSqlParameterSource("Student_Qualification_Id", stdntQualifctnId)
			.addValue("File_No", fileNo);
			getNamedParamJdbcTemplate().update(dltQuery, namedParam);

			String deleteQuery = admissionQueryProps.getProperty("deleteAcademicDtlExclusion");
			SqlParameterSource namedParameter = new MapSqlParameterSource("Student_Qualification_Id", stdntQualifctnId)
			.addValue("File_No", fileNo);
			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	
	@Override
	public List<QualificationSubject> getQualificaionSubDtl(Long fileNo) {

		String queryString="FROM QualificationSubject qs WHERE qs.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		List<QualificationSubject> result= query.list();
		return result;
	}

	@Override
	public void saveQualificationSubDtl(List<QualificationSubject> qualificationSubjects, Long fileNo) {
		if(qualificationSubjects!=null && qualificationSubjects.size()>0){
			deleteQualificationSubDtlExclusion(qualificationSubjects, fileNo);
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
	public void deleteQualificationSubDtlExclusion(List<QualificationSubject> qualificationSubjects, Long fileNo) {

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

			String deleteQuery = admissionQueryProps
					.getProperty("deleteQualificationSubjectDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Stdnt_Subjct_Id", stdntSubjctId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
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

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Branch_Preference_Id", branchPreferenceId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
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

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Stdnt_Counslling_Id", stdntCounsllingId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
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

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Student_Doc_Id", studentDocumentId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}

	@Override
	public List<Object[]> getStudentDocumentDtl() {
		String queryString="Select stdoc.File_No, docm.Document_Id as DocId,docm.Document_Name as DocName,  (case when stdoc.File_No is null then 0 else 1end) as Received" +
							"FROM Document as docm left join StudentDocument as stdoc" ;
		Query query=getCurrentSession().createQuery(queryString);
		List<Object[]> studentDocuments = query.list();
		return studentDocuments;
	}

		
	

}