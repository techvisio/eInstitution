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

	public HostelReservation getHostelReservation(Long fileNo);

	public void saveHostelReservation(HostelReservation hostelReservation, Long fileNo);

	public void deleteHostelReservation(Long fileNo);

	


}