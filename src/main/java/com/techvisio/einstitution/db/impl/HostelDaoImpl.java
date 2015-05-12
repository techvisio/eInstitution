package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.RoomTypeMaster;
import com.techvisio.einstitution.beans.Session;
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
	//GET DATA FROM HostelInventory TABLE 

	public RoomTypeMaster getHostelInventory(String typeCode) {
		
		logger.info("{} : Get hostel inventory by typecode:{}",this.getClass().getName(), typeCode);
		String getQuery = hostelQueryProps.getProperty("getHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", typeCode);

		RoomTypeMaster hostelInventory = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<RoomTypeMaster>() {

			public RoomTypeMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoomTypeMaster hostel = new RoomTypeMaster();
				hostel.setDescription(rs.getString("Description"));
				hostel.setPrice(rs.getDouble("Price"));
				hostel.setRoomCapacity(rs.getInt("Room_Capacity"));
				hostel.setThreshold(rs.getInt("Threshold"));
				hostel.setTypeCode(rs.getString("Type_Code"));
				return hostel;
			}

		});

		return hostelInventory;

		/*HostelInventory hostelInven=null;

if(hostelInventories != null && hostelInventories.size()>0){

	hostelInven = hostelInventories.get(0);
}

		return hostelInven;

		 */	
	}


	//INSERT DATA IN HostelInventory TABLE


	public void addHostelInventory(RoomTypeMaster hostelInventory) {
		logger.info("{} : Add  hostel inventory for typecode:{}",this.getClass().getName(), hostelInventory.getTypeCode());
		String addQuery = hostelQueryProps.getProperty("addHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", hostelInventory.getTypeCode())
		.addValue("Description", hostelInventory.getDescription())
		.addValue("Threshold", hostelInventory.getThreshold())
		.addValue("Price", hostelInventory.getPrice())
		.addValue("Room_Capacity", hostelInventory.getRoomCapacity());


		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}


	// UPDATE DATA IN HostelInventory TABLE


	public void updateHostelInventory(RoomTypeMaster hostelInventory) {
		logger.info("{} : Update  hostel inventory for typecode:{}",this.getClass().getName(), hostelInventory.getTypeCode());
		String addQuery = hostelQueryProps.getProperty("updateHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", hostelInventory.getTypeCode())
		.addValue("Description", hostelInventory.getDescription())
		.addValue("Threshold", hostelInventory.getThreshold())
		.addValue("Price", hostelInventory.getPrice())
		.addValue("Room_Capacity", hostelInventory.getRoomCapacity());


		getNamedParamJdbcTemplate().update(addQuery, namedParameter);		

	}


	// DELETE DATA FROM HostelInventory TABLE


	public void deleteHostelInventory(String typeCode) {
		logger.info("{} : Delete hostel inventory for typecode:{}",this.getClass().getName(), typeCode);
		String addQuery = hostelQueryProps.getProperty("deleteHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}



	//GET DATA FROM HostelAllocation TABLE



	public RoomAllocationDetail getHostelAllocation(Long fileNo) {
		logger.info("{} : Get hostel allocation for file no:{}",this.getClass().getName(), fileNo);
		String getQuery = hostelQueryProps.getProperty("getHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);

		List<RoomAllocationDetail> hostelAllocations =  getNamedParamJdbcTemplate().query(getQuery, namedParameter, new RowMapper<RoomAllocationDetail>(){

			public RoomAllocationDetail mapRow(ResultSet rs,int rowNum)throws SQLException {
				RoomAllocationDetail hostel = new RoomAllocationDetail();
				hostel.setFileNo(rs.getLong("File_No"));
                hostel.setAllocated(rs.getBoolean("IsAllocated"));
                hostel.setAllocatedBy(rs.getString("Allocated_By"));
                hostel.setAllocatedOn(rs.getDate("Allocated_On"));
                hostel.setUpdatedBy(rs.getString("Updated_By"));
                hostel.setCheckoutOn(rs.getDate("Checkout_On"));
                String roomNo = rs.getString("Room_No");
                RoomTypeDetail typeDetail=cacheManager.getroomDetailByRoomNo(roomNo);
                hostel.setRoomTypeDetail(typeDetail);
				return hostel;

			}

		});


		RoomAllocationDetail hostelAllocation = null;
		if(hostelAllocations != null && hostelAllocations.size()>0 ){
			hostelAllocation = hostelAllocations.get(0);
		}

		return hostelAllocation;
	}

	public void saveRoomDetail(RoomAllocationDetail roomAllocationDetail){
		
		RoomAllocationDetail newRoomAllocation = roomAllocationDetail;
		RoomAllocationDetail oldRoomAllocation = getHostelAllocation(roomAllocationDetail.getFileNo());
		
		if(oldRoomAllocation.isAllocated()){
			
			if(oldRoomAllocation.getRoomTypeDetail().getRoomNo()!=newRoomAllocation.getRoomTypeDetail().getRoomNo()){
				
				deleteHostelAllocation(oldRoomAllocation.getFileNo());
				addHostelAllocation(newRoomAllocation);
			}
		}
		if(!oldRoomAllocation.isAllocated()){
			
			addHostelAllocation(newRoomAllocation);
		}
           	
	
	}
	

	// INSERT DATA IN HostelAllocation TABLE
	public void addHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : Get hostel allocation for file no:{}",this.getClass().getName(), hostelAllocation.getFileNo());
		String addQuery = hostelQueryProps.getProperty("addHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomTypeDetail().getRoomNo())
		.addValue("Allocated_On", hostelAllocation.getAllocatedOn())
		.addValue("Allocated_By", hostelAllocation.getAllocatedBy())
		.addValue("Checkout_On", hostelAllocation.getCheckoutOn())
		.addValue("Updated_By", hostelAllocation.getUpdatedBy())
		.addValue("File_No", hostelAllocation.getFileNo())
		.addValue("IsAllocated", hostelAllocation.isAllocated());

		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
	}


	// UPDATE DATA IN HostelAllocation TABLE
	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : update hostel allocation for file no:{}",this.getClass().getName(), hostelAllocation.getFileNo());
		String updateQuery = hostelQueryProps.getProperty("updateHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomTypeDetail().getRoomNo())
		.addValue("Allocated_On", hostelAllocation.getAllocatedOn())
		.addValue("Allocated_By", hostelAllocation.getAllocatedBy())
		.addValue("Checkout_On", hostelAllocation.getCheckoutOn())
		.addValue("Updated_By", hostelAllocation.getUpdatedBy())
		.addValue("File_No", hostelAllocation.getFileNo())
		.addValue("IsAllocated", hostelAllocation.isAllocated());
		
		getNamedParamJdbcTemplate().update(updateQuery,namedParameter);


	}


	//DELETE DATA FROM HostelAllocation TABLE	
	public void deleteHostelAllocation(Long fileNo) {
		logger.info("{} : delete hostel allocation by file no:{}",this.getClass().getName(), fileNo);
	
		RoomAllocationDetail roomAllocationDetail = new RoomAllocationDetail();
		roomAllocationDetail.setAllocated(false);
		addHostelAllocation(roomAllocationDetail);
     		
			}


	// GET DATA FROM HostelReservation TABLE
	public HostelReservation getHostelReservation(Long fileNo) {
		logger.info("{} : Get hostel reservation for file no:{}",this.getClass().getName(), fileNo);
		String getQuery = hostelQueryProps.getProperty("getHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
		HostelReservation hostelReservation = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter,new RowMapper<HostelReservation>(){

			public HostelReservation mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				HostelReservation hostel = new HostelReservation();
				hostel.setFeePaid(rs.getBoolean("Fee_Paid"));
				hostel.setFileNo(rs.getLong("File_No"));
				hostel.setTypeCode(rs.getString("Type_Code"));
				hostel.setAllocationStatus(rs.getString("Allocation_Status"));
				hostel.setActive(rs.getBoolean("Is_Active"));
				hostel.setPrice(rs.getDouble("Price"));
				hostel.setDescription(rs.getString("Description"));

				return hostel;
			}

		});
		/*HostelReservation hostelRes = null;

if(hostelReservations != null && hostelReservations.size()>0){
	hostelRes = hostelReservations.get(0);
}*/

		return hostelReservation;

	}


	
	
// INSERT DATA IN HostelReservation TABLE
	public void addHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : add hostel reservation for file no:{}",this.getClass().getName(), hostelReservation.getFileNo());
		String addQuery = hostelQueryProps.getProperty("addHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", hostelReservation.getFileNo())
		.addValue("Fee_Paid", hostelReservation.isFeePaid())
		.addValue("Type_Code", hostelReservation.getTypeCode())
		.addValue("Allocation_Status",hostelReservation.getAllocationStatus())
		.addValue("Is_Active",hostelReservation.isActive());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}


	// UPDATE DATA IN HostelReservation TABLE	
	public void updateHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : update hostel reservation for file no:{}",this.getClass().getName(), hostelReservation.getFileNo());		
		String addQuery = hostelQueryProps.getProperty("updateHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", hostelReservation.getFileNo())
		.addValue("Fee_Paid", hostelReservation.isFeePaid())
		.addValue("Type_Code", hostelReservation.getTypeCode())
		.addValue("Allocation_Status",hostelReservation.getAllocationStatus())
		.addValue("Is_Active",hostelReservation.isActive());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);


	}


	// DELETE DATA FROM HostelReservation TABLE	
	public void deleteHostelReservation(Long fileNo) {
		logger.info("{} : delete hostel reservation for file no:{}",this.getClass().getName(), fileNo);
		String addQuery = hostelQueryProps.getProperty("deleteHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo);

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}


	// GET DATA FROM RoomTypeDetail TABLE
	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		logger.info("{} : get room type detail for type code:{}",this.getClass().getName(), typeCode);
		String getQuery = hostelQueryProps.getProperty("getRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", typeCode);
		RoomTypeDetail roomTypeDetail = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter,new RowMapper<RoomTypeDetail>(){

			public RoomTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoomTypeDetail room = new RoomTypeDetail();
				room.setRoomNo(rs.getString("Room_No"));
				room.setTypeCode(rs.getString("Type_Code"));
				room.setWingId(CommonUtil.getLongValue(rs.getLong("Wing_Id")));
				room.setFloorId(CommonUtil.getLongValue(rs.getLong("Floor_Id")));
				room.setBlockId(CommonUtil.getLongValue(rs.getLong("Block_Id")));
				
				return room;
			}

		});
		/*
	RoomTypeDetail r = null;
	if(roomTypeDetails != null && roomTypeDetails.size()>0){
		r = roomTypeDetails.get(0);
	}
		 */
		return roomTypeDetail;
	}



	// INSERT DATA IN RoomTypeDetail TABLE
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : add room type detail for type code:{}",this.getClass().getName(), roomTypeDetail.getTypeCode());
		String addQuery = hostelQueryProps.getProperty("addRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
		.addValue("Type_Code", roomTypeDetail.getTypeCode())
		.addValue("Wing_Id", roomTypeDetail.getWingId())
		.addValue("Floor_Id", roomTypeDetail.getFloorId())
		.addValue("Block_Id", roomTypeDetail.getBlockId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}


	//UPDATE DATA IN RoomTypeDetail TABLE
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : update room type detail for type code:{}",this.getClass().getName(), roomTypeDetail.getTypeCode());		
		String addQuery = hostelQueryProps.getProperty("updateRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
		.addValue("Type_Code", roomTypeDetail.getTypeCode())
		.addValue("Wing_Id", roomTypeDetail.getWingId())
		.addValue("Floor_Id", roomTypeDetail.getFloorId())
		.addValue("Block_Id", roomTypeDetail.getBlockId());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	//DELETE DATA IN RoomTypeDetail TABLE	
	public void deleteRoomTypeDetail(String typeCode) {
		logger.info("{} : delete room type detail for type code:{}",this.getClass().getName(), typeCode);	
		String addQuery = hostelQueryProps.getProperty("deleteRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}




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


	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo){
	logger.info("{} : get current allocation",this.getClass().getName());
	
		String getQuery = hostelQueryProps.getProperty("getCurrentAllocation");

		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomNo);
		
		 RoomAllocationDetailForRoom currentAllocation = new RoomAllocationDetailForRoom();
         List<StudentBasicInfo> basicInfos = new ArrayList<StudentBasicInfo>();
		 
		 List<Map<String, Object>> roomAllocationMaps = getNamedParamJdbcTemplate().queryForList(getQuery, namedParameter );
	    		
	    		
	    for (Map<String, Object> allocationMap : roomAllocationMaps) {
	         RoomAllocationDetailForRoom roomAllocation = new RoomAllocationDetailForRoom();
	        
	         roomAllocation.setCapacity((Integer) (allocationMap.get("Room_Capacity")));
	         roomAllocation.setRoomNo((String) (allocationMap.get("Room_No")));
	        
	         for(StudentBasicInfo basicInfo : basicInfos){

	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Registration_No")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("File_No")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("First_Name")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Last_Name")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Father_name")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Gender")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("DOB")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Enroll_No")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Course_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Branch_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Semester")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Academic_Year")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Batch_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Section_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Shift_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Centre_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Session_Id")));
	        	 basicInfo.setRegistrationNo((String)(allocationMap.get("Lateral")));
	         
	             basicInfos.add(basicInfo);
	         }
	         
	         currentAllocation.setBasicInfos(basicInfos);
	    }	
				
		return currentAllocation;

	}
	
	
}
