package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

@Component
public interface TransportDao {

	public List<AvailableTransport> getAvailableTransports();
	public TransportReservation getTransportReservationDtl(Long fileNo);
	public void saveTransportReservationDtl(TransportReservation transportReservation,Long fileNo);
	public void deleteTransportReservationDtl(Long fileNo);
	List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria);
	StudentBasicInfo getStudentBsInfo(Long fileNo);
	void saveTransportAllocationDtl(TransportAllocation transportAllocation,
			Long fileNo);
	void deleteTransportAllocationDtl(Long fileNo);
	TransportAllocation getTransportAllocation(Long fileNo);
	List<VehicleDetail> getAvailableVehicles();
	Boolean isSeatAvailable(Long vehicleId);


}
