package com.techvisio.einstitution.db.impl;

import java.util.Properties;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.AddressDeatil;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;

public class AdmissionDaoImpl extends BaseDao implements AdmissionDao {

	private Properties admissionQueryProps;

	public void setAdmissionQueryProps(Properties admissionQueryProps) {

		this.admissionQueryProps = admissionQueryProps;

	}

	public void getStudentDtl(StudentDetail studentDtl) {
		// TODO Auto-generated method stub

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
				.addValue("Hostel", studentDtl.getHostel())
				.addValue("Transportation", studentDtl.getTransportation())
				.addValue("Academic_Year", studentDtl.getAcademicYear())
				.addValue("Semester", studentDtl.getSemester())
				.addValue("Management_Approval",
						studentDtl.getManagementApproval())
				.addValue("Fee_Paid", studentDtl.getFeePaid())
				.addValue("Category_Id", studentDtl.getCategoryId())
				.addValue("Course_Id", studentDtl.getCourseId())
				.addValue("Branch_Id", studentDtl.getBranchId())
				.addValue("Created_By", studentDtl.getCreatedBy())
				.addValue("Created_On", studentDtl.getCreatedOn())
				.addValue("Updated_By", studentDtl.getUpdatedBy())
				.addValue("Updated_On", studentDtl.getUpdatedOn())
				.addValue("Fee_Received_By", studentDtl.getFeeReceivedBy())
				.addValue("Fee_Received_On", studentDtl.getFeeReceivedOn())
				.addValue("Document_Received_By",
						studentDtl.getDocumentReceivedBy())
				.addValue("Document_Received_On",
						studentDtl.getDocumentReceivedOn())
				.addValue("Document_verified_By",
						studentDtl.getDocumentVerifiedBy())
				.addValue("Document_Verified_On",
						studentDtl.getDocumentVerifiedOn())
				.addValue("Management_Approved_By",
						studentDtl.getManagementApprovedBy())
				.addValue("Management_Approved_On",
						studentDtl.getManagementApprovedOn())
				.addValue("Domicile_State", studentDtl.getDomicileState());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	
		if (studentDtl.getAcademicDtl() != null) {
			for (StudentAcademicDetail studentAcademicDetail : studentDtl
					.getAcademicDtl()) {
				addAcademicDtl(studentAcademicDetail);
			}
		}

		if (studentDtl.getDiscountDtl() != null) {

			for (AdmissionDiscountDtl admissionDiscountDtl : studentDtl
					.getDiscountDtl()) {

				addAdmissionDisDtl(admissionDiscountDtl);
			}
		}

		if (studentDtl.getAddressDtl() != null) {

			for (AddressDeatil addressDeatil : studentDtl.getAddressDtl()) {

				addAddressDtl(addressDeatil);
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
				.addValue("Hostel", studentDtl.getHostel())
				.addValue("Transportation", studentDtl.getTransportation())
				.addValue("Academic_Year", studentDtl.getAcademicYear())
				.addValue("Semester", studentDtl.getSemester())
				.addValue("Management_Approval",
						studentDtl.getManagementApproval())
				.addValue("Fee_Paid", studentDtl.getFeePaid())
				.addValue("Category_Id", studentDtl.getCategoryId())
				.addValue("Course_Id", studentDtl.getCourseId())
				.addValue("Branch_Id", studentDtl.getBranchId())
				.addValue("Created_By", studentDtl.getCreatedBy())
				.addValue("Created_On", studentDtl.getCreatedOn())
				.addValue("Updated_By", studentDtl.getUpdatedBy())
				.addValue("Updated_On", studentDtl.getUpdatedOn())
				.addValue("Fee_Received_By", studentDtl.getFeeReceivedBy())
				.addValue("Fee_Received_On", studentDtl.getFeeReceivedOn())
				.addValue("Document_Received_By",
						studentDtl.getDocumentReceivedBy())
				.addValue("Document_Received_On",
						studentDtl.getDocumentReceivedOn())
				.addValue("Document_verified_By",
						studentDtl.getDocumentVerifiedBy())
				.addValue("Document_Verified_On",
						studentDtl.getDocumentVerifiedOn())
				.addValue("Management_Approved_By",
						studentDtl.getManagementApprovedBy())
				.addValue("Management_Approved_On",
						studentDtl.getManagementApprovedOn())
				.addValue("Domicile_State", studentDtl.getDomicileState());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	public void deleteSudentDtl(StudentDetail studentDtl) {

		if (studentDtl.getAcademicDtl() != null) {

			for (StudentAcademicDetail academicDetail : studentDtl
					.getAcademicDtl()) {

				deleteAcademicDtl(academicDetail);
			}
		}

		if (studentDtl.getDiscountDtl() != null) {

			for (AdmissionDiscountDtl admissionDiscountDtl : studentDtl
					.getDiscountDtl()) {

				deleteAdmissionDisDtl(admissionDiscountDtl);
			}
		}

		if (studentDtl.getAddressDtl() != null) {

			for (AddressDeatil addressDeatil : studentDtl.getAddressDtl()) {

				deleteAddressDtl(addressDeatil);
			}
		}

		String deleteQuery = admissionQueryProps
				.getProperty("deleteStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", studentDtl.getFileNo());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public void getAcademicDtl(StudentAcademicDetail academicDtl) {
		// TODO Auto-generated method stub

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

		if (academicDtl.getQualificationSubDtl() != null) {

			for (QualificationSubjectDtl qualificationSubjectDtl : academicDtl
					.getQualificationSubDtl()) {

				addQualificationDtl(qualificationSubjectDtl);
			}

		}

	}

	private void updateAcademicDtl(StudentAcademicDetail academicDtl) {

		deleteAcademicDtl(academicDtl);

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

	private void deleteAcademicDtl(StudentAcademicDetail academicDtl) {

		if (academicDtl.getQualificationSubDtl() != null) {

			for (QualificationSubjectDtl qualificationSubjectDtl : academicDtl
					.getQualificationSubDtl()) {

				deleteQualificationDtl(qualificationSubjectDtl);
			}
		}

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAcademicDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", academicDtl.getFileNo());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public void getAddressDtl(AddressDeatil addressDtl) {
		// TODO Auto-generated method stub

	}

	private void addAddressDtl(AddressDeatil addressDtl) {

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

	private void updateAddressDtl(AddressDeatil addressDtl) {

		deleteAddressDtl(addressDtl);

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

	private void deleteAddressDtl(AddressDeatil addressDtl) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAddressDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", addressDtl.getFileNo());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);
	}

	public void getAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {
		// TODO Auto-generated method stub

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

		deleteAdmissionDisDtl(admissionDisDtl);

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

	private void deleteAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteAdmissionDisDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", admissionDisDtl.getFileNo());

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public void getQualificationDtl(QualificationSubjectDtl qualificationDtl) {
		// TODO Auto-generated method stub

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

		deleteQualificationDtl(qualificationDtl);
		
		addQualificationDtl(qualificationDtl);
		
		
//		String updateQuery = admissionQueryProps
//				.getProperty("updateQualificationSubjectDtl");
//
//		SqlParameterSource namedParameter = new MapSqlParameterSource(
//				"File_No", qualificationDtl.getFileNo())
//				.addValue("Subject_Id", qualificationDtl.getSubjectId())
//				.addValue("Qualification_Id",
//						qualificationDtl.getQualificationId())
//				.addValue("Marks_Obtained", qualificationDtl.getMarksObtained())
//				.addValue("Max_Marks", qualificationDtl.getMaxMarks());
//
//		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	private void deleteQualificationDtl(QualificationSubjectDtl qualificationDtl) {

		String deleteQuery = admissionQueryProps
				.getProperty("deleteQualificationSubjectDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", qualificationDtl.getFileNo());
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

}
