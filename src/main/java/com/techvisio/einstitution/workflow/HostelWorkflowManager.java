package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
@Component
@Transactional
public interface HostelWorkflowManager {


	public List<HostelAvailability> getHostelAvailability();

	public HostelReservation getHostelReservation(Long fileNo);

	public void saveHostelReservation(HostelReservation hostelReservation);

	public void deleteHostelReservation(Long fileNo);
}
