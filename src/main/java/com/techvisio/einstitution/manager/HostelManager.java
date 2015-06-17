package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomTypeDetail;
@Component
public interface HostelManager {
	
	public List<HostelAvailability> getHostelAvailability();
	
	public RoomAllocation getHostelAllocation(Long fileNo);
//	public void saveRoomDetail(RoomAllocationDetail roomAllocationDetail);
	public void addHostelAllocation(RoomAllocation hostelAllocation);
//	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void deleteHostelAllocation( Long fileNo);

	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo);
	public RoomAllocation getActiveRoomAllocationDetail (Long fileNo);
	public List<RoomAllocation> getPreviousAllocatedDetail(Long fileNo );
	
	public HostelReservation getHostelReservation(Long fileNo);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);

	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);

	public void saveAllocationDetails(RoomAllocation newRoomAllocation,RoomAllocation oldRoomAllocation);

//	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

//	public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

	

}
