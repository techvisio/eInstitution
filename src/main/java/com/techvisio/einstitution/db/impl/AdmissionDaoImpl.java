package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.AddressDetail;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.util.CommonUtil;

public class AdmissionDaoImpl extends BaseDao implements AdmissionDao {

	private Properties admissionQueryProps;

	public void setAdmissionQueryProps(Properties admissionQueryProps) {

		this.admissionQueryProps = admissionQueryProps;

	}

	
	public StudentDetail getStudentDtlBySearchCriteria(SearchCriteria searchCriteria){

		String getQuery = admissionQueryProps
				.getProperty("getStudentDtlDynamically");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", searchCriteria.getFileNo()==null?null:searchCriteria.getFileNo())
		.addValue("Email_Id", StringUtils.isEmpty(searchCriteria.getEmailId())?null:searchCriteria.getEmailId())
		.addValue("Enroll_No", StringUtils.isEmpty(searchCriteria.getEnrollNo())?null:searchCriteria.getEnrollNo())
		.addValue("Uni_Enroll_No", StringUtils.isEmpty(searchCriteria.getUniEnrollNo())?null:searchCriteria.getUniEnrollNo())
		.addValue("Self_Mobile_No", StringUtils.isEmpty(searchCriteria.getMobileNo())?null:searchCriteria.getMobileNo());
		
		StudentDetail studentDetail=getNamedParamJdbcTemplate().queryForObject(
				getQuery, namedParameter, new StudentDetailRowMapper());
		
		    return studentDetail;
	}
	
	public StudentDetail getStudentDtl(Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getStudentDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		StudentDetail studentDetail = getNamedParamJdbcTemplate().queryForObject(
				getQuery, namedParameter, new StudentDetailRowMapper());


			List<StudentAcademicDetail> academicDetails = getAcademicDtl(fileNo);
			studentDetail.setAcademicDtl(academicDetails);

			List<AddressDetail> addressDetails = getAddressDtl(fileNo);
			studentDetail.setAddressDtl(addressDetails);

			List<AdmissionDiscountDtl> admissionDiscountDtls = getAdmissionDisDtl(fileNo);
			studentDetail.setDiscountDtl(admissionDiscountDtls);

			List<BranchPreference> branchPreferences = getBranchPreference(fileNo);
			studentDetail.setBranchPreference(branchPreferences);

			List<CounsellingDetail> counsellingDetails = getCounsellingDetail(fileNo);
			studentDetail.setCounsellingDtl(counsellingDetails);

		return studentDetail;
	}

	public void addStudentDtl(StudentDetail studentDtl) {

		String addQuery = admissionQueryProps.getProperty("addStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", studentDtl.getFileNo())
        .addValue("Registration_No", studentDtl.getRegistrationNo())
		.addValue("Enroll_No", studentDtl.getEnrollNo())
		.addValue("Uni_Enroll_No", studentDtl.getUniEnrollNo())
		.addValue("First_Name", studentDtl.getFirstName())
		.addValue("Last_Name", studentDtl.getLastName())
		.addValue("Father_Name", studentDtl.getFatherName())
		.addValue("Photo", studentDtl.getPhoto())
		.addValue("Mother_Name", studentDtl.getMotherName())
		.addValue("Gender", studentDtl.getGender())
		.addValue("DOB", studentDtl.getDob())
		.addValue("Blood_Group", studentDtl.getBloodGroup())
		.addValue("Father_Occupation", studentDtl.getFatherOccupation())
		.addValue("FixedLine_No", studentDtl.getFixedlineNo())
		.addValue("Self_Mobile_No", studentDtl.getSelfMobileNo())
		.addValue("Parent_Mobile_No", studentDtl.getParentMobileNo())
		.addValue("Gaurdian_Mobile_No",
				studentDtl.getGaurdianMobileNo())
				.addValue("Email_Id", studentDtl.getEmailId())
				.addValue("Gaurdian_Email_Id", studentDtl.getGaurdianEmailId())
				.addValue("Hostel", studentDtl.isHostel())
				.addValue("Transportation", studentDtl.isTransportation())
				.addValue("Academic_Year", studentDtl.getAcademicYear())
				.addValue("Semester", studentDtl.getSemester())
				.addValue("Management_Approval",
						studentDtl.isManagementApproval())
						.addValue("Fee_Paid", studentDtl.isFeePaid())
						.addValue("Category_Id", studentDtl.getCategoryId())
						.addValue("Course_Id", studentDtl.getCourseId())
						.addValue("Branch_Id", studentDtl.getBranchId())
						.addValue("Created_By", studentDtl.getCreatedBy())
						.addValue("Updated_By", studentDtl.getUpdatedBy())
						.addValue("Admission_Mode", studentDtl.getAdmissionMode())
						.addValue("Referred_By", studentDtl.getReferredBy())
						.addValue("Quota_Code", studentDtl.getQuotaCode())
						.addValue("Lateral", studentDtl.isLateral())
						.addValue("Remarks", studentDtl.getRemarks())
						.addValue("Application_Status",studentDtl.getApplicationStatus());;

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		if (studentDtl.getAcademicDtl() != null) {

			saveAcademicDtl(studentDtl.getAcademicDtl());
		}

		if (studentDtl.getDiscountDtl() != null) {

				saveAdmissionDisDtl(studentDtl.getDiscountDtl());
		}

		if (studentDtl.getAddressDtl() != null) {

				saveAddressDetails(studentDtl.getAddressDtl());
		}

		if (studentDtl.getBranchPreference() != null) {

				saveBranchPreference(studentDtl.getBranchPreference());
		}

		if (studentDtl.getCounsellingDtl() !=null) {

				saveCounsellingDetail(studentDtl.getCounsellingDtl());
		}


	}

