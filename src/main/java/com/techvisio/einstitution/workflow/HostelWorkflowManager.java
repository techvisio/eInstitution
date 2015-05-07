package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
@Component
public interface HostelWorkflowManager {

    
	public List<HostelAvailability> getHostelAvailability();
	
	public RoomAllocationDetail getHostelAllocation(Long fileNo);
	public void addHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void deleteHostelAllocation( Long fileNo);

	
	public HostelReservation getHostelReservation(Long fileNo);
	public Long addHostelReservation(HostelReservation hostelReservation);
	public Long updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);

	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);

	public HostelAllocationAdmissionBean getHostelAllocationAdmissiondtl(Long fileNo);

	public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean);

	
}
