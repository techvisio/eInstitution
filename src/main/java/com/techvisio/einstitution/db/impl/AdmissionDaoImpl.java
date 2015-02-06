package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.AddressDetail;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;

public class AdmissionDaoImpl extends BaseDao implements AdmissionDao {

	private Properties admissionQueryProps;

	public void setAdmissionQueryProps(Properties admissionQueryProps) {

		this.admissionQueryProps = admissionQueryProps;

	}

	public StudentDetail getStudentDtl(String fileNo) {

		String getQuery = admissionQueryProps
				.getProperty("getStudentDtlByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		List<StudentDetail> studentDetails = getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new RowMapper<StudentDetail>() {

					public StudentDetail mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						StudentDetail studentDetail = new StudentDetail();

						studentDetail.setFileNo(rs.getString("File_No"));
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
						studentDetail.setCategoryId(rs.getLong("Category_Id"));
						studentDetail.setCourseId(rs.getLong("Course_Id"));
						studentDetail.setBranchId(rs.getLong("Branch_Id"));
						studentDetail.setCreatedBy(rs.getString("Created_By"));
						studentDetail.setCreated_On(rs.getDate("Created_On"));
						studentDetail.setUpdatedBy(rs.getString("Updated_By"));
						studentDetail.setUpdatedOn(rs.getDate("Updated_On"));
						studentDetail.setDomicileState(rs
								.getString("Domicile_State_Id"));
						studentDetail.setScholarship(rs.getBoolean("Scholarship"));
						studentDetail.setRemarks(rs.getString("Remarks"));
						studentDetail.setAdmissionMode(rs.getString("Admission_Mode"));
						studentDetail.setReferredBy(rs.getString("Referred_By"));
						return studentDetail;

					}

				});

		StudentDetail studentDetail = null;

		if (studentDetails != null && studentDetails.size() > 0) {
			studentDetail = studentDetails.get(0);

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
		 
		}
		return studentDetail;
	}

	public void addStudentDtl(StudentDetail studentDtl) {

		String addQuery = admissionQueryProps.getProperty("addStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", studentDtl.getFileNo())
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
						.addValue("Created_On", studentDtl.getCreatedOn())
						.addValue("Updated_By", studentDtl.getUpdatedBy())
						.addValue("Updated_On", studentDtl.getUpdatedOn())
						.addValue("Domicile_State_Id", studentDtl.getDomicileState())
						.addValue("Scholarship", studentDtl.isScholarship())
						.addValue("Remarks", studentDtl.getRemarks())
						.addValue("Admission_Mode", studentDtl.getAdmissionMode())
		                .addValue("Referred_By", studentDtl.getReferredBy()); 

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		if (studentDtl.getAcademicDtl() == null) {
			for (StudentAcademicDetail studentAcademicDetail : studentDtl
					.getAcademicDtl()) {
				addAcademicDtl(studentAcademicDetail);
				continue;
			}
		}

		if (studentDtl.getDiscountDtl() == null) {

			for (AdmissionDiscountDtl admissionDiscountDtl : studentDtl
					.getDiscountDtl()) {

				addAdmissionDisDtl(admissionDiscountDtl);
				continue;
			}
		}

		if (studentDtl.getAddressDtl() == null) {

			for (AddressDetail addressDeatil : studentDtl.getAddressDtl()) {

				addAddressDtl(addressDeatil);
				continue;
			}
		}

		if (studentDtl.getBranchPreference() == null) {

			for (BranchPreference branchPreference : studentDtl
					.getBranchPreference()) {

				addBranchPreference(branchPreference);
				continue;
			}
		}

		if (studentDtl.getCounsellingDtl() == null) {

			for (CounsellingDetail counsellingDetail : studentDtl
					.getCounsellingDtl()) {

				addCounsellingDetail(counsellingDetail);
				continue;
			}
		}

	
	}

	public void updateStudentDtl(StudentDetail studentDtl) {

		String updateQuery = admissionQueryProps
				.getProperty("updateStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", studentDtl.getFileNo())
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
						.addValue("Created_On", studentDtl.getCreatedOn())
						.addValue("Updated_By", studentDtl.getUpdatedBy())
						.addValue("Updated_On", studentDtl.getUpdatedOn())
						.addValue("Domicile_State_Id", studentDtl.getDomicileState())
						.addValue("Scholarship", studentDtl.isScholarship())
						.addValue("Remarks", studentDtl.getRemarks())
						.addValue("Admission_Mode", studentDtl.getAdmissionMode())
						.addValue("Referred_By", studentDtl.getReferredBy());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	public void deleteSudentDtl(String fileNo) {


		deleteAcademicDtl(fileNo);


		deleteAdmissionDisDtl(fileNo);

		deleteAddressDtl(fileNo);

		deleteBranchPreference(fileNo);

		deleteCounsellingDetail(fileNo);
		
		String deleteQuery = admissionQueryProps
				.getProperty("deleteStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	private List<StudentAcademicDetail> getAcademicDtl(String fileNo) {

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
								.getString("File_No"));
						studentAcademicDetail.setQualificationId(rs
								.getLong("Qualification_Id"));
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

						return studentAcademicDetail;


					}
				});

		return studentAcademicDetails;
	}

	private void addAcademicDtl(StudentAcademicDetail academicDtl) {

		String addQuery = admissionQueryProps.getProperty("addAcademicDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", academicDtl.getFileNo())
		.addValue("University", academicDtl.getUniversity())
		.addValue("College_Name", academicDtl.getCollegeName())
		.addValue("Passing_Year", academicDtl.getPassingYear())
		.addValue("Percentage", academicDtl.getPercentage())
		.addValue("Roll_No", academicDtl.getRollNo())
		.addValue("Qualification_Id", academicDtl.getQualificationId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);


		if (academicDtl.getQualificationSubDtl() == null) {

			for (QualificationSubjectDtl qualificationSubjectDtl : academicDtl
					.getQualificationSubDtl()) {

				addQualificationDtl(qualificationSubjectDtl);
				continue;
			}

		}

	}

	private void updateAcademicDtl(StudentAcademicDetail academicDtl) {

		String fileNo = academicDtl.getFileNo();

		deleteAcademicDtl(fileNo);

		addAcademicDtl(academicDtl);

		// String updateQuery = admissionQueryProps
		// .getProperty("updateAcademicDtl");
		//
		// SqlParameterSource namedParameter = new MapSqlParameterSource(
		// "File_No", academicDtl.getFileNo())
		// .addValue("University", academicDtl.getUniversity())
		// .addValue("College_Name", academicDtl.getCollegeName())
		// .addValue("Passing_Year", academicDtl.getPassingYear())
		// .addValue("Percentage", academicDtl.getPercentage())
		// .addValue("Roll_No", academicDtl.getRollNo())
		// .addValue("Qualification_Id", academicDtl.getQualificationId());
		//
		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	private void deleteAcademicDtl(String fileNo) {


		deleteQualificationDtl(fileNo);

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAcademicDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	private List<AddressDetail> getAddressDtl(String fileNo) {

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
						addressDetail.setFileNo(rs.getString("File_No"));
						addressDetail.setAddressType(rs
								.getString("Address_Type"));
						addressDetail.setState(rs.getString("State_Id"));

						return addressDetail;
					}
				});
		return addressDetails;
	}

	private void addAddressDtl(AddressDetail addressDtl) {

		String addQuery = admissionQueryProps.getProperty("addAddressDtl");

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

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	private void updateAddressDtl(AddressDetail addressDtl) {

		String fileNo=addressDtl.getFileNo();

		deleteAddressDtl(fileNo);

		addAddressDtl(addressDtl);

		// String updateQuery = admissionQueryProps
		// .getProperty("updateAddressDtl");
		//
		// SqlParameterSource namedParameter = new MapSqlParameterSource(
		// "File_No", addressDtl.getFileNo())
		// .addValue("House_No", addressDtl.getHouseNo())
		// .addValue("Locality", addressDtl.getLocality())
		// .addValue("Landmark", addressDtl.getLandmark())
		// .addValue("District", addressDtl.getDistrict())
		// .addValue("City", addressDtl.getCity())
		// .addValue("Pincode", addressDtl.getPincode())
		// .addValue("Address_Type", addressDtl.getAddressType())
		// .addValue("State_Id", addressDtl.getState());
		//
		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		//
	}

	private void deleteAddressDtl(String fileNo) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAddressDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	private List<AdmissionDiscountDtl> getAdmissionDisDtl(String fileNo) {

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
						admissionDiscountDtl.setFeeHeadId(rs
								.getLong("FeeHead_Id"));
						admissionDiscountDtl.setFileNo(rs
								.getNString("File_No"));
						admissionDiscountDtl.setPercent(rs
								.getFloat("Percent"));

						return admissionDiscountDtl;
					}
				});
		return admissionDiscountDtls;
	}

	private void addAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {

		String addQuery = admissionQueryProps.getProperty("addAdmissionDisDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", admissionDisDtl.getFileNo())
		.addValue("FeeHead_Id", admissionDisDtl.getFeeHeadId())
		.addValue("Amount", admissionDisDtl.getAmount())
		.addValue("Percent", admissionDisDtl.getPercent());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	private void updateAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {

		String fileNo=admissionDisDtl.getFileNo();

		deleteAdmissionDisDtl(fileNo);

		addAdmissionDisDtl(admissionDisDtl);

		// String updateQuery = admissionQueryProps
		// .getProperty("updateAdmissionDisDtl");
		//
		// SqlParameterSource namedParameter = new MapSqlParameterSource(
		// "File_No", admissionDisDtl.getFileNo())
		// .addValue("FeeHead_Id", admissionDisDtl.getFeeHeadId())
		// .addValue("Amount", admissionDisDtl.getAmount())
		// .addValue("Percent", admissionDisDtl.getPercent());
		//
		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		//
	}

	private void deleteAdmissionDisDtl(String fileNo) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAdmissionDisDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	private List<QualificationSubjectDtl> getQualificationDtl(String fileNo) {

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

						qualificationSubjectDtl.setSubjectId(rs
								.getLong("Subject_Id"));
						qualificationSubjectDtl.setQualificationId(rs
								.getLong("Qualification_Id"));
						qualificationSubjectDtl.setFileNo(rs
								.getString("File_No"));
						qualificationSubjectDtl.setMarksObtained(rs
								.getDouble("Marks_Obtained"));
						qualificationSubjectDtl.setMaxMarks(rs
								.getDouble("Max_Marks"));

						return qualificationSubjectDtl;
					}

				});
		return qualificationSubjectDtls;
	}

	private void addQualificationDtl(QualificationSubjectDtl qualificationDtl) {

		String addQuery = admissionQueryProps
				.getProperty("addQualificationSubjectDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", qualificationDtl.getFileNo())
		.addValue("Subject_Id", qualificationDtl.getSubjectId())
		.addValue("Qualification_Id",
				qualificationDtl.getQualificationId())
				.addValue("Marks_Obtained", qualificationDtl.getMarksObtained())
				.addValue("Max_Marks", qualificationDtl.getMaxMarks());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	private void updateQualificationDtl(QualificationSubjectDtl qualificationDtl) {

		String fileNo=qualificationDtl.getFileNo();

		deleteQualificationDtl(fileNo);

		addQualificationDtl(qualificationDtl);

		// String updateQuery = admissionQueryProps
		// .getProperty("updateQualificationSubjectDtl");
		//
		// SqlParameterSource namedParameter = new MapSqlParameterSource(
		// "File_No", qualificationDtl.getFileNo())
		// .addValue("Subject_Id", qualificationDtl.getSubjectId())
		// .addValue("Qualification_Id",
		// qualificationDtl.getQualificationId())
		// .addValue("Marks_Obtained", qualificationDtl.getMarksObtained())
		// .addValue("Max_Marks", qualificationDtl.getMaxMarks());
		//
		// getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	private void deleteQualificationDtl(String fileNo) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteQualificationSubjectDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	private List<BranchPreference> getBranchPreference(String fileNo) {

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
								.getString("File_No"));

						branchPreference.setBranchId(rs
								.getLong("Branch_Id"));
						branchPreference.setBranchPreferenceId(rs
								.getLong("Branch_Preference_Id"));
						return branchPreference;
					}
				});

		return branchPreferences;
	}

	private void addBranchPreference(BranchPreference branchPreference) {

		String addQuery = admissionQueryProps
				.getProperty("addBranchPreference");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", branchPreference.getFileNo())
		.addValue("Branch_Preference_Id",
				branchPreference.getBranchPreferenceId())
				.addValue("Branch_Id", branchPreference.getBranchId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	private void updateBranchPreference(BranchPreference branchPreference) {

		String fileNo=branchPreference.getFileNo();

		deleteBranchPreference(fileNo);

		addBranchPreference(branchPreference);

	}

	private void deleteBranchPreference(String fileNo) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteBranchPreference");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
	
	private List<CounsellingDetail> getCounsellingDetail(String fileNo) {
		String getQuery = admissionQueryProps.getProperty("getcounsellingDetailByFileNo");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<CounsellingDetail> counsellingDetails = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<CounsellingDetail>() {

					public CounsellingDetail mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						CounsellingDetail counsellingDetail = new CounsellingDetail();

						counsellingDetail.setFileNo(rs.getString("File_No"));
						counsellingDetail.setCounsellingId(rs.getLong("Counselling_Id"));
						counsellingDetail.setRollNo(rs.getString("Roll_No"));
						counsellingDetail.setRank(rs.getLong("Rank"));
						counsellingDetail.setCategoryRank(rs.getLong("Category_Rank"));
						counsellingDetail.setPercentile(rs.getFloat("Percentile"));

						return counsellingDetail;
					}
				});

		return counsellingDetails;
		}

	private void addCounsellingDetail(CounsellingDetail counsellingDetail) {

		String addQuery = admissionQueryProps.getProperty("addCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", counsellingDetail.getFileNo())
		.addValue("Counselling_Id", counsellingDetail.getCounsellingId())
		.addValue("Roll_No", counsellingDetail.getRollNo())
		.addValue("Rank", counsellingDetail.getRank())
		.addValue("Category_Rank", counsellingDetail.getCategoryRank())
		.addValue("Percentile", counsellingDetail.getPercentile());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		
		}


	private void updateCounsellingDetail(CounsellingDetail counsellingDetail) {

		String fileNo=counsellingDetail.getFileNo();

		deleteCounsellingDetail(fileNo);

		addCounsellingDetail(counsellingDetail);

	}

	private void deleteCounsellingDetail(String fileNo) {

		String deleteQuery = admissionQueryProps.getProperty("deleteCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
		
	}


}
