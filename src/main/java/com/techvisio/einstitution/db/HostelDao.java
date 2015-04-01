package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelInventory;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;

public interface HostelDao {

	public List<HostelAvailability> getHostelAvailability();
	
	
	
	public HostelInventory getHostelInventory(String typeCode);
	public void addHostelInventory(HostelInventory hostelInventory);
	public void updateHostelInventory(HostelInventory hostelInventory);
	public void deleteHostelInventory(String typeCode);
	
	
	
	public HostelAllocation getHostelAllocation(Long fileNo);
	public void addHostelAllocation(HostelAllocation hostelAllocation);
	public void updateHostelAllocation(HostelAllocation hostelAllocation);
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