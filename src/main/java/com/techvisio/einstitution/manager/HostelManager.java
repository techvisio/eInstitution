package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
@Component
public interface HostelManager {

	public List<HostelAvailability> getHostelAvailability();

	public HostelReservation getHostelReservation(Long fileNo);

	public void saveHostelReservation(HostelReservation hostelReservation, Long fileNo);

	public void deleteHostelReservation(Long fileNo);

	List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria);

	StudentBasicInfo getStudentBsInfo(Long fileNo);
	
	RoomAllocation getRoomAllocation(Long fileNo);

	void saveRoomAllocation(RoomAllocation roomAllocation, Long fileNo);

	void deleteRoomAllocation(Long fileNo);

	List<RoomTypeDetail> getAvailableRooms();

}
