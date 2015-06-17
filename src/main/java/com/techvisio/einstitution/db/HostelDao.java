package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;

@Component
public interface HostelDao {

	public List<HostelAvailability> getHostelAvailability();
	
	
	
	public RoomType getHostelInventory(String typeCode);
	public void addHostelInventory(RoomType hostelInventory);
	public void updateHostelInventory(RoomType hostelInventory);
	public void deleteHostelInventory(String typeCode);
	
	
	
	public RoomAllocation getHostelAllocation(Long fileNo);
	public void addHostelAllocation(RoomAllocation hostelAllocation);
//	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void deleteHostelAllocation(Long fileNo);

	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo);
	public RoomAllocation getActiveRoomAllocationDtl (Long fileNo);
	public List<RoomAllocation> getPreviousAllocatedDetail(Long fileNo );
	
	public HostelReservation getHostelReservation(Long fileNo);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);
	
	
	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);

}