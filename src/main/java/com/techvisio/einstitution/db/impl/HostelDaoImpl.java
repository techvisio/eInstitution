package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelInventory;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;

public class HostelDaoImpl extends BaseDao implements HostelDao {
	
	private Properties hostelQueryProps;
	
	

	
	public void setHostelQueryProps(Properties hostelQueryProps) {
		this.hostelQueryProps = hostelQueryProps;
	}
	

//GET DATA FROM HostelInventory TABLE 
	
	public HostelInventory getHostelInventory(String typeCode) {
		String getQuery = hostelQueryProps.getProperty("getHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", typeCode);
	
HostelInventory hostelInventory = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<HostelInventory>() {

	public HostelInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
		HostelInventory hostel = new HostelInventory();
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

	
	public void addHostelInventory(HostelInventory hostelInventory) {
	
		String addQuery = hostelQueryProps.getProperty("addHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", hostelInventory.getTypeCode())
											.addValue("Description", hostelInventory.getDescription())
											.addValue("Threshold", hostelInventory.getThreshold())
											.addValue("Price", hostelInventory.getPrice())
											.addValue("Room_Capacity", hostelInventory.getRoomCapacity());
		
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	
// UPDATE DATA IN HostelInventory TABLE
	
	
	public void updateHostelInventory(HostelInventory hostelInventory) {
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

		String addQuery = hostelQueryProps.getProperty("deleteHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}
	
	

//GET DATA FROM HostelAllocation TABLE
	
	
	
	public HostelAllocation getHostelAllocation(String fileNo) {
		String getQuery = hostelQueryProps.getProperty("getHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
		
HostelAllocation hostelAllocation =  getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter, new RowMapper<HostelAllocation>(){

		public HostelAllocation mapRow(ResultSet rs,int rowNum)throws SQLException {
		HostelAllocation hostel = new HostelAllocation();
		hostel.setBlock(rs.getString("Block"));
		hostel.setFileNo(rs.getString("File_No"));
		hostel.setFloor(rs.getString("Floor"));
		hostel.setName(rs.getString("Name"));
		hostel.setRoomNo(rs.getString("Room_No"));
		hostel.setWing(rs.getString("Wing"));
			
			
			
			return hostel;
														
		}
														
	});

		
/*HostelAllocation hostelAll = null;
if(hostelAllocations != null && hostelAllocations.size()>0 ){
	hostelAll = hostelAllocations.get(0);
}*/
	
return hostelAllocation;
}
	

// INSERT DATA IN HostelAllocation TABLE
	
	
	
	public void addHostelAllocation(HostelAllocation hostelAllocation) {
		String addQuery = hostelQueryProps.getProperty("addHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomNo())
												.addValue("Wing", hostelAllocation.getWing())
												.addValue("Floor", hostelAllocation.getFloor())
												.addValue("Block", hostelAllocation.getBlock())
												.addValue("Name", hostelAllocation.getName())
												.addValue("File_No", hostelAllocation.getFileNo());
		
		
		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
		
		
	}
	
	

// UPDATE DATA IN HostelAllocation TABLE
	
	

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		String updateQuery = hostelQueryProps.getProperty("updateHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomNo())
											.addValue("Wing", hostelAllocation.getWing())
											.addValue("Floor", hostelAllocation.getFloor())
											.addValue("Block", hostelAllocation.getBlock())
											.addValue("Name", hostelAllocation.getName())
											.addValue("File_No", hostelAllocation.getFileNo());

		
		getNamedParamJdbcTemplate().update(updateQuery,namedParameter);
		
		
	}
	

//DELETE DATA FROM HostelAllocation TABLE	

	
	public void deleteHostelAllocation(String fileNo) {

		String addQuery = hostelQueryProps.getProperty("deleteHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
	
		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
	}


// GET DATA FROM HostelReservation TABLE
	
	
	public HostelReservation getHostelReservation(String fileNo) {
		String getQuery = hostelQueryProps.getProperty("getHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
HostelReservation hostelReservation = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter,new RowMapper<HostelReservation>(){

	public HostelReservation mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		HostelReservation hostel = new HostelReservation();
		hostel.setFeePaid(rs.getBoolean("Fee_Paid"));
		hostel.setFileNo(rs.getString("File_No"));
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
		
		String addQuery = hostelQueryProps.getProperty("updateHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", hostelReservation.getFileNo())
											.addValue("Fee_Paid", hostelReservation.isFeePaid())
											.addValue("Type_Code", hostelReservation.getTypeCode())
											.addValue("Allocation_Status",hostelReservation.getAllocationStatus())
											.addValue("Is_Active",hostelReservation.isActive());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		

	}
	
	
// DELETE DATA FROM HostelReservation TABLE	

	public void deleteHostelReservation(String fileNo) {

		String addQuery = hostelQueryProps.getProperty("deleteHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo);
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}
	
	
// GET DATA FROM RoomTypeDetail TABLE
	
	
	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		String getQuery = hostelQueryProps.getProperty("getRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", typeCode);
RoomTypeDetail roomTypeDetail = getNamedParamJdbcTemplate().queryForObject(getQuery, namedParameter,new RowMapper<RoomTypeDetail>(){

	public RoomTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoomTypeDetail room = new RoomTypeDetail();
		room.setRoomNo(rs.getString("Room_No"));
		room.setTypeCode(rs.getString("Type_Code"));
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
		String addQuery = hostelQueryProps.getProperty("addRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
											.addValue("Type_Code", roomTypeDetail.getTypeCode());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}
	
	
//UPDATE DATA IN RoomTypeDetail TABLE
	
	
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		
		String addQuery = hostelQueryProps.getProperty("updateRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
											.addValue("Type_Code", roomTypeDetail.getTypeCode());
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}
	
//DELETE DATA IN RoomTypeDetail TABLE	

	public void deleteRoomTypeDetail(String typeCode) {
		
		String addQuery = hostelQueryProps.getProperty("deleteRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}




public List<HostelAvailability> getHostelAvailability() {
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

	
}
