package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmissionBean;
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
	
	
	public TransportAllocation getTransportAllocationDtl(Long fileNo) {
		logger.info("{} : calling getTransportAllocationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		TransportAllocation transportAllocation=null;
	
		transportAllocation=transportDao.getTransportAllocationDtl(fileNo);

		return transportAllocation;
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {
		logger.info("{} : calling addTransportAllocationDtl method for fileno:{}  ",this.getClass().getName(), transportAllocation.getFileNo());
		transportDao.addTransportAllocationDtl(transportAllocation);
	}

	public void updateTransportAllocationDtl(
			TransportAllocation transportAllocation) {
		logger.info("{} : calling updateTransportAllocationDtl method for fileno:{}  ",this.getClass().getName(), transportAllocation.getFileNo());
		transportDao.updateTransportAllocationDtl(transportAllocation);
	}

	public void deleteTransportAllocationDtl(Long fileNo) {
		logger.info("{} : calling deleteTransportAllocationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		transportDao.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling getTransportReservationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		TransportReservation transportReservation=null;
		transportReservation= transportDao.getTransportReservationDtl(fileNo);

		return transportReservation;
	}

	public void addTransportReservationDtl(
			TransportReservation transportReservation) {
		logger.info("{} : calling addTransportReservationDtl method for fileno:{}  ",this.getClass().getName(), transportReservation.getFileNo());
		transportDao.addTransportReservationDtl(transportReservation);
	}

	public void updateTransportReservationDtl(
			TransportReservation transportReservation) {
		logger.info("{} : calling updateTransportReservationDtl method for fileno:{}  ",this.getClass().getName(), transportReservation.getFileNo());
		transportDao.updateTransportReservationDtl(transportReservation);
	}

	public void deleteTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling deleteTransportReservationDtl method by passing fileno:{}  ",this.getClass().getName(), fileNo);
		transportDao.deleteTransportReservationDtl(fileNo);
	}

	public VehicleDetail getVehicleDetail(Long vehicleId) {
		logger.info("{} : calling getVehicleDetail method by passing vehicleId:{}  ",this.getClass().getName(),vehicleId);
		VehicleDetail vehicleDetail = null;
		vehicleDetail = transportDao.getVehicleDetail(vehicleId);
		return vehicleDetail;
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : calling addVehicleDetail method for vehicleId:{}  ",this.getClass().getName(),vehicleDetail.getVehicleId());
		transportDao.addVehicleDetail(vehicleDetail);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : calling updateVehicleDetail method for vehicleId:{}  ",this.getClass().getName(),vehicleDetail.getVehicleId());
		transportDao.updateVehicleDetail(vehicleDetail);
	}

	public void deleteVehicleDetail(Long vehicleId) {
		logger.info("{} : calling deleteVehicleDetail method by passing vehicleId:{}  ",this.getClass().getName(),vehicleId);
		transportDao.deleteVehicleDetail(vehicleId);
	}
	
	@Override
	public void addTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
		logger.info("{} : add Transport Allocation AdmissionDtl for Student:{}  ",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());		
		TransportAllocation transportAllocation = transportAllocationAdmissionBean.getTransportAllocation();
		updateTransportAllocationDtl(transportAllocation);
	}

	@Override
	public void updateTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
		logger.info("{} : update Transport Allocation AdmissionDtl for Student:{}  ",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());		
		TransportAllocation transportAllocation = transportAllocationAdmissionBean.getTransportAllocation();
		updateTransportAllocationDtl(transportAllocation);
	}

	@Override
	public TransportAllocationDtlForVehicle getCurrentAllocationByVehichleId(
			Long vehicleId) {
		logger.info("{} : get Current Allocation By VehichleId:{}  ",this.getClass().getName(), vehicleId);
		return transportDao.getCurrentAllocationByVehichleId(vehicleId);
	}

	@Override
	public TransportAllocation getVehicleAllocatedDetail(Long fileNo) {
		logger.info("{} : calling getVehicleAllocatedDetail by passing file no:{}  ",this.getClass().getName(), fileNo);
		return transportDao.getVehicleAllocatedDetail(fileNo);
	}

	@Override
	public List<TransportAllocation> getPreviousAllocatedDetail(Long fileNo) {
		logger.info("{} : calling getPreviousAllocatedDetail by passing file no:{}  ",this.getClass().getName(), fileNo);
		return transportDao.getPreviousAllocatedDetail(fileNo);
	}


}
