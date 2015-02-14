package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;

public class HostelWorkflowManagerImpl implements HostelWorkflowManager {

	HostelManager manager=HostelManagerImpl.getInstance();
	
	public List<HostelAvailability> getHostelAvailability() {
		return manager.getHostelAvailability();
	}

	public HostelAllocation getHostelAllocation(String fileNo) {

		return manager.getHostelAllocation(fileNo);
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {

		manager.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		manager.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(String fileNo) {

		manager.deleteHostelAllocation(fileNo);
	}

	public HostelReservation getHostelReservation(String fileNo) {

		return manager.getHostelReservation(fileNo);
	}

	public void addHostelReservation(HostelReservation hostelReservation) {

		manager.addHostelReservation(hostelReservation);
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {

		manager.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(String fileNo) {

		manager.deleteHostelReservation(fileNo);
	}

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {

		return manager.getRoomTypeDetail(typeCode);
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		manager.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		manager.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {

		manager.deleteRoomTypeDetail(typeCode);
	}

}