	public void updateStudentDtl(StudentDetail studentDtl) {

		String updateQuery = admissionQueryProps
				.getProperty("updateStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", studentDtl.getFileNo())
		.addValue("Registration_No",studentDtl.getRegistrationNo())
		.addValue("Enroll_No", studentDtl.getEnrollNo())
		.addValue("Uni_Enroll_No", studentDtl.getUniEnrollNo())
		.addValue("First_Name", studentDtl.getFirstName())
		.addValue("Last_Name", studentDtl.getLastName())
		.addValue("Father_Name", studentDtl.getFatherName())
		.addValue("Photo", studentDtl.getPhoto())
		.addValue("Mother_Name", studentDtl.getMotherName())
		.addValue("Gender", studentDtl.getGender())
		.addValue("DOB", studentDtl.getDob())
		.addValue("Blood_Group", studentDtl.getBloodGroup())
		.addValue("Father_Occupation", studentDtl.getFatherOccupation())
		.addValue("FixedLine_No", studentDtl.getFixedlineNo())
		.addValue("Self_Mobile_No", studentDtl.getSelfMobileNo())
		.addValue("Parent_Mobile_No", studentDtl.getParentMobileNo())
		.addValue("Gaurdian_Mobile_No",
				studentDtl.getGaurdianMobileNo())
				.addValue("Email_Id", studentDtl.getEmailId())
				.addValue("Gaurdian_Email_Id", studentDtl.getGaurdianEmailId())
				.addValue("Hostel", studentDtl.isHostel())
				.addValue("Transportation", studentDtl.isTransportation())
				.addValue("Academic_Year", studentDtl.getAcademicYear())
				.addValue("Semester", studentDtl.getSemester())
				.addValue("Management_Approval",
						studentDtl.isManagementApproval())
						.addValue("Fee_Paid", studentDtl.isFeePaid())
						.addValue("Category_Id", studentDtl.getCategoryId())
						.addValue("Course_Id", studentDtl.getCourseId())
						.addValue("Branch_Id", studentDtl.getBranchId())
						.addValue("Created_By", studentDtl.getCreatedBy())
						.addValue("Updated_By", studentDtl.getUpdatedBy())
						.addValue("Admission_Mode", studentDtl.getAdmissionMode())
						.addValue("Referred_By", studentDtl.getReferredBy())
						.addValue("Quota_Code", studentDtl.getQuotaCode())
						.addValue("Lateral", studentDtl.isLateral())
						.addValue("Remarks", studentDtl.getRemarks())
						.addValue("Application_Status", studentDtl.getApplicationStatus());;

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

		if (studentDtl.getAcademicDtl() != null) {

			saveAcademicDtl(studentDtl.getAcademicDtl());
		}

		if (studentDtl.getDiscountDtl() != null) {

				saveAdmissionDisDtl(studentDtl.getDiscountDtl());
		}

		if (studentDtl.getAddressDtl() != null) {

				saveAddressDetails(studentDtl.getAddressDtl());
		}

		if (studentDtl.getBranchPreference() != null) {

				saveBranchPreference(studentDtl.getBranchPreference());
		}

		if (studentDtl.getCounsellingDtl() !=null) {

				saveCounsellingDetail(studentDtl.getCounsellingDtl());
		}


	}

	public void deleteSudentDtl(Long fileNo) {

        StudentDetail detail = new StudentDetail();
		
		deleteAcademicDtl(detail.getAcademicDtl());

		deleteAdmissionDisDtl(detail.getDiscountDtl());

		deleteAddressDtl(detail.getAddressDtl());

		deleteBranchPreference(detail.getBranchPreference());

		deleteCounsellingDetail(detail.getCounsellingDtl());

		String deleteQuery = admissionQueryProps
				.getProperty("deleteStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	private List<StudentAcademicDetail> getAcademicDtl(final Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getAcademicDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		List<StudentAcademicDetail> studentAcademicDetails = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<StudentAcademicDetail>() {

					public StudentAcademicDetail mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						StudentAcademicDetail studentAcademicDetail = new StudentAcademicDetail();

						studentAcademicDetail.setFileNo(rs
								.getLong("File_No"));
						studentAcademicDetail.setQualificationId(CommonUtil.getLongValue(rs
								.getLong("Qualification_Id")));
						studentAcademicDetail.setUniversity(rs
								.getString("University"));
						studentAcademicDetail.setCollegeName(rs
								.getString("College_Name"));
						studentAcademicDetail.setPassingYear(rs
								.getString("Passing_Year"));
						studentAcademicDetail.setPercentage(rs
								.getFloat("Percentage"));
						studentAcademicDetail.setRollNo(rs
								.getString("Roll_No"));
						
						List<QualificationSubjectDtl> qualificationSubjectDtls = getQualificationDtl(fileNo);
						studentAcademicDetail.setQualificationSubDtl(qualificationSubjectDtls);

						return studentAcademicDetail;


					}
				});
		return studentAcademicDetails;
	}

	private void saveAcademicDtl(List<StudentAcademicDetail> academicDetails){
		
		if(academicDetails != null){
			deleteAcademicDtl(academicDetails);
			for(StudentAcademicDetail academicDetail:academicDetails){
				saveAcademicDtl(academicDetail);
			}
			}

	}
	
	private void saveAcademicDtl(StudentAcademicDetail academicDtl) {

		String upsertQuery = admissionQueryProps.getProperty("upsertAcademicDtl");
		if(academicDtl.getQualificationId() != null)
		{
			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"File_No", academicDtl.getFileNo())
			.addValue("University", academicDtl.getUniversity())
			.addValue("College_Name", academicDtl.getCollegeName())
			.addValue("Passing_Year", academicDtl.getPassingYear())
			.addValue("Percentage", academicDtl.getPercentage())
			.addValue("Roll_No", academicDtl.getRollNo())
			.addValue("Qualification_Id", academicDtl.getQualificationId());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

			if (academicDtl.getQualificationSubDtl() != null) {

					saveQualificationDtl(academicDtl.getQualificationSubDtl());
				}
		}

	}

