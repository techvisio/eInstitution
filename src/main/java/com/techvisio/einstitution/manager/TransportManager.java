package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

public interface TransportManager {

	public TransportAllocation getTransportAllocationDtl(String fileNo);
	public void addTransportAllocationDtl(TransportAllocation transportAllocation);
	public void updateTransportAllocationDtl(TransportAllocation transportAllocation);
	public void deleteTransportAllocationDtl(String fileNo);

	public TransportReservation getTransportReservationDtl(String fileNo);
	public void addTransportReservationDtl(TransportReservation transportReservation);
	public void updateTransportReservationDtl(TransportReservation transportReservation);
	public void deleteTransportReservationDtl(String fileNo);

	public VehicleDetail getVehicleDetail(Long vehicleId);
	public void addVehicleDetail(VehicleDetail vehicleDetail);
	public void updateVehicleDetail(VehicleDetail vehicleDetail);
	public void deleteVehicleDetail(Long vehicleId);


}
