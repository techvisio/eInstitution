package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
@Component
public interface HostelWorkflowManager {

    
	public List<HostelAvailability> getHostelAvailability();
	
	public RoomAllocation getHostelAllocation(Long fileNo);
	public void addHostelAllocation(RoomAllocation hostelAllocation);
//	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void deleteHostelAllocation( Long fileNo);

	public RoomAllocationForStudent getAllocationForStudent(Long fileNo);
	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo);
	public RoomAllocation getActiveRoomAllocationDetail (Long fileNo);
	public List<RoomAllocation> getPreviousAllocatedDetail(Long fileNo );
	
	public HostelReservation getHostelReservation(Long fileNo);
	public void saveRoomDetail(RoomAllocation roomAllocationDetail);
	public Long addHostelReservation(HostelReservation hostelReservation);
	public Long updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);

	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);

	public HostelAllocationAdmission getHostelAllocationAdmissiondtl(Long fileNo);

	public void saveHostelAllocationAdmissionDtl(RoomAllocation roomAllocationDetail);

//	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

}
