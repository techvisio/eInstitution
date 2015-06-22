package com.techvisio.einstitution.db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.QualificationSubject;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;

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
		if (addresses == null || addresses.size() == 0) {
			addressId.add(-1L);
		}
		else {
			if (addresses != null) {
				for (Address address : addresses) {
					addressId.add(address.getAddressId());
				}
			}

			String deleteQuery = admissionQueryProps
					.getProperty("deleteAddressDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Address_Id", addressId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

		}
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
		if (admissionDiscounts == null || admissionDiscounts.size() == 0) {
			discountId.add(-1L);
		}
		else {
			if (admissionDiscounts != null) {
				for (AdmissionDiscount admissionDiscount : admissionDiscounts) {
					discountId.add(admissionDiscount.getDiscountDtlId());
				}
			}

			String deleteQuery = admissionQueryProps
					.getProperty("deleteAdmissionDisDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Discount_Dtl_Id", discountId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
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
		if (studentAcademics == null || studentAcademics.size() == 0) {
			stdntQualifctnId.add(-1L);
		}
		else {
			if (studentAcademics != null) {
				for (StudentAcademic studentAcademic : studentAcademics) {
					stdntQualifctnId.add(studentAcademic.getStdntQualifctnId());
				}
			}

			//delete subject exclusion for qualifications

			String dltQuery = admissionQueryProps.getProperty("deleteSubjectDtlExclusion");
			SqlParameterSource namedParam = new MapSqlParameterSource("Student_Qualification_Id", stdntQualifctnId)
			.addValue("File_No", fileNo);
			getNamedParamJdbcTemplate().update(dltQuery, namedParam);

			String deleteQuery = admissionQueryProps.getProperty("deleteAcademicDtlExclusion");
			SqlParameterSource namedParameter = new MapSqlParameterSource("Student_Qualification_Id", stdntQualifctnId)
			.addValue("File_No", fileNo);
			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
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
	public void deleteQualificationSubDtlExclusion(
			List<QualificationSubject> qualificationSubjects, Long fileNo) {

		List<Long> stdntSubjctId = new ArrayList<Long>();
		if (qualificationSubjects == null || qualificationSubjects.size() == 0) {
			stdntSubjctId.add(-1L);
		}
		else {
			if (qualificationSubjects != null) {
				for (QualificationSubject qualificationSubject : qualificationSubjects) {
					stdntSubjctId.add(qualificationSubject.getStdntSubjctId());
				}
			}

			String deleteQuery = admissionQueryProps
					.getProperty("deleteQualificationSubjectDtlExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Stdnt_Subjct_Id", stdntSubjctId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
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
		if (branchPreferences == null || branchPreferences.size() == 0) {
			branchPreferenceId.add(-1L);
		}
		else {
			if (branchPreferences != null) {
				for (BranchPreference branchPreference : branchPreferences) {
					branchPreferenceId.add(branchPreference.getBranchPreferenceId());
				}
			}

			String deleteQuery = admissionQueryProps
					.getProperty("deleteBranchPreferenceExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Branch_Preference_Id", branchPreferenceId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
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
		if (counsellings == null || counsellings.size() == 0) {
			stdntCounsllingId.add(-1L);
		}
		else {
			if (counsellings != null) {
				for (Counselling counselling : counsellings) {
					stdntCounsllingId.add(counselling.getStdntCounsllingId());
				}
			}

			String deleteQuery = admissionQueryProps
					.getProperty("deleteCounsellingDetailExclusion");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"Stdnt_Counslling_Id", stdntCounsllingId)
			.addValue("File_No", fileNo);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	}

}