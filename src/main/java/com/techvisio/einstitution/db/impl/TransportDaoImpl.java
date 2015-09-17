package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
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

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.db.TransportDao;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class TransportDaoImpl extends BaseDao implements TransportDao {
	private static CustomLogger logger = CustomLogger.getLogger(TransportDaoImpl.class);	
	@Autowired @Qualifier(value="transportQueryProps")
	private Properties transportQueryProps;

	public void setTransportQueryProps(Properties transportQueryProps) {
		this.transportQueryProps = transportQueryProps;
	}

	@Autowired
	CacheManager cacheManager;

	public List<AvailableTransport> getAvailableTransports(){
		logger.info("{} : get available transport",this.getClass().getName());		
		String getQuery = transportQueryProps.getProperty("getAvailableTransport");

		List<AvailableTransport> availableTransports = getNamedParamJdbcTemplate().query(getQuery, new RowMapper<AvailableTransport>() {

			public AvailableTransport mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				AvailableTransport availableTransport = new AvailableTransport();
				availableTransport.setAvailable(rs.getInt("Available_Seat"));
				availableTransport.setReserved(rs.getInt("Reserved_Seat"));
				availableTransport.setCapacity(rs.getString("Capacity"));
				availableTransport.setDescription(rs.getString("Description"));
				return availableTransport;
			}
		});
		return availableTransports;
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		String queryString="FROM TransportReservation tr WHERE tr.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<TransportReservation> result= (List<TransportReservation>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public void saveTransportReservationDtl(TransportReservation transportReservation, Long fileNo) {
			getCurrentSession().merge(transportReservation);
	}

	
	public void deleteTransportReservationDtl(Long fileNo) {
		String queryString="delete TransportReservation where fileNo =" + fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		int result = query.executeUpdate();
	}

	@Override
	public TransportAllocation getTransportAllocation(Long fileNo) {
		String queryString="FROM TransportAllocation ta WHERE ta.active=1 and ta.fileNo = "+fileNo;
		Query query=getCurrentSession().createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<TransportAllocation> result= (List<TransportAllocation>)query.list();
		if(result != null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public Boolean isSeatAvailable(Long vehicleId){
		String getQuery = transportQueryProps.getProperty("getAvailableSeat");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Vehicle_Id",vehicleId);
		
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
	public synchronized void saveTransportAllocationDtl(TransportAllocation transportAllocation, Long fileNo) {
		TransportAllocation currentAllocation = getTransportAllocation(fileNo);

		if(isSeatAvailable(transportAllocation.getVehicleDetail().getVehicleId())){
		if(currentAllocation==null){
			transportAllocation.setActive(true);
			transportAllocation.setAllocatedOn(new Date());
			getCurrentSession().persist(transportAllocation);
		}

		if(currentAllocation != null && currentAllocation.getVehicleDetail().getVehicleId() != transportAllocation.getVehicleDetail().getVehicleId()){

			currentAllocation.setActive(false);
			currentAllocation.setSwitchedOn(new Date());
			getCurrentSession().update(currentAllocation);

			transportAllocation.setTrnsprtAllctnId(null);
			transportAllocation.setActive(true);
			transportAllocation.setAllocatedOn(new Date());
			getCurrentSession().persist(transportAllocation);
		}	
		}

		else {
			throw new RuntimeException("No seat availble in this vehicle");
		}
	}

	@Override
	public void deleteTransportAllocationDtl(Long fileNo) {
		String queryString="delete TransportAllocation where fileNo =" + fileNo;
		Query query = getCurrentSession().createQuery(queryString);
		int result = query.executeUpdate();
	}

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		logger.info(
				"{} : Getting Student detail bby searching criteria for enquiryId:{}",
				this.getClass().getName(), searchCriteria.getInquryId());
		String getQuery = transportQueryProps
				.getProperty("getStudentDtlForTransport");

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
		String getQuery = transportQueryProps.getProperty("getStudentBasicInfoByFileNo");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
		StudentBasicInfo info = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new StudentBasicInfoRowMaper());
		
		return info;
	}
	
	
	@Override
	public List<VehicleDetail> getAvailableVehicles() {
		String queryString="select VD.Vehicle_Id,VD.Vehicle_No,VD.Capacity,VD.Route_Id,VD.Type_Id,VD.Created_By,VD.Created_On,VD.Updated_By,VD.Updated_On FROM vehicle_detail VD left join transport_allocation TA on VD.Vehicle_Id = TA.Vehicle_Id group by VD.Vehicle_Id having VD.Capacity > count(TA.Is_Active)" ;
		SQLQuery query=getCurrentSession().createSQLQuery(queryString);
		query.addEntity(VehicleDetail.class);
		List<VehicleDetail> vehicleDetails = (List<VehicleDetail>)query.list();

		return vehicleDetails;
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
