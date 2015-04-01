package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

public interface TransportDao {
	
	public List<AvailableTransport> getAvailableTransports();
	
	public Transport getTransport(String routeCode);
	public void addTransport(Transport transport);
	public void updateTransport(Transport transport);
	public void deleteTransport(String routeCode);

	public TransportAllocation getTransportAllocationDtl(Long fileNo);
	public void addTransportAllocationDtl(TransportAllocation transportAllocation);
	public void updateTransportAllocationDtl(TransportAllocation transportAllocation);
	public void deleteTransportAllocationDtl(Long fileNo);

	public TransportReservation getTransportReservationDtl(Long fileNo);
	public void addTransportReservationDtl(TransportReservation transportReservation);
	public void updateTransportReservationDtl(TransportReservation transportReservation);
	public void deleteTransportReservationDtl(Long fileNo);

	public VehicleDetail getVehicleDetail(Long vehicleId);
	public void addVehicleDetail(VehicleDetail vehicleDetail);
	public void updateVehicleDetail(VehicleDetail vehicleDetail);
	public void deleteVehicleDetail(Long vehicleId);

}
