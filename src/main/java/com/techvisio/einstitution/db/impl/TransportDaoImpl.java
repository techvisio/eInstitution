package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.Transport;
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

				availableTransport.setRouteCode(rs.getString("Route_Code"));
				availableTransport.setAvailable(rs.getInt("Available_Seat"));
				availableTransport.setReserved(rs.getInt("Reserved_Seat"));
				availableTransport.setThreshold(rs.getString("Threshold"));
				availableTransport.setPrice(rs.getDouble("Price"));
				availableTransport.setDescription(rs.getString("Description"));
				return availableTransport;
			}
		});
		return availableTransports;
	}
	
	
	public Transport getTransport(String routeCode) {
		logger.info("{} : get transport for routecode:{}",this.getClass().getName(), routeCode);
		String getQuery = transportQueryProps
				.getProperty("getTransportByRouteCode");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Route_Code", routeCode);

		List<Transport> transports = getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new RowMapper<Transport>() {

					public Transport mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Transport transport = new Transport();

						transport.setRouteCode(rs.getString("Route_Code"));
						transport.setDescription(rs.getString("Description"));
						transport.setThreshold(rs.getString("Threshold"));
						transport.setPrice(rs.getDouble("Price"));

						return transport;
					}
				});

 		Transport transport = null;

		if (transports != null && transports.size() > 0) {

			transport = transports.get(0);
		}

		return transport;
	}

	public void addTransport(Transport transport) {
		logger.info("{} : add transport for route code:{}",this.getClass().getName(), transport.getRouteCode());
		String addQuery = transportQueryProps.getProperty("addTransport");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Route_Code", transport.getRouteCode())
		.addValue("Description", transport.getDescription())
		.addValue("Threshold", transport.getThreshold())
		.addValue("Price", transport.getPrice());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateTransport(Transport transport) {
		logger.info("{} : update transport for route code:{}",this.getClass().getName(), transport.getRouteCode());
		String updateQuery = transportQueryProps.getProperty("updateTransport");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Route_Code", transport.getRouteCode())
		.addValue("Description", transport.getDescription())
		.addValue("Threshold", transport.getThreshold())
		.addValue("Price", transport.getPrice());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	public void deleteTransport(String routeCode) {
		logger.info("{} : delete transport for route code:{}",this.getClass().getName(), routeCode);
		String deleteQuery = transportQueryProps.getProperty("deletetransport");

		SqlParameterSource namedparameter = new MapSqlParameterSource(
				"Route_Code", routeCode);

		getNamedParamJdbcTemplate().update(deleteQuery, namedparameter);

	}

	public TransportAllocation getTransportAllocationDtl(Long fileNo) {
		logger.info("{} : get transport allocation for file no :{}",this.getClass().getName(), fileNo);
		String getQuery = transportQueryProps
				.getProperty("getTransportAllocationByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		List<TransportAllocation> transportAllocations = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<TransportAllocation>() {

					public TransportAllocation mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						TransportAllocation transportAllocation = new TransportAllocation();

						transportAllocation.setFileNo(rs.getLong("File_No"));
						
						Long vehicleId = (CommonUtil.getLongValue(rs.getLong("Vehicle_Id")));
						VehicleDetail vehicleDetail = cacheManager.getVehicleDeatilByVehicleId(vehicleId);
						
						transportAllocation.setVehicleDetail(vehicleDetail);
						transportAllocation.setAllocated(rs.getBoolean("isAllocated"));
						transportAllocation.setAllocatedBy(rs.getString("Allocated_By"));
						transportAllocation.setAllocatedOn(rs.getDate("Allocated_on"));
						transportAllocation.setRemark(rs.getString("Remark"));
						transportAllocation.setSwitchedOn(rs.getDate("Switched_On"));
						transportAllocation.setUpdatedBy(rs.getString("Updated_by"));
						return transportAllocation;

					}
				});

		TransportAllocation transportAllocation = null;

		if (transportAllocations != null && transportAllocations.size() > 0) {

			transportAllocation = transportAllocations.get(0);
		}
		return transportAllocation;
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {
		logger.info("{} : add transport allocation detail for file no :{}",this.getClass().getName(), transportAllocation.getFileNo());
		String addQuery = transportQueryProps
				.getProperty("addTransportAllocation");

		SqlParameterSource namedParameter = getParameterSource(transportAllocation);
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	
	
	public void updateTransportAllocationDtl(
			TransportAllocation transportAllocation) {
		logger.info("{} : update transport allocation detail for file no :{}",this.getClass().getName(), transportAllocation.getFileNo());
		String updateQuery = transportQueryProps
				.getProperty("updateTransportAllocation");

		SqlParameterSource namedParameter = getParameterSource(transportAllocation);

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	
private MapSqlParameterSource getParameterSource (TransportAllocation transportAllocation){
	logger.info("{} : Adding value in particular field through MapSqlParameterSource ",this.getClass().getName());
	return new MapSqlParameterSource("File_No", transportAllocation.getFileNo())
											.addValue("Vehicle_Id", transportAllocation.getVehicleDetail().getVehicleId())
											.addValue("Allocated_on", transportAllocation.getAllocatedOn())
											.addValue("Allocated_By", transportAllocation.getAllocatedBy())
											.addValue("Updated_by", transportAllocation.getUpdatedBy())
											.addValue("Switched_On", transportAllocation.getSwitchedOn())
											.addValue("isAllocated", transportAllocation.isAllocated())	
											.addValue("Remark", transportAllocation.getRemark());
	
}
	public void deleteTransportAllocationDtl(Long fileNo) {
		logger.info("{} : delete transport allocation detail for file no :{}",this.getClass().getName(), fileNo);
		String deleteQuery = transportQueryProps
				.getProperty("deleteTransportAllocation");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : get transport reservation detail for file no :{}",this.getClass().getName(), fileNo);
		String getQuery = transportQueryProps
				.getProperty("getTransportReservationByFileNo");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		List<TransportReservation> transportReservations = getNamedParamJdbcTemplate()
				.query(getQuery, namedParameter,
						new RowMapper<TransportReservation>() {

					public TransportReservation mapRow(ResultSet rs,
							int rowNum) throws SQLException {

						TransportReservation transportReservation = new TransportReservation();

						transportReservation.setFileNo(rs
								.getLong("File_No"));
						transportReservation.setRouteCode(rs
								.getString("Route_Code"));
						transportReservation.setFeePaid(rs
								.getBoolean("Fee_Paid"));
						transportReservation.setAllocationStatus(rs.getString("Allocation_Status"));
						transportReservation.setActive(rs.getBoolean("Is_Active"));
						transportReservation.setPrice(rs.getDouble("Price"));
						transportReservation.setDescription(rs.getString("Description"));
						
						return transportReservation;
					}
				});

		TransportReservation transportReservation = null;

		if (transportReservations != null && transportReservations.size()>0) {

			transportReservation = transportReservations.get(0);
		}
		return transportReservation;
	}

	public void addTransportReservationDtl(
			TransportReservation transportReservation) {
		logger.info("{} : add transport reservation detail for file no :{}",this.getClass().getName(), transportReservation.getFileNo());
		String addQuery = transportQueryProps
				.getProperty("addTransportReservation");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", transportReservation.getFileNo()).addValue(
						"Fee_Paid", transportReservation.isFeePaid()).addValue(
								"Route_Code", transportReservation.getRouteCode())
								.addValue("Allocation_Status", transportReservation.getAllocationStatus())
								.addValue("Is_Active", transportReservation.isActive());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateTransportReservationDtl(
			TransportReservation transportReservation) {
		logger.info("{} : update transport reservation detail for file no :{}",this.getClass().getName(),transportReservation.getFileNo());
		String updateQuery = transportQueryProps
				.getProperty("updateTransportReservation");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", transportReservation.getFileNo()).addValue(
						"Fee_Paid", transportReservation.isFeePaid()).addValue(
								"Route_Code", transportReservation.getRouteCode())
								.addValue("Allocation_Status", transportReservation.getAllocationStatus())
								.addValue("Is_Active", transportReservation.isActive());


		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
	}

	public void deleteTransportReservationDtl(Long fileNo) {
		logger.info("{} : delete transport reservation detail for file no :{}",this.getClass().getName(), fileNo);
		String deleteQuery = transportQueryProps
				.getProperty("deleteTransportReservation");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"File_No", fileNo);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}

	public VehicleDetail getVehicleDetail(Long vehicleId) {
		logger.info("{} : get vehicle detail for vehicle id :{}",this.getClass().getName(), vehicleId);
		String getQuery = transportQueryProps
				.getProperty("getVehicleDetailByVehicleId");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Vehicle_Id", vehicleId);

		List<VehicleDetail> vehicleDetails = getNamedParamJdbcTemplate().query(
				getQuery, namedParameter, new RowMapper<VehicleDetail>() {

					public VehicleDetail mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						VehicleDetail vehicleDetail = new VehicleDetail();

						vehicleDetail.setVehicleId(CommonUtil.getLongValue(rs.getLong("Vehicle_Id")));
						vehicleDetail.setRouteCode(rs.getString("Route_Code"));
						vehicleDetail.setVehicleNo(rs.getString("Vehicle_No"));
						vehicleDetail.setType(rs.getString("Type"));
						vehicleDetail.setCapacity(rs.getString("Capacity"));
						return vehicleDetail;
					}
				});

		VehicleDetail vehicleDetail = null;

		if (vehicleDetails != null && vehicleDetails.size() > 0) {

			vehicleDetail = vehicleDetails.get(0);
		}

		return vehicleDetail;
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : add vehicle detail for vehicle id :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
		String addQuery = transportQueryProps.getProperty("addtVehicleDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Vehicle_Id", vehicleDetail.getVehicleId())
		.addValue("Type", vehicleDetail.getType())
		.addValue("Capacity", vehicleDetail.getCapacity())
		.addValue("Vehicle_No", vehicleDetail.getVehicleNo())
		.addValue("Route_Code", vehicleDetail.getRouteCode());

		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : update vehicle detail for vehicle id :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
		String updateQuery = transportQueryProps
				.getProperty("updateVehicleDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Vehicle_Id", vehicleDetail.getVehicleId())
		.addValue("Type", vehicleDetail.getType())
		.addValue("Capacity", vehicleDetail.getCapacity())
		.addValue("Vehicle_No", vehicleDetail.getVehicleNo())
		.addValue("Route_Code", vehicleDetail.getRouteCode());

		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);

	}

	public void deleteVehicleDetail(Long vehicleId) {
		logger.info("{} : delete vehicle detail for vehicle id :{}",this.getClass().getName(), vehicleId);
		String deleteQuery = transportQueryProps
				.getProperty("deleteVehicleDetail");

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"Vehicle_Id", vehicleId);

		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

	}


	@Override
	public TransportAllocation getVehicleAllocatedDetail(Long fileNo) {
		logger.info("{} : get vehicle allocated detail for file no :{}",this.getClass().getName(), fileNo);
		String getQuery = transportQueryProps.getProperty("getVehicleAllocatedDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("file_no",fileNo);
		List<TransportAllocation> transportAllocations = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<TransportAllocation>(){

			@Override
			public TransportAllocation mapRow(ResultSet rs, int arg1)
					throws SQLException {
				TransportAllocation allocation = new TransportAllocation();
				allocation.setAllocated(rs.getBoolean(""));
				allocation.setAllocatedBy(rs.getString(""));
				allocation.setAllocatedOn(rs.getDate(""));
				allocation.setFileNo(CommonUtil.getLongValue(rs.getLong("")));
				allocation.setRemark(rs.getString(""));
				allocation.setSwitchedOn(rs.getDate(""));
				allocation.setUpdatedBy(rs.getString(""));
				Long vehicleId = (CommonUtil.getLongValue(rs.getLong("")));
				VehicleDetail vehicleDetail = cacheManager.getVehicleDeatilByVehicleId(vehicleId);
				allocation.setVehicleDetail(vehicleDetail);
						
				
				return allocation;
			}
			
		});
		TransportAllocation transportAllocation = null;
		if(transportAllocations !=null && transportAllocations.size() >0 ){
			transportAllocation = transportAllocations.get(0);
		}
		
		return transportAllocation;
	}