//	private void updateAcademicDtl(StudentAcademicDetail academicDtl) {
//
//		String fileNo = academicDtl.getFileNo();
//
//		deleteAcademicDtl(fileNo);
//
//		addAcademicDtl(academicDtl);
//
//		// String updateQuery = admissionQueryProps
//		// .getProperty("updateAcademicDtl");
//		//
//		// SqlParameterSource namedParameter = new MapSqlParameterSource(
//		// "File_No", academicDtl.getFileNo())
//		// .addValue("University", academicDtl.getUniversity())
//		// .addValue("College_Name", academicDtl.getCollegeName())
//		// .addValue("Passing_Year", academicDtl.getPassingYear())
//		// .addValue("Percentage", academicDtl.getPercentage())
//		// .addValue("Roll_No", academicDtl.getRollNo())
//		// .addValue("Qualification_Id", academicDtl.getQualificationId());
//		//
//		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
//
//		if (academicDtl.getQualificationSubDtl() != null) {
//
//			for (QualificationSubjectDtl qualificationSubjectDtl : academicDtl
//					.getQualificationSubDtl()) {
//
//				updateQualificationDtl(qualificationSubjectDtl);
//			}
//
//		}
//
//
//	}

	private void deleteAcademicDtl(List<StudentAcademicDetail> academicDetails) {

		StudentAcademicDetail detail = new StudentAcademicDetail();
		deleteQualificationDtl(detail.getQualificationSubDtl());

		List<Long> qualificationIds=new ArrayList<Long>();
		Long fileNo=null;
		if(academicDetails != null){
			for(StudentAcademicDetail academicDetail:academicDetails){
				qualificationIds.add(academicDetail.getQualificationId());
				fileNo=academicDetail.getFileNo();
			}

			 if(qualificationIds.size()==0){
					
				 qualificationIds.add(-1L);
			     }
			
			
		String deleteQuery = admissionQueryProps
				.getProperty("deleteAcademicDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo)
		.addValue("Qualification_Id", qualificationIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
	}
	private List<AddressDetail> getAddressDtl(Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getAddressDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		List<AddressDetail> addressDetails = getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new RowMapper<AddressDetail>() {

					public AddressDetail mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						AddressDetail addressDetail = new AddressDetail();

						addressDetail.setHouseNo(rs.getString("House_No"));
						addressDetail.setLocality(rs.getString("Locality"));
						addressDetail.setLandmark(rs.getString("Landmark"));
						addressDetail.setDistrict(rs.getString("District"));
						addressDetail.setCity(rs.getString("City"));
						addressDetail.setPincode(rs.getInt("Pincode"));
						addressDetail.setFileNo(rs.getLong("File_No"));
						addressDetail.setAddressType(rs
								.getString("Address_Type"));
						addressDetail.setState(rs.getString("State_Id"));

						return addressDetail;
					}
				});
		return addressDetails;
	}
	
	private void saveAddressDetails(List<AddressDetail> addresses){
	
		if(addresses != null){
		deleteAddressDtl(addresses);
		for(AddressDetail address:addresses){
			saveAddressDtl(address);
		}
		}
		
	}

	private void saveAddressDtl(AddressDetail addressDtl) {

		String upsertQuery = admissionQueryProps.getProperty("upsertAddressDtl");

		if(addressDtl.getState()!=null)
		{
			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"File_No", addressDtl.getFileNo())
			.addValue("House_No", addressDtl.getHouseNo())
			.addValue("Locality", addressDtl.getLocality())
			.addValue("Landmark", addressDtl.getLandmark())
			.addValue("District", addressDtl.getDistrict())
			.addValue("City", addressDtl.getCity())
			.addValue("Pincode", addressDtl.getPincode())
			.addValue("Address_Type", addressDtl.getAddressType())
			.addValue("State_Id", addressDtl.getState());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);
		}
	}

