package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.RouteStoppage;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.beans.VehicleType;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
@Component
@Transactional
public class TransportWorkflowManagerImpl implements TransportWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(TransportWorkflowManagerImpl.class);

	@Autowired
	TransportManager transportManager ;
	
	@Autowired
	CacheManager cacheManager;

	public List<AvailableTransport> getAvailableTransport() {
		logger.info("{} : calling getAvailableTransport ",this.getClass().getName());
		return transportManager.getAvailableTransport();
	}
	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling getTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return transportManager.getTransportReservationDtl(fileNo);
	}

	public void saveTransportReservationDtl(TransportReservation transportReservation, Long fileNo) {
         
		RouteStoppage routeStoppage = cacheManager.getTransportByStopId(transportReservation.getRouteStoppage().getRouteStopId());
		transportReservation.setDescription(routeStoppage.getRoute().getDescription());
		transportReservation.setPrice(routeStoppage.getPrice());
		transportReservation.setActive(true);
		transportManager.saveTransportReservationDtl(transportReservation, fileNo);
         
	}

	public void deleteTransportReservationDtl(Long fileNo) {
		logger.info("{} : deleteTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		transportManager.deleteTransportReservationDtl(fileNo);
	}

	@Override
	public TransportAllocation getTransportAllocation(Long fileNo) {
		
		TransportAllocation transportAllocation = transportManager.getTransportAllocation(fileNo);
		return transportAllocation;
	}
	
	@Override
	public void saveTransportAllocationDtl(TransportAllocation transportAllocation, Long fileNo) {
		transportManager.saveTransportAllocationDtl(transportAllocation, fileNo);
	}
	
	@Override
	public void deleteTransportAllocationDtl(Long fileNo) {
		transportManager.deleteTransportAllocationDtl(fileNo);
	}
	
	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		List<StudentBasicInfo> basicInfos = transportManager.getStudentDtlBySearchCriteria(searchCriteria);
		return basicInfos;
	}
	
	@Override
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		StudentBasicInfo basicInfo = transportManager.getStudentBsInfo(fileNo);
		return basicInfo;
	}

	@Override
	public List<VehicleDetail> getAvailableVehicles() {
		List<VehicleDetail> vehicleDetails = transportManager.getAvailableVehicles();
		return vehicleDetails;
	}

}
