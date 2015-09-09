package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.TransportReservation;
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
		String queryString="FROM HostelReservation tr WHERE tr.fileNo = "+fileNo;
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
				allocationDetail.setAllocated(rs.getBoolean("isAllocated"));
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
}