//	private void updateAddressDtl(AddressDetail addressDtl) {
//
//		String fileNo=addressDtl.getFileNo();
//
//		deleteAddressDtl(fileNo);
//
//		addAddressDtl(addressDtl);
//
//		// String updateQuery = admissionQueryProps
//		// .getProperty("updateAddressDtl");
//		//
//		// SqlParameterSource namedParameter = new MapSqlParameterSource(
//		// "File_No", addressDtl.getFileNo())
//		// .addValue("House_No", addressDtl.getHouseNo())
//		// .addValue("Locality", addressDtl.getLocality())
//		// .addValue("Landmark", addressDtl.getLandmark())
//		// .addValue("District", addressDtl.getDistrict())
//		// .addValue("City", addressDtl.getCity())
//		// .addValue("Pincode", addressDtl.getPincode())
//		// .addValue("Address_Type", addressDtl.getAddressType())
//		// .addValue("State_Id", addressDtl.getState());
//		//
//		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
//		//
//	}

	private void deleteAddressDtl(List<AddressDetail> addresses) {
		
		List<String> addressTypes=new ArrayList<String>();
		Long fileNo=null;
		if(addresses != null){
			for(AddressDetail address:addresses){
				addressTypes.add(address.getAddressType());
				fileNo=address.getFileNo();
			}

			  if(addressTypes.size()==0){
					
				  addressTypes.add(" ");
			     }
			  
			
			String deleteQuery = admissionQueryProps
					.getProperty("deleteAddressDtl");

			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"File_No", fileNo)
			.addValue("Address_Type", addressTypes);

			getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		}
	}

	private List<AdmissionDiscountDtl> getAdmissionDisDtl(Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getAdmissionDisDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		List<AdmissionDiscountDtl> admissionDiscountDtls = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<AdmissionDiscountDtl>() {

					public AdmissionDiscountDtl mapRow(ResultSet rs,
							int rowNum) throws SQLException {
						AdmissionDiscountDtl admissionDiscountDtl = new AdmissionDiscountDtl();

						admissionDiscountDtl.setAmount(rs
								.getDouble("Amount"));
						admissionDiscountDtl.setFeeHeadId(CommonUtil.getLongValue(rs
								.getLong("FeeHead_Id")));
						admissionDiscountDtl.setFileNo(rs
								.getLong("File_No"));
						admissionDiscountDtl.setPercent(rs
								.getFloat("Percent"));
						admissionDiscountDtl.setDiscountType(rs.getString("Discount_Type"));

						return admissionDiscountDtl;
					}
				});
		return admissionDiscountDtls;
	}

	private void saveAdmissionDisDtl(List<AdmissionDiscountDtl> admissionDiscountDtls){
		

		if(admissionDiscountDtls != null){
			deleteAdmissionDisDtl(admissionDiscountDtls);
			for(AdmissionDiscountDtl admissionDiscountDtl:admissionDiscountDtls){
				saveAdmissionDisDtl(admissionDiscountDtl);
			}
			}
	
	
	}
	private void saveAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {

		String upsertQuery = admissionQueryProps.getProperty("upsertAdmissionDisDtl");

		if(admissionDisDtl.getFeeHeadId()!=null)
		{
			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"File_No", admissionDisDtl.getFileNo())
			.addValue("FeeHead_Id", admissionDisDtl.getFeeHeadId())
			.addValue("Amount", admissionDisDtl.getAmount())
			.addValue("Percent", admissionDisDtl.getPercent())
			.addValue("Discount_Type", admissionDisDtl.getDiscountType());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);
		}
	}

//	private void updateAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {
//
//		String fileNo=admissionDisDtl.getFileNo();
//
//		deleteAdmissionDisDtl(fileNo);
//
//		addAdmissionDisDtl(admissionDisDtl);
//
//		// String updateQuery = admissionQueryProps
//		// .getProperty("updateAdmissionDisDtl");
//		//
//		// SqlParameterSource namedParameter = new MapSqlParameterSource(
//		// "File_No", admissionDisDtl.getFileNo())
//		// .addValue("FeeHead_Id", admissionDisDtl.getFeeHeadId())
//		// .addValue("Amount", admissionDisDtl.getAmount())
//		// .addValue("Percent", admissionDisDtl.getPercent());
//		//
//		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
//		//
//	}

	private void deleteAdmissionDisDtl(List<AdmissionDiscountDtl> admissionDiscountDtls) {

		List<Long> feeHeadIds=new ArrayList<Long>();
		Long fileNo=null;
		if(admissionDiscountDtls != null){
			for(AdmissionDiscountDtl admissionDiscountDtl:admissionDiscountDtls){
				feeHeadIds.add(admissionDiscountDtl.getFeeHeadId());
				fileNo=admissionDiscountDtl.getFileNo();
			}
		
			  if(feeHeadIds.size()==0){
					
				  feeHeadIds.add(-1L);
			     }
			  
		String deleteQuery = admissionQueryProps
				.getProperty("deleteAdmissionDisDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo)
		.addValue("FeeHead_Id", feeHeadIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
	}
	private List<QualificationSubjectDtl> getQualificationDtl(Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getQualificationSubjectDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		List<QualificationSubjectDtl> qualificationSubjectDtls = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<QualificationSubjectDtl>() {

					public QualificationSubjectDtl mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						QualificationSubjectDtl qualificationSubjectDtl = new QualificationSubjectDtl();

						qualificationSubjectDtl.setSubjectId(CommonUtil.getLongValue(rs
								.getLong("Subject_Id")));
						qualificationSubjectDtl.setQualificationId(CommonUtil.getLongValue(rs
								.getLong("Qualification_Id")));
						qualificationSubjectDtl.setFileNo(rs
								.getLong("File_No"));
						qualificationSubjectDtl.setMarksObtained(rs
								.getDouble("Marks_Obtained"));
						qualificationSubjectDtl.setMaxMarks(rs
								.getDouble("Max_Marks"));

						return qualificationSubjectDtl;
					}

				});
		return qualificationSubjectDtls;
	}

	private void saveQualificationDtl(List<QualificationSubjectDtl> qualificationSubjectDtls){
	
		if(qualificationSubjectDtls != null){
			deleteQualificationDtl(qualificationSubjectDtls);
			for(QualificationSubjectDtl qualificationSubjectDtl:qualificationSubjectDtls){
				
				saveQualificationDtl(qualificationSubjectDtl);
			}
			}
	}
	
	private void saveQualificationDtl(QualificationSubjectDtl qualificationDtl) {

		String upsertQuery = admissionQueryProps
				.getProperty("upsertQualificationSubjectDtl");

		if(qualificationDtl.getSubjectId() != null)
		{
			SqlParameterSource namedParameter = new MapSqlParameterSource(
					"File_No", qualificationDtl.getFileNo())
			.addValue("Subject_Id", qualificationDtl.getSubjectId())
			.addValue("Qualification_Id",
					qualificationDtl.getQualificationId())
					.addValue("Marks_Obtained", qualificationDtl.getMarksObtained())
					.addValue("Max_Marks", qualificationDtl.getMaxMarks());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);
		}
	}

