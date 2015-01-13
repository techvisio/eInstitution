package com.techvisio.einstitution.db.impl;

import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelInventory;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;

public class HostelDaoImpl extends BaseDao implements HostelDao {
	
	private Properties hostelQueryProps;
	
	

	
	public void setHostelQueryProps(Properties hostelQueryProps) {
		this.hostelQueryProps = hostelQueryProps;
	}
	


	
	public HostelInventory getHostelInventory(String typeCode) {
		String addQuery = hostelQueryProps.getProperty("getHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", typeCode);
		
	List<HostelInventory> hostelInventories = getNamedParamJdbcTemplate().query(sql, paramSource, rowMapper)
	}

	
	public void addHostelInventory(HostelInventory hostelInventory) {
	
		String addQuery = hostelQueryProps.getProperty("addHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", hostelInventory.getTypeCode())
											.addValue("Description", hostelInventory.getDescription())
											.addValue("Threshold", hostelInventory.getThreshold())
											.addValue("Price", hostelInventory.getPrice())
											.addValue("Room_Capacity", hostelInventory.getRoomCapacity());
		
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateHostelInventory(HostelInventory hostelInventory) {
		String addQuery = hostelQueryProps.getProperty("updateHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code", hostelInventory.getTypeCode())
											.addValue("Description", hostelInventory.getDescription())
											.addValue("Threshold", hostelInventory.getThreshold())
											.addValue("Price", hostelInventory.getPrice())
											.addValue("Room_Capacity", hostelInventory.getRoomCapacity());
	
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);		
		
	}

	public void deleteHostelInventory(String typeCode) {

		String addQuery = hostelQueryProps.getProperty("deleteHostelInventory");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}


	
	public void getHostelAllocation(HostelAllocation hostelAllocation) {
		// TODO Auto-generated method stub
		
	}
	


	
	
	public void addHostelAllocation(HostelAllocation hostelAllocation) {
		String addQuery = hostelQueryProps.getProperty("addHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomNo())
												.addValue("Wing", hostelAllocation.getWing())
												.addValue("Floor", hostelAllocation.getWing())
												.addValue("Block", hostelAllocation.getBlock())
												.addValue("Name", hostelAllocation.getName())
												.addValue("File_No", hostelAllocation.getFileNo());
		
		
		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
		
		
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		String addQuery = hostelQueryProps.getProperty("updateHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", hostelAllocation.getRoomNo())
											.addValue("Wing", hostelAllocation.getWing())
											.addValue("Floor", hostelAllocation.getWing())
											.addValue("Block", hostelAllocation.getBlock())
											.addValue("Name", hostelAllocation.getName())
											.addValue("File_No", hostelAllocation.getFileNo());

		
		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
		
		
	}

	public void deleteHostelAllocation(String fileNo) {

		String addQuery = hostelQueryProps.getProperty("deleteHostelAllocation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", fileNo);
	
		getNamedParamJdbcTemplate().update(addQuery,namedParameter);
	}


	
	public void getHostelReservation(HostelReservation hostelReservation) {
		// TODO Auto-generated method stub
		
	}

	
	

	public void addHostelReservation(HostelReservation hostelReservation) {

		String addQuery = hostelQueryProps.getProperty("addHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", hostelReservation.getFileNo())
											.addValue("Fee_Paid", hostelReservation.getFeePaid())
											.addValue("Type_Code", hostelReservation.getTypeCode());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {
		
		String addQuery = hostelQueryProps.getProperty("updateHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No", hostelReservation.getFileNo())
											.addValue("Fee_Paid", hostelReservation.getFeePaid())
											.addValue("Type_Code", hostelReservation.getTypeCode());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		

	}

	public void deleteHostelReservation(String fileNo) {

		String addQuery = hostelQueryProps.getProperty("deleteHostelReservation");
		SqlParameterSource namedParameter = new MapSqlParameterSource("File_No",fileNo);
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	
	
	public void getRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		String addQuery = hostelQueryProps.getProperty("addRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
											.addValue("Type_Code", roomTypeDetail.getTypeCode());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		
		String addQuery = hostelQueryProps.getProperty("updateRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Room_No", roomTypeDetail.getRoomNo())
											.addValue("Type_Code", roomTypeDetail.getTypeCode());
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

	}

	public void deleteRoomTypeDetail(String typeCode) {
		
		String addQuery = hostelQueryProps.getProperty("deleteRoomTypeDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Type_Code",typeCode);
	
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	
}
