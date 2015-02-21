package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

public interface TransportWorkflowManager {

public List<AvailableTransport> getAvailableTransport();
	
	public TransportAllocation getTransportAllocationDtl(String fileNo);
	public void addTransportAllocationDtl(TransportAllocation transportAllocation);
	public void updateTransportAllocationDtl(TransportAllocation transportAllocation);
	public void deleteTransportAllocationDtl(String fileNo);

	public TransportReservation getTransportReservationDtl(String fileNo);
	public String addTransportReservationDtl(TransportReservation transportReservation);
	public void updateTransportReservationDtl(TransportReservation transportReservation);
	public void deleteTransportReservationDtl(String fileNo);

	public VehicleDetail getVehicleDetail(Long vehicleId);
	public void addVehicleDetail(VehicleDetail vehicleDetail);
	public void updateVehicleDetail(VehicleDetail vehicleDetail);
	public void deleteVehicleDetail(Long vehicleId);

	
}