//	private void updateQualificationDtl(QualificationSubjectDtl qualificationDtl) {
//
//		String fileNo=qualificationDtl.getFileNo();
//
//		deleteQualificationDtl(fileNo);
//
//		addQualificationDtl(qualificationDtl);
//
//		// String updateQuery = admissionQueryProps
//		// .getProperty("updateQualificationSubjectDtl");
//		//
//		// SqlParameterSource namedParameter = new MapSqlParameterSource(
//		// "File_No", qualificationDtl.getFileNo())
//		// .addValue("Subject_Id", qualificationDtl.getSubjectId())
//		// .addValue("Qualification_Id",
//		// qualificationDtl.getQualificationId())
//		// .addValue("Marks_Obtained", qualificationDtl.getMarksObtained())
//		// .addValue("Max_Marks", qualificationDtl.getMaxMarks());
//		//
//		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
//
//	}
//
	private void deleteQualificationDtl(List<QualificationSubjectDtl> qualificationSubjectDtls) {

		List<Long> subjectIds=new ArrayList<Long>();
		Long fileNo=null;
		if(qualificationSubjectDtls != null){
			for(QualificationSubjectDtl qualificationSubjectDtl:qualificationSubjectDtls){
				subjectIds.add(qualificationSubjectDtl.getSubjectId());
				fileNo=qualificationSubjectDtl.getFileNo();
			}
		
			  if(subjectIds.size()==0){
					
				  subjectIds.add(-1L);
			     }
			
		String deleteQuery = admissionQueryProps
				.getProperty("deleteQualificationSubjectDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo)
		.addValue("Subject_Id", subjectIds);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}
	}
	private List<BranchPreference> getBranchPreference(Long fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getBranchPreferenceByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		List<BranchPreference> branchPreferences = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<BranchPreference>() {

					public BranchPreference mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						BranchPreference branchPreference = new BranchPreference();

						branchPreference.setFileNo(rs
								.getLong("File_No"));

						branchPreference.setBranchId(CommonUtil.getLongValue(rs
								.getLong("Branch_Id")));
						branchPreference.setBranchPreferenceId(CommonUtil.getLongValue(rs
								.getLong("Branch_Preference_Id")));
						return branchPreference;
					}
				});

		return branchPreferences;
	}

	private void saveBranchPreference(List<BranchPreference> branchPreferences){
		
		if(branchPreferences != null){
			deleteBranchPreference(branchPreferences);
			for(BranchPreference branchPreference:branchPreferences){
				saveBranchPreference(branchPreference);
			}
			}
	
	}
	private void saveBranchPreference(BranchPreference branchPreference) {

		String upsertQuery = admissionQueryProps
				.getProperty("upsertBranchPreference");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", branchPreference.getFileNo())
		.addValue("Branch_Preference_Id",
				branchPreference.getBranchPreferenceId())
				.addValue("Branch_Id", branchPreference.getBranchId());

		getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

	}

