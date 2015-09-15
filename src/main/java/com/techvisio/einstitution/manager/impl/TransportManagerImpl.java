package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmission;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.db.TransportDao;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class TransportManagerImpl implements TransportManager {
	private static CustomLogger logger = CustomLogger.getLogger(TaskFollowManagerImpl.class);
	@Autowired
	TransportDao transportDao;

	private static TransportManagerImpl instance=null;
	public static synchronized TransportManagerImpl getInstance()
	{
		if(instance == null){
			instance=new TransportManagerImpl();
		}

		return instance;
	}

	private TransportManagerImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<AvailableTransport> getAvailableTransport(){
		logger.info("{} : calling getAvailableTransports method  ",this.getClass().getName());
		List<AvailableTransport> availableTransports = null;

		availableTransports = transportDao.getAvailableTransports();
		return availableTransports;
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling getTransportReservationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		TransportReservation transportReservation=null;
		transportReservation= transportDao.getTransportReservationDtl(fileNo);

		return transportReservation;
	}

	public void saveTransportReservationDtl(TransportReservation transportReservation, Long fileNo) {
		logger.info("{} : calling addTransportReservationDtl method for fileno:{}  ",this.getClass().getName(), transportReservation.getFileNo());
		transportDao.saveTransportReservationDtl(transportReservation, fileNo);
	}

	public void deleteTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling deleteTransportReservationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		transportDao.deleteTransportReservationDtl(fileNo);
	}

	@Override
	public TransportAllocation getTransportAllocation(Long fileNo) {
		
		TransportAllocation transportAllocation = transportDao.getTransportAllocation(fileNo);
		return transportAllocation;
	}
	
	@Override
	public void saveTransportAllocationDtl(TransportAllocation transportAllocation, Long fileNo) {
		transportDao.saveTransportAllocationDtl(transportAllocation, fileNo);
	}
	
	@Override
	public void deleteTransportAllocationDtl(Long fileNo) {
		transportDao.deleteTransportAllocationDtl(fileNo);
	}
	
	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		List<StudentBasicInfo> basicInfos = transportDao.getStudentDtlBySearchCriteria(searchCriteria);
		return basicInfos;
	}
	
	@Override
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		StudentBasicInfo basicInfo = transportDao.getStudentBsInfo(fileNo);
		return basicInfo;
	}

	@Override
	public List<VehicleDetail> getAvailableVehicles() {
		List<VehicleDetail> vehicleDetails = transportDao.getAvailableVehicles();
		return vehicleDetails;
	}
}