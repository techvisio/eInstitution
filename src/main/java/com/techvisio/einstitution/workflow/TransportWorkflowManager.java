package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmission;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportAllocationForStudent;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
@Component
@Transactional
public interface TransportWorkflowManager {

    public List<AvailableTransport> getAvailableTransport();

	public TransportReservation getTransportReservationDtl(Long fileNo);
	public void saveTransportReservationDtl(TransportReservation transportReservation);
	public void deleteTransportReservationDtl(Long fileNo);
}