//	private void updateBranchPreference(BranchPreference branchPreference) {
//
//		String fileNo=branchPreference.getFileNo();
//
//		deleteBranchPreference(fileNo);
//
//		addBranchPreference(branchPreference);
//
//	}

	private void deleteBranchPreference(List<BranchPreference> branchPreferences) {

		List<Long> branchPreferenceIds=new ArrayList<Long>();
		Long fileNo=null;
		if(branchPreferences != null){
			for(BranchPreference branchPreference:branchPreferences){
				branchPreferenceIds.add(branchPreference.getBranchPreferenceId());
				fileNo=branchPreference.getFileNo();
			}
			if(branchPreferenceIds.size()==0){
				
				branchPreferenceIds.add(-1L);
			}
		
		String deleteQuery = admissionQueryProps
				.getProperty("deleteBranchPreference");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo).addValue("Branch_Preference_Id", branchPreferenceIds);
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
	}
	private List<CounsellingDetail> getCounsellingDetail(Long fileNo) {
		String getQuery = admissionQueryProps.getProperty("getcounsellingDetailByFileNo");


		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<CounsellingDetail> counsellingDetails = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<CounsellingDetail>() {

					public CounsellingDetail mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						CounsellingDetail counsellingDetail = new CounsellingDetail();

						counsellingDetail.setFileNo(rs.getLong("File_No"));
						counsellingDetail.setCounsellingId(CommonUtil.getLongValue(rs.getLong("Counselling_Id")));
						counsellingDetail.setRollNo(rs.getString("Roll_No"));
						counsellingDetail.setRank(CommonUtil.getLongValue(rs.getLong("Rank")));
						counsellingDetail.setCategoryRank(CommonUtil.getLongValue(rs.getLong("Category_Rank")));
						counsellingDetail.setPercentile(rs.getFloat("Percentile"));

						return counsellingDetail;
					}
				});

		return counsellingDetails;
	}

	private void saveCounsellingDetail(List<CounsellingDetail> counsellingDetails){
		
		if(counsellingDetails != null){
			deleteCounsellingDetail(counsellingDetails);
			for(CounsellingDetail counsellingDetail:counsellingDetails){
				saveCounsellingDetail(counsellingDetail);
			}
			}
		
	}
	private void saveCounsellingDetail(CounsellingDetail counsellingDetail) {

		String upsertQuery = admissionQueryProps.getProperty("upsertCounsellingDetail");

		if(counsellingDetail.getCounsellingId()!=null)
		{
			SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", counsellingDetail.getFileNo())
			.addValue("Counselling_Id", counsellingDetail.getCounsellingId())
			.addValue("Roll_No", counsellingDetail.getRollNo())
			.addValue("Rank", counsellingDetail.getRank())
			.addValue("Category_Rank", counsellingDetail.getCategoryRank())
			.addValue("Percentile", counsellingDetail.getPercentile());

			getNamedParamJdbcTemplate().update(upsertQuery, namedParameter);

		}
	}


