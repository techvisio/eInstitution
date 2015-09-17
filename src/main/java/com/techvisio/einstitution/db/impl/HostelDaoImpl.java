package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.Role;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class HostelDaoImpl extends BaseDao implements HostelDao {
	private static CustomLogger logger = CustomLogger.getLogger(HostelDaoImpl.class);
	@Autowired @Qualifier(value="hostelQueryProps")
	private Properties hostelQueryProps;

	public void setHostelQueryProps(Properties hostelQueryProps) {
		this.hostelQueryProps = hostelQueryProps;
	}

	@Autowired
	CacheManager cacheManager ; 

	@Override
	public List<HostelAvailability> getHostelAvailability() {
		logger.info("{} : get hostel availability",this.getClass().getName());
		String getQuery = hostelQueryProps.getProperty("getHostelAvailability");

		List<HostelAvailability> hostelAvailabilities = getNamedParamJdbcTemplate().query(getQuery, new RowMapper<HostelAvailability>() {

			public HostelAvailability mapRow(ResultSet rs, int rowNum)throws SQLException {
				HostelAvailability hostelAvailability = new HostelAvailability();
				hostelAvailability.setTypeCode(rs.getString("TYPE_CODE"));
				hostelAvailability.setDescription(rs.getString("DESCRIPTION"));
				hostelAvailability.setThreshold(rs.getInt("THRESHOLD"));
				hostelAvailability.setReservedRoom(rs.getInt("RESERVED_ROOM"));
				hostelAvailability.setPrice(rs.getDouble("PRICE"));
				hostelAvailability.setAvailable(rs.getInt("AVAILABLE"));

				return hostelAvailability;
			}
		});

		return hostelAvailabilities;
	}

	@Override
	public HostelReservation getHostelReservation(Long fileNo) {
		String queryString="FROM HostelReservation hr WHERE hr.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<HostelReservation> result= (List<HostelReservation>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void saveHostelReservation(HostelReservation hostelReservation, Long fileNo) {
		getCurrentSession().merge(hostelReservation);
	}

	@Override
	public void deleteHostelReservation(Long fileNo) {
		String queryString="delete HostelReservation where fileNo =" + fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		int result = query.executeUpdate();
	}


	@Override
	public RoomAllocation getActiveRoomAllocation(Long fileNo) {
		String queryString="FROM RoomAllocation ra WHERE ra.active=1 and ra.fileNo= "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<RoomAllocation> result= (List<RoomAllocation>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public Boolean isRoomAvailable(Long roomId){
		String getQuery = hostelQueryProps.getProperty("getRoomAvailability");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_Id",roomId);
		
		Boolean isAvailable = false;
		
		isAvailable=getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<Boolean>(){

			@Override
			public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getBoolean("Result");
			}
		});	
		return isAvailable;
	}
	
	@Override
	public synchronized void saveRoomAllocation(RoomAllocation roomAllocation, Long fileNo) {

		if(isRoomAvailable(roomAllocation.getRoomTypeDetail().getRoomId())){
		RoomAllocation currentAllocation = getActiveRoomAllocation(fileNo);

		if(currentAllocation==null){
			roomAllocation.setActive(true);
			roomAllocation.setAllocatedOn(new Date());
			getCurrentSession().persist(roomAllocation);
		}

		if(currentAllocation != null && currentAllocation.getRoomTypeDetail().getRoomId() != roomAllocation.getRoomTypeDetail().getRoomId()){

			currentAllocation.setActive(false);
			currentAllocation.setCheckoutOn(new Date());
			getCurrentSession().update(currentAllocation);

			roomAllocation.setRoomAllocationId(null);
			roomAllocation.setActive(true);
			roomAllocation.setAllocatedOn(new Date());
			getCurrentSession().persist(roomAllocation);
		}	
		}
		
		else{
			throw new RuntimeException("No Bed Available in this room");
		}
	}

	@Override
	public void deleteRoomAllocation(Long fileNo) {
		String queryString="delete RoomAllocation where fileNo =" + fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		int result = query.executeUpdate();
	}

	@Override
	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo){
		logger.info("{} : get current allocation by room no :{}",this.getClass().getName(), roomNo);


		String getQuery = hostelQueryProps.getProperty("getCurrentAllocation");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomNo);

		RoomAllocationDetailForRoom currentAllocation = new RoomAllocationDetailForRoom();
		List<StudentBasicInfo> basicInfos = new ArrayList<StudentBasicInfo>();
		currentAllocation.setBasicInfos(basicInfos);
		List<Map<String, Object>> roomAllocationMaps = getNamedParamJdbcTemplate().queryForList(getQuery, namedParameter);

		for (Map<String, Object> allocationMap : roomAllocationMaps) {

			currentAllocation.setCapacity((Integer) (allocationMap.get("Room_Capacity")));
			currentAllocation.setRoomNo((String) (allocationMap.get("Room_No")));

			StudentBasicInfo basicInfo= new StudentBasicInfo();
			basicInfo.setRegistrationNo((String)(allocationMap.get("Registration_No")));
			basicInfo.setFileNo((CommonUtil.getLongToObject(allocationMap.get("File_No"))));
			basicInfo.setFirstName((String)(allocationMap.get("First_Name")));
			basicInfo.setLastName((String)(allocationMap.get("Last_Name")));
			basicInfo.setFatherName((String)(allocationMap.get("Father_name")));
			basicInfo.setGender((String)(allocationMap.get("Gender")));
			basicInfo.setDob((Date)(allocationMap.get("DOB")));
			basicInfo.setEnrollmentNo((String)(allocationMap.get("Enroll_No")));
			Long courseId=((CommonUtil.getLongToObject(allocationMap.get("Course_Id"))));
			Course course = cacheManager.getCourseById(courseId);
			basicInfo.setCourse(course);
			Long branchId=((CommonUtil.getLongToObject(allocationMap.get("Branch_Id"))));
			Branch branch = cacheManager.getBranchById(branchId);
			basicInfo.setBranch(branch);
			basicInfo.setSemester((String)(allocationMap.get("Semester")));
			basicInfo.setAcademicYear((String)(allocationMap.get("Academic_Year")));
			Long batchId=((CommonUtil.getLongToObject(allocationMap.get("Batch_Id"))));
			Batch batch = cacheManager.getBatchByBatchId(batchId);
			basicInfo.setBatch(batch);			
			Long sectionId=((CommonUtil.getLongToObject(allocationMap.get("Section_Id"))));
			Section section = cacheManager.getSectionBySectionId(sectionId);
			basicInfo.setSection(section);
			Long shiftId=((CommonUtil.getLongToObject(allocationMap.get("Shift_Id"))));
			Shift shift = cacheManager.getShiftByShiftId(shiftId);
			basicInfo.setShift(shift);
			Long centreId=((CommonUtil.getLongToObject(allocationMap.get("Centre_Id"))));
			Centre centre = cacheManager.getCentreByCentreId(centreId);
			basicInfo.setCentre(centre);
			basicInfo.setRegistrationNo((String)(allocationMap.get("Registration_No")));
			Long sessionId=((CommonUtil.getLongToObject(allocationMap.get("Session_Id"))));
			Session session = cacheManager.getSessionBySessionId(sessionId);
			basicInfo.setSession(session);
			basicInfo.setLateral((Boolean)(allocationMap.get("Lateral")));

			basicInfos.add(basicInfo);

		}	

		return currentAllocation;

	}

	@Override
	public RoomAllocation getActiveRoomAllocationDtl(Long fileNo) {
		logger.info("{} : Get room allocation detail for file no:{}",this.getClass().getName(), fileNo);
		String getQuery = hostelQueryProps.getProperty("getRoomAllocatedDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("file_no",fileNo);
		List<RoomAllocation> roomAllocationDetails = getNamedParamJdbcTemplate().query(getQuery, namedParameter,  new RowMapper<RoomAllocation>(){

			@Override
			public RoomAllocation mapRow(ResultSet rs, int arg1)
					throws SQLException {
				RoomAllocation allocationDetail = new RoomAllocation();
				allocationDetail.setAllocatedBy(rs.getString("Allocated_By"));
				allocationDetail.setAllocatedOn(rs.getDate("Allocated_on"));
				allocationDetail.setCheckoutOn(rs.getDate("Checkout_on"));
				allocationDetail.setFileNo(CommonUtil.getLongValue(rs.getLong("file_no")));
				allocationDetail.setRemark(rs.getString("Remark"));
				String roomNo = rs.getString("Room_No");
				RoomTypeDetail typeDetail=cacheManager.getroomDetailByRoomNo(roomNo);
				allocationDetail.setRoomTypeDetail(typeDetail);
				allocationDetail.setUpdatedBy(rs.getString("updated_by"));
				return allocationDetail;
			}

		});
		RoomAllocation roomAllocationDetail = null;
		if(roomAllocationDetails != null && roomAllocationDetails.size()>0 ){
			roomAllocationDetail = roomAllocationDetails.get(0);
		}

		return roomAllocationDetail;
	}


	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		logger.info(
				"{} : Getting Student detail bby searching criteria for enquiryId:{}",
				this.getClass().getName(), searchCriteria.getInquryId());
		String getQuery = hostelQueryProps
				.getProperty("getStudentDtlForHostel");

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

	@Override
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		logger.info("{} : Getting basic information of student, having  : file no : {}",this.getClass().getName(), fileNo);
		String getQuery = hostelQueryProps.getProperty("getStudentBasicInfoByFileNo");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
		StudentBasicInfo info = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new StudentBasicInfoRowMaper());

		return info;
	}

	@Override
	public List<RoomTypeDetail> getAvailableRooms() {
		String queryString="select RD.Room_Id,RD.Room_No,RD.Room_Capacity,RD.Created_By,RD.Created_On,RD.Updated_By,RD.Updated_On,RD.Block_Id,RD.Floor_Id,RD.Room_Type_Id,RD.Wing_Id from room_type_detail RD left join room_allocation_detail RAD on RD.Room_Id = RAD.Room_Id group by RD.Room_Id having RD.Room_Capacity > count(RAD.Is_Active)" ;
		SQLQuery query=getCurrentSession().createSQLQuery(queryString);
		query.addEntity(RoomTypeDetail.class);
		List<RoomTypeDetail> roomTypeDetails = (List<RoomTypeDetail>)query.list();

		return roomTypeDetails;
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

}
