package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;

@Component
public interface HostelDao {

	public List<HostelAvailability> getHostelAvailability();

	public HostelReservation getHostelReservation(Long fileNo);

	public void saveHostelReservation(HostelReservation hostelReservation, Long fileNo);

	public void deleteHostelReservation(Long fileNo);

	public RoomAllocation getActiveRoomAllocationDtl(Long fileNo);

	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo);

	List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria);

	StudentBasicInfo getStudentBsInfo(Long fileNo);

	RoomAllocation getActiveRoomAllocation(Long fileNo);

	void saveRoomAllocation(RoomAllocation roomAllocation, Long fileNo);

	void deleteRoomAllocation(Long fileNo);

	List<RoomTypeDetail> getAvailableRooms();

}