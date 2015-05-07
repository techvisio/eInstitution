package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.RoomTypeMaster;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;

@Component
public interface HostelDao {

	public List<HostelAvailability> getHostelAvailability();
	
	
	
	public RoomTypeMaster getHostelInventory(String typeCode);
	public void addHostelInventory(RoomTypeMaster hostelInventory);
	public void updateHostelInventory(RoomTypeMaster hostelInventory);
	public void deleteHostelInventory(String typeCode);
	
	
	
	public RoomAllocationDetail getHostelAllocation(Long fileNo);
	public void addHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation);
	public void deleteHostelAllocation(Long fileNo);
	
	
	public HostelReservation getHostelReservation(Long fileNo);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(Long fileNo);
	
	
	
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);


}