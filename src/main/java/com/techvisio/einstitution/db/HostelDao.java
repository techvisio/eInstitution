package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelInventory;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;

public interface HostelDao {

	public HostelInventory getHostelInventory(String hostelInventory);
	public void addHostelInventory(HostelInventory hostelInventory);
	public void updateHostelInventory(HostelInventory hostelInventory);
	public void deleteHostelInventory(String hostelInventory);
	
	
	
	public HostelAllocation getHostelAllocation(String hostelAllocation);
	public void addHostelAllocation(HostelAllocation hostelAllocation);
	public void updateHostelAllocation(HostelAllocation hostelAllocation);
	public void deleteHostelAllocation(String hostelAllocation);
	
	
	public HostelReservation getHostelReservation(String hostelReservation);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(String hostelReservation);
	
	
	public RoomTypeDetail getRoomTypeDetail(String roomTypeDetail);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String roomTypeDetail);


}
