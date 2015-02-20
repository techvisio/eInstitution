package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.manager.impl.TransportManagerImpl;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;

public class TransportWorkflowManagerImpl implements TransportWorkflowManager{

	TransportManager manager = TransportManagerImpl.getInstance();
	
	public List<AvailableTransport> getAvailableTransport() {

		return manager.getAvailableTransport();
	}

	public TransportAllocation getTransportAllocationDtl(String fileNo) {

		return manager.getTransportAllocationDtl(fileNo);
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		manager.addTransportAllocationDtl(transportAllocation);
	}

	public void updateTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		manager.updateTransportAllocationDtl(transportAllocation);
	}

	public void deleteTransportAllocationDtl(String fileNo) {

		manager.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(String fileNo) {

		return manager.getTransportReservationDtl(fileNo);
	}

	public void addTransportReservationDtl(
			TransportReservation transportReservation) {

		manager.addTransportReservationDtl(transportReservation);
	}

	public void updateTransportReservationDtl(
			TransportReservation transportReservation) {

		manager.updateTransportReservationDtl(transportReservation);
	}

	public void deleteTransportReservationDtl(String fileNo) {

		manager.deleteTransportReservationDtl(fileNo);
	}

	public VehicleDetail getVehicleDetail(Long vehicleId) {

		return manager.getVehicleDetail(vehicleId);
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {

		manager.addVehicleDetail(vehicleDetail);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {

		manager.updateVehicleDetail(vehicleDetail);
	}

	public void deleteVehicleDetail(Long vehicleId) {

		manager.deleteVehicleDetail(vehicleId);
	}

}