/*
SqlParameterSource namedParameter = new MapSqlParameterSource("file_no",fileNo);
		List<RoomAllocationDetail> roomAllocationDetails = getNamedParamJdbcTemplate().query(getQuery, namedParameter,  new RowMapper<RoomAllocationDetail>(){

			@Override
			public RoomAllocationDetail mapRow(ResultSet rs, int arg1)
					throws SQLException {
				RoomAllocationDetail allocationDetail = new RoomAllocationDetail();
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
		RoomAllocationDetail roomAllocationDetail = null;
		if(roomAllocationDetails != null && roomAllocationDetails.size()>0 ){
			roomAllocationDetail = roomAllocationDetails.get(0);
		}

		return roomAllocationDetail;
	}
 */

	@Override
	public List<TransportAllocation> getPreviousAllocatedDetail(Long fileNo) {
		logger.info("{} : Get previous allocation detail for file no:{}",this.getClass().getName(), fileNo);
		String getQuery = transportQueryProps.getProperty("getPreviousAllocatedDetail");
		SqlParameterSource namedParameter = new MapSqlParameterSource("file_no",fileNo);
		List<TransportAllocation> transportAllocations = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<TransportAllocation>(){

			@Override
			public TransportAllocation mapRow(ResultSet rs, int arg1)
					throws SQLException {
				TransportAllocation allocation = new TransportAllocation();
				allocation.setAllocated(rs.getBoolean(""));
				allocation.setAllocatedBy(rs.getString(""));
				allocation.setAllocatedOn(rs.getDate(""));
				allocation.setFileNo(CommonUtil.getLongValue(rs.getLong("")));
				allocation.setRemark(rs.getString(""));
				allocation.setSwitchedOn(rs.getDate(""));
				allocation.setUpdatedBy(rs.getString(""));
				Long vehicleId = (CommonUtil.getLongValue(rs.getLong("")));
				VehicleDetail vehicleDetail = cacheManager.getVehicleDeatilByVehicleId(vehicleId);
				allocation.setVehicleDetail(vehicleDetail);
				return allocation;
			}
			
		});
		return transportAllocations;
	}

}