//	private void updateCounsellingDetail(CounsellingDetail counsellingDetail) {
//
//		String fileNo=counsellingDetail.getFileNo();
//
//		deleteCounsellingDetail(fileNo);
//
//		addCounsellingDetail(counsellingDetail);
//
//	}
//
	private void deleteCounsellingDetail(List<CounsellingDetail> counsellingDetails) {

		List<Long> counsellingIds=new ArrayList<Long>();
		Long fileNo=null;
		if(counsellingDetails != null){
			for(CounsellingDetail counsellingDetail:counsellingDetails){
				counsellingIds.add(counsellingDetail.getCounsellingId());
				fileNo=counsellingDetail.getFileNo();
			}

            if(counsellingIds.size()==0){
				
	              counsellingIds.add(-1L);
			     }
		
		
		String deleteQuery = admissionQueryProps.getProperty("deleteCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Counselling_Id", counsellingIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
}

public class StudentDetailRowMapper implements RowMapper<StudentDetail>{
	public StudentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

		StudentDetail studentDetail = new StudentDetail();

		studentDetail.setRegistrationNo(rs.getString("Registration_No"));
		studentDetail.setFileNo(rs.getLong("File_No"));
		studentDetail.setEnrollNo(rs.getString("Enroll_No"));
		studentDetail.setUniEnrollNo(rs
				.getString("Uni_Enroll_No"));
		studentDetail.setPhoto(rs.getBytes("Photo"));
		studentDetail.setFirstName(rs.getString("First_Name"));
		studentDetail.setLastName(rs.getString("Last_Name"));
		studentDetail.setFatherName(rs.getString("Father_Name"));
		studentDetail.setMotherName(rs.getString("Mother_Name"));
		studentDetail.setGender(rs.getString("Gender"));
		studentDetail.setDob(rs.getDate("DOB"));
		studentDetail.setBloodGroup(rs.getString("Blood_Group"));
		studentDetail.setFatherOccupation(rs
				.getString("Father_Occupation"));
		studentDetail.setFixedLineNo(rs
				.getString("FixedLine_No"));
		studentDetail.setSelfMobile_No(rs
				.getString("Self_Mobile_No"));
		studentDetail.setParentMobileNo(rs
				.getString("Parent_Mobile_No"));
		studentDetail.setGaurdianMobileNo(rs
				.getString("Gaurdian_Mobile_No"));
		studentDetail.setEmailId(rs.getString("Email_Id"));
		studentDetail.setGaurdianEmailId(rs
				.getString("Gaurdian_Email_Id"));
		studentDetail.setHostel(rs.getBoolean("Hostel"));
		studentDetail.setTransportation(rs
				.getBoolean("Transportation"));
		studentDetail.setAcademicYear(rs
				.getString("Academic_Year"));
		studentDetail.setSemester(rs.getString("Semester"));
		studentDetail.setManagementApproval(rs
				.getBoolean("Management_Approval"));
		studentDetail.setFeePaid(rs.getBoolean("Fee_Paid"));
		studentDetail.setCategoryId(CommonUtil.getLongValue(rs.getLong("Category_Id")));
		studentDetail.setCourseId(CommonUtil.getLongValue(rs.getLong("Course_Id")));
		studentDetail.setBranchId(CommonUtil.getLongValue(rs.getLong("Branch_Id")));
		studentDetail.setCreatedBy(rs.getString("Created_By"));
		studentDetail.setCreated_On(rs.getDate("Created_On"));
		studentDetail.setUpdatedBy(rs.getString("Updated_By"));
		studentDetail.setUpdatedOn(rs.getDate("Updated_On"));
		studentDetail.setAdmissionMode(rs.getString("Admission_Mode"));
		studentDetail.setReferredBy(rs.getString("Referred_By"));
		studentDetail.setQuotaCode(rs.getString("Quota_Code"));
		studentDetail.setLateral(rs.getBoolean("Lateral"));
		studentDetail.setRemarks(rs.getString("Remarks"));
		studentDetail.setApplicationStatus(rs.getString("Application_Status"));

		return studentDetail;
	}
	
	
}
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit) {
		//StudentBasicInfo info = new StudentBasicInfo();
		
		String getQuery = admissionQueryProps.getProperty("getAdmissionBasicInfo");
		SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
		
		List<StudentBasicInfo> infos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new StudentBasicInfoRowMaper());
		return infos;
	}

public StudentBasicInfo getStudentBsInfo(Long fileNo) {
	String getQuery = admissionQueryProps.getProperty("getStudentBasicInfoByFileNo");
	SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
	StudentBasicInfo info = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new StudentBasicInfoRowMaper());
	
	return info;
}


public List<StudentBasicInfo> getUnapprovedAdmissions(int limit) {
	
	String getQuery = admissionQueryProps.getProperty("getUnapprovedAdmissions");
	SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
	
	List<StudentBasicInfo> infos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new StudentBasicInfoRowMaper());
	return infos;
}



class StudentBasicInfoRowMaper implements RowMapper<StudentBasicInfo>{

	public StudentBasicInfo mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		StudentBasicInfo basicInfo = new StudentBasicInfo();
		basicInfo.setFirstName(rs.getString("First_Name"));
		basicInfo.setLastName(rs.getString("Last_Name"));
		basicInfo.setAcademicYear(rs.getString("Academic_Year"));
		basicInfo.setBranch(rs.getString("Branch"));
		basicInfo.setCourse(rs.getString("Course"));
		basicInfo.setDob(rs.getDate("DOB"));
		basicInfo.setEnrollmentNo(rs.getString("Enroll_No"));
		basicInfo.setFatherName(rs.getString("Father_Name"));
		basicInfo.setFileNo(rs.getLong("File_No"));
		basicInfo.setGender(rs.getString("Gender"));
		basicInfo.setModifiedDate(rs.getDate("Updated_On"));
		basicInfo.setSemester(rs.getString("Semester"));
		
		return basicInfo;
	}
}
}