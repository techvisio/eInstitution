package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

@Component
public interface TransportDao {
	
	public List<AvailableTransport> getAvailableTransports();
	public TransportReservation getTransportReservationDtl(Long fileNo);
	public void saveTransportReservationDtl(TransportReservation transportReservation);
	public void deleteTransportReservationDtl(Long fileNo);

}
