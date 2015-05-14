package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AddressDetail;
import com.techvisio.einstitution.beans.AdmissionDiscountDtl;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.CounsellingDetail;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.AdmissionDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class AdmissionDaoImpl extends BaseDao implements AdmissionDao {
	private static CustomLogger logger = CustomLogger.getLogger(AdmissionDaoImpl.class);
	
	@Autowired
	CacheManager cacheManager;

	@Autowired @Qualifier(value="admissionQueryProps")
	private Properties admissionQueryProps;
	
	public void setAdmissionQueryProps(Properties admissionQueryProps) {

		this.admissionQueryProps = admissionQueryProps;

	}

	
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
	
	public StudentDetail getStudentDtl(Long fileNo) {
		  logger.info("{} : Getting Student record in AdmissionDaoImpl: file no : {}",this.getClass().getName(), fileNo);
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

			Remark remark = getRemarkByFileNo(fileNo);
			studentDetail.setRemark(remark);

			return studentDetail;
	}

	public void addStudentDtl(StudentDetail studentDtl) {
		logger.info("{} : New Student record being created: Student Name : {}",this.getClass().getName(),studentDtl.getFirstName()+studentDtl.getLastName());
		String addQuery = admissionQueryProps.getProperty("addStudentDtl");

		SqlParameterSource namedParameter = getParameterMap(studentDtl);

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

		if(studentDtl.getRemark()!=null){
			
			saveRemark(studentDtl.getRemark());
		}

	}
	
	
	private MapSqlParameterSource getParameterMap(
			StudentDetail studentDtl) {
		logger.info("{} : Set value in particular field through MapSqlParameterSource in AdmissionDaoImpl: Student : {}",this.getClass().getName(), studentDtl.getFirstName()+studentDtl.getLastName());	 
		return new MapSqlParameterSource(
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
						.addValue("Application_Status",studentDtl.getApplicationStatus())
						.addValue("Shift_Id", studentDtl.getShiftId())
						.addValue("Centre_Id", studentDtl.getCentreId())
						.addValue("Batch_Id", studentDtl.getBatchId())
						.addValue("Session_Id", studentDtl.getSessionId())
						.addValue("Section_Id", studentDtl.getSectionId());
	}


	public void updateStudentDtl(StudentDetail studentDtl) {
		logger.info("{} : update student record in AdmissionDaoImpl: Student : {}",this.getClass().getName(), studentDtl.getFirstName()+studentDtl.getLastName());
		String updateQuery = admissionQueryProps
				.getProperty("updateStudentDtl");

		SqlParameterSource namedParameter =  getParameterMap(studentDtl);
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

		if(studentDtl.getRemark()!=null){
			
			saveRemark(studentDtl.getRemark());
		}

	}

	public void deleteSudentDtl(Long fileNo) {
		logger.info("{} : delete student record for a particular file no in AdmissionDaoImpl : File no : {}",this.getClass().getName(), fileNo);
        StudentDetail detail = new StudentDetail();
		
		deleteAcademicDtl(fileNo,detail.getAcademicDtl());

		deleteAdmissionDisDtl(fileNo, detail.getDiscountDtl());

		deleteAddressDtl(fileNo,detail.getAddressDtl());

		deleteBranchPreference(fileNo, detail.getBranchPreference());

		deleteCounsellingDetail(fileNo, detail.getCounsellingDtl());

		String deleteQuery = admissionQueryProps
				.getProperty("deleteStudentDtl");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	private List<StudentAcademicDetail> getAcademicDtl(final Long fileNo) {
		logger.info("{} : Get academic detail for a particular file no in AdmissionDaoImpl : File no : {}",this.getClass().getName(), fileNo);
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
		logger.info("{} :we are dealing with saveAcademicDtl by passing  AcademicDetails: {}",this.getClass().getName(), academicDetails);
		if(academicDetails != null){
			for(StudentAcademicDetail academicDetail:academicDetails){
			Long fileNo=academicDetail.getFileNo();
			deleteAcademicDtl(fileNo,academicDetails);
				saveAcademicDtl(academicDetail);
			}
			}

	}
	
	private void saveAcademicDtl(StudentAcademicDetail academicDtl) {
		logger.info("{} :we are dealing with saveAcademicDtl method for : File no : {}",this.getClass().getName(), academicDtl.getFileNo());
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

	private void deleteAcademicDtl(Long fileNo,List<StudentAcademicDetail> academicDetails) {
		logger.info("{} :we are dealing with deleteAcademicDtl method for : File no : {}",this.getClass().getName(), fileNo);		
		StudentAcademicDetail detail = new StudentAcademicDetail();
	
		List<Long> qualificationIds=new ArrayList<Long>();
		
		if(academicDetails==null || academicDetails.size()==0){
			qualificationIds.add(-1L);
		}
		
		else{
		
		if(academicDetails != null){
			for(StudentAcademicDetail academicDetail:academicDetails){
				qualificationIds.add(academicDetail.getQualificationId());
				fileNo=academicDetail.getFileNo();
				deleteQualificationDtl(fileNo, detail.getQualificationSubDtl());
			}
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
		logger.info("{} :Getting adress for an particular : file no : {}",this.getClass().getName(), fileNo);
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
						addressDetail.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
						addressDetail.setAddressType(rs
								.getString("Address_Type"));
						addressDetail.setState(rs.getString("State_Id"));

						return addressDetail;
					}
				});
		return addressDetails;
	}
	
	private void saveAddressDetails(List<AddressDetail> addresses){
		logger.info("{} :saveAddressDtl by passing address",this.getClass().getName());	
		if(addresses != null){
			for(AddressDetail address:addresses){
			Long fileNo=address.getFileNo();
				deleteAddressDtl(fileNo, addresses);
		
			saveAddressDtl(address);
		}
		}
		
	}

	private void saveAddressDtl(AddressDetail addressDtl) {
		logger.info("{} :Saving adress for an particular : file no : {}",this.getClass().getName(), addressDtl.getFileNo());
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

	private void deleteAddressDtl(Long fileNo, List<AddressDetail> addresses) {
		logger.info("{} : Dealing with deleteAddressDtl : file no : {}",this.getClass().getName(), fileNo);		
		List<String> addressTypes=new ArrayList<String>();

		if(addresses==null || addresses.size()==0){
			addressTypes.add(" ");
		}
		
		else{

		if(addresses != null){
			for(AddressDetail address:addresses){
				addressTypes.add(address.getAddressType());
				fileNo=address.getFileNo();
			}
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
		logger.info("{} : Getting discount detail for : file no : {}",this.getClass().getName(), fileNo);
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
		logger.info("{} : calling saveAdmissionDisDtl by passing admissionDiscountDtl",this.getClass().getName());	

		if(admissionDiscountDtls != null){
			for(AdmissionDiscountDtl admissionDiscountDtl:admissionDiscountDtls){
				
				Long fileNo = admissionDiscountDtl.getFileNo();
			deleteAdmissionDisDtl(fileNo, admissionDiscountDtls);
			
				saveAdmissionDisDtl(admissionDiscountDtl);
			}
			}
	
	
	}
	private void saveAdmissionDisDtl(AdmissionDiscountDtl admissionDisDtl) {
		logger.info("{} : Saving discount detail for : file no : {}",this.getClass().getName(), admissionDisDtl.getFileNo());
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

	private void deleteAdmissionDisDtl(Long fileNo, List<AdmissionDiscountDtl> admissionDiscountDtls) {
		logger.info("{} : deleting discount detail for : file no : {}",this.getClass().getName(), fileNo);
		List<Long> feeHeadIds=new ArrayList<Long>();
		if(admissionDiscountDtls==null || admissionDiscountDtls.size()==0){
			feeHeadIds.add(-1L);
		}
		
		else{
		if(admissionDiscountDtls != null){
			for(AdmissionDiscountDtl admissionDiscountDtl:admissionDiscountDtls){
				feeHeadIds.add(admissionDiscountDtl.getFeeHeadId());
				fileNo=admissionDiscountDtl.getFileNo();
			}
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
		logger.info("{} : Getting qualification detail for : file no : {}",this.getClass().getName(), fileNo);
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
		logger.info("{} : Calling saveQualificationDtl by passing qualificationSubjectDtl",this.getClass().getName());
		if(qualificationSubjectDtls != null){
			for(QualificationSubjectDtl qualificationSubjectDtl:qualificationSubjectDtls){
				
				Long fileNo=qualificationSubjectDtl.getFileNo();
			deleteQualificationDtl(fileNo, qualificationSubjectDtls);
			
				
				saveQualificationDtl(qualificationSubjectDtl);
			}
			}
	}
	
	private void saveQualificationDtl(QualificationSubjectDtl qualificationDtl) {
		logger.info("{} : Saving qualification detail for : file no : {}",this.getClass().getName(), qualificationDtl.getFileNo());
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
	private void deleteQualificationDtl(Long fileNo, List<QualificationSubjectDtl> qualificationSubjectDtls) {
		logger.info("{} : Deleting qualification detail for : file no : {}",this.getClass().getName(), fileNo);
		List<Long> subjectIds=new ArrayList<Long>();

		if(qualificationSubjectDtls==null || qualificationSubjectDtls.size()==0){
			subjectIds.add(-1L);
		}
		
		else{
		if(qualificationSubjectDtls != null){
			for(QualificationSubjectDtl qualificationSubjectDtl:qualificationSubjectDtls){
				subjectIds.add(qualificationSubjectDtl.getSubjectId());
				fileNo=qualificationSubjectDtl.getFileNo();
			}
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
		logger.info("{} : Getting Branch preference for : file no : {}",this.getClass().getName(), fileNo);
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

						branchPreference.setFileNo(CommonUtil.getLongValue(rs
								.getLong("File_No")));

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
		logger.info("{} : calling saveBranchPreference by passing branchPreference",this.getClass().getName());		
		if(branchPreferences != null){
			for(BranchPreference branchPreference:branchPreferences){
				Long fileNo=branchPreference.getFileNo();
			deleteBranchPreference(fileNo, branchPreferences);
			
				saveBranchPreference(branchPreference);
			}
			}
	
	}
	private void saveBranchPreference(BranchPreference branchPreference) {
		logger.info("{} : Saving branch preference for : file no : {}",this.getClass().getName(), branchPreference.getFileNo());
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

	private void deleteBranchPreference(Long fileNo, List<BranchPreference> branchPreferences) {
		logger.info("{} : deleting branch prefrence for : file no : {}",this.getClass().getName(), fileNo);
		List<Long> branchPreferenceIds=new ArrayList<Long>();
		

		if(branchPreferences==null || branchPreferences.size()==0){
			branchPreferenceIds.add(-1L);
		}
		
		else{
		if(branchPreferences != null){
			for(BranchPreference branchPreference:branchPreferences){
				branchPreferenceIds.add(branchPreference.getBranchPreferenceId());
				fileNo=branchPreference.getFileNo();
			}
		}
				
				
		
		String deleteQuery = admissionQueryProps
				.getProperty("deleteBranchPreference");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo).addValue("Branch_Preference_Id", branchPreferenceIds);
		
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
	}
	private List<CounsellingDetail> getCounsellingDetail(Long fileNo) {
		logger.info("{} : Getting counselling detail for : file no : {}",this.getClass().getName(), fileNo);
		String getQuery = admissionQueryProps.getProperty("getcounsellingDetailByFileNo");


		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo);

		List<CounsellingDetail> counsellingDetails = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<CounsellingDetail>() {

					public CounsellingDetail mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						CounsellingDetail counsellingDetail = new CounsellingDetail();

						counsellingDetail.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
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
		logger.info("{} :calling saveCounsellingDetail by passing counsellingDetail ",this.getClass().getName());		
		if(counsellingDetails != null){
			for(CounsellingDetail counsellingDetail:counsellingDetails){
			
				Long fileNo=counsellingDetail.getFileNo();
			    deleteCounsellingDetail(fileNo, counsellingDetails);
			
				saveCounsellingDetail(counsellingDetail);
			}
			}
		
	}
	private void saveCounsellingDetail(CounsellingDetail counsellingDetail) {
		logger.info("{} : Saving qualification detail for : file no : {}",this.getClass().getName(), counsellingDetail.getFileNo());
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
	private void deleteCounsellingDetail(Long fileNo, List<CounsellingDetail> counsellingDetails) {
		logger.info("{} : deleting counselling detail for : file no : {}",this.getClass().getName(), fileNo);
		List<Long> counsellingIds=new ArrayList<Long>();

		if(counsellingDetails==null || counsellingDetails.size()==0){
			counsellingIds.add(-1L);
		}
		
		else{
			
		if(counsellingDetails != null){
			for(CounsellingDetail counsellingDetail:counsellingDetails){
				counsellingIds.add(counsellingDetail.getCounsellingId());
				fileNo=counsellingDetail.getFileNo();
			}
		}
         
		
		String deleteQuery = admissionQueryProps.getProperty("deleteCounsellingDetail");

		SqlParameterSource namedParameter =  new MapSqlParameterSource("File_No", fileNo)
		.addValue("Counselling_Id", counsellingIds);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}
}

	@Override
	public Remark getRemarkByFileNo(Long fileNo) {
		logger.info("{} : Getting remark by : file no : {}",this.getClass().getName(), fileNo);	
		Remark remark = new Remark();
		remark.setFileNo(fileNo);
		String getQuery = admissionQueryProps.getProperty("getRemarkByFileNo");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
		
		List<Remark> remarks = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<Remark>(){

			@Override
			public Remark mapRow(ResultSet rs, int rowNum) throws SQLException {
				Remark r = new Remark();
				r.setEnquiryRemark(rs.getString("Enquiry_Remark"));
				r.setEnquiryRemarkDate(rs.getDate("Enquiry_Remark_Date"));
				r.setFeeRemark(rs.getString("Fee_Remark"));
				r.setFeeRemarkDate(rs.getDate("Fee_Remark_Date"));
				r.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
				r.setManagementRemark(rs.getString("Management_Remark"));
				r.setManagementRemarkDate(rs.getDate("Management_Remark_Date"));
				
				return r;
			}
			
		});
		
		if(remarks!=null && remarks.size()>0){
			
			return remarks.get(0);
		}
		return remark;
	}

	@Override
	public void saveRemark(Remark remark) {
		logger.info("{} : Saving remark for : file no : {}",this.getClass().getName(), remark.getFileNo());
		String saveQuery = admissionQueryProps.getProperty("upsertRemark");
		if(remark.getFileNo() != null){
		SqlParameterSource namedParameter = new MapSqlParameterSource("Enquiry_Remark", remark.getEnquiryRemark())
											.addValue("Enquiry_Remark_Date", remark.getEnquiryRemarkDate())
											.addValue("Fee_Remark", remark.getFeeRemark())
											.addValue("Fee_Remark_Date", remark.getFeeRemarkDate())
											.addValue("Management_Remark", remark.getManagementRemark())
											.addValue("Management_Remark_Date", remark.getManagementRemarkDate())
											.addValue("File_No", remark.getFileNo());
		
		getNamedParamJdbcTemplate().update(saveQuery, namedParameter );
	}
			
	}

public class StudentDetailRowMapper implements RowMapper<StudentDetail>{
	public StudentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		logger.info("{} : putting value in all setters of studentdetail bean  : {}",this.getClass().getName());
		StudentDetail studentDetail = new StudentDetail();

		studentDetail.setRegistrationNo(rs.getString("Registration_No"));
		studentDetail.setFileNo(CommonUtil.getLongValue(rs.getLong("File_No")));
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
		studentDetail.setShiftId(CommonUtil.getLongValue(rs.getLong("Shift_Id")));
		studentDetail.setCentreId(CommonUtil.getLongValue(rs.getLong("Centre_Id")));
		studentDetail.setBatchId(CommonUtil.getLongValue(rs.getLong("Batch_Id")));
		studentDetail.setSessionId(CommonUtil.getLongValue(rs.getLong("Session_Id")));
        studentDetail.setSectionId(CommonUtil.getLongValue(rs.getLong("Section_Id")));
		return studentDetail;
	}
	
}
	public List<StudentBasicInfo> getLatestAdmissionInfo(int limit) {
		logger.info("{} : Getting latest admission information by giving : limit : {}",this.getClass().getName(), limit);		
		String getQuery = admissionQueryProps.getProperty("getAdmissionBasicInfo");
		SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
		
		List<StudentBasicInfo> infos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new StudentBasicInfoRowMaper());
		return infos;
	}

public StudentBasicInfo getStudentBsInfo(Long fileNo) {
	logger.info("{} : Getting basic information of student, having  : file no : {}",this.getClass().getName(), fileNo);
	String getQuery = admissionQueryProps.getProperty("getStudentBasicInfoByFileNo");
	SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
	StudentBasicInfo info = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new StudentBasicInfoRowMaper());
	
	return info;
}


public List<StudentBasicInfo> getUnapprovedAdmissions(int limit) {
	logger.info("{} : Getting unapproved admissions by giving : limit : {}",this.getClass().getName(), limit);	
	String getQuery = admissionQueryProps.getProperty("getUnapprovedAdmissions");
	SqlParameterSource namedParameter = new MapSqlParameterSource("limit",limit);
	
	List<StudentBasicInfo> infos = getNamedParamJdbcTemplate().query(getQuery,namedParameter ,new StudentBasicInfoRowMaper());
	return infos;
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
		
		basicInfo.setLateral(rs.getBoolean("Lateral"));
		Remark remark = getRemarkByFileNo(basicInfo.getFileNo());
		basicInfo.setRemark(remark);
		
		return basicInfo;
	}
}
}
