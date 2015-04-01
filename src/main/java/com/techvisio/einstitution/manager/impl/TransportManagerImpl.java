package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.db.TransportDao;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.util.ContextProvider;

public class TransportManagerImpl implements TransportManager {

	TransportDao transportDao=ContextProvider.getContext().getBean(TransportDao.class);
	
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
		
		List<AvailableTransport> availableTransports = null;
		
		availableTransports = transportDao.getAvailableTransports();
		return availableTransports;
		
		
	}
	
	
	public TransportAllocation getTransportAllocationDtl(Long fileNo) {

		TransportAllocation transportAllocation=null;
	
		transportAllocation=transportDao.getTransportAllocationDtl(fileNo);

		return transportAllocation;
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		transportDao.addTransportAllocationDtl(transportAllocation);
	}

	public void updateTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		transportDao.updateTransportAllocationDtl(transportAllocation);
	}

	public void deleteTransportAllocationDtl(Long fileNo) {

		transportDao.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {

		TransportReservation transportReservation=null;
		transportReservation= transportDao.getTransportReservationDtl(fileNo);

		return transportReservation;
	}

	public void addTransportReservationDtl(
			TransportReservation transportReservation) {

		transportDao.addTransportReservationDtl(transportReservation);
	}

	public void updateTransportReservationDtl(
			TransportReservation transportReservation) {

		transportDao.updateTransportReservationDtl(transportReservation);
	}

	public void deleteTransportReservationDtl(Long fileNo) {

		transportDao.deleteTransportReservationDtl(fileNo);
	}

	public VehicleDetail getVehicleDetail(Long vehicleId) {

		VehicleDetail vehicleDetail = null;
		vehicleDetail = transportDao.getVehicleDetail(vehicleId);
		return vehicleDetail;
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {

		transportDao.addVehicleDetail(vehicleDetail);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {

		transportDao.updateVehicleDetail(vehicleDetail);
	}

	public void deleteVehicleDetail(Long vehicleId) {

		transportDao.deleteVehicleDetail(vehicleId);
	}

}
