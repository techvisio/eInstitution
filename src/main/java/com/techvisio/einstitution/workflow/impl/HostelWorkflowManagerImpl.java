package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;

public class HostelWorkflowManagerImpl implements HostelWorkflowManager {

	AdmissionManager admissionManager = AdmissionManagerImpl.getInstance();
	HostelManager hostelManager=HostelManagerImpl.getInstance();
	
	public List<HostelAvailability> getHostelAvailability() {
		return hostelManager.getHostelAvailability();
	}

	public HostelAllocation getHostelAllocation(String fileNo) {

		return hostelManager.getHostelAllocation(fileNo);
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(String fileNo) {

		hostelManager.deleteHostelAllocation(fileNo);
	}

	public HostelReservation getHostelReservation(String fileNo) {

		return hostelManager.getHostelReservation(fileNo);
	}

	public void addHostelReservation(HostelReservation hostelReservation) {
		
		if(hostelReservation.getFileNo() == null ){
		
			StudentDetail newStudentDetail=new StudentDetail();
			String fileNo=admissionManager.addStudentDtl(newStudentDetail);
						
			hostelReservation.setFileNo(fileNo);
		}
		
		hostelManager.addHostelReservation(hostelReservation);
	
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {

		hostelManager.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(String fileNo) {

		hostelManager.deleteHostelReservation(fileNo);
	}

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {

		return hostelManager.getRoomTypeDetail(typeCode);
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		hostelManager.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		hostelManager.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {

		hostelManager.deleteRoomTypeDetail(typeCode);
	}

}
