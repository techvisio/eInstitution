package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
@Component
public interface HostelManager {
	
	public List<HostelAvailability> getHostelAvailability();
	
	public HostelAllocation getHostelAllocation(Long fileNo);
	public void addHostelAllocation(HostelAllocation hostelAllocation);
	public void updateHostelAllocation(HostelAllocation hostelAllocation);
	public void deleteHostelAllocation( Long fileNo);

	
	public HostelReservation getHostelReservation(Long fileNo);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);

	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);

	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

	public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

}
