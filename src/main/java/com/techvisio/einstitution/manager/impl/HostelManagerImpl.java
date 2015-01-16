package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.util.ContextProvider;

public class HostelManagerImpl implements HostelManager {
	
	HostelDao hostelDao = ContextProvider.getContext().getBean(HostelDao.class);
//HostelAllocation
	
	
	public HostelAllocation getHostelAllocation(String fileNo) {
		HostelAllocation h = hostelDao.getHostelAllocation("fileNo");
		return h;
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(String fileNo) {
		hostelDao.deleteHostelAllocation("fileNo");
	}

	
//HostelReservation
	
	
	public HostelReservation getHostelReservation(String fileNo) {
		HostelReservation hostelReservation = hostelDao.getHostelReservation("fileNo");
		return hostelReservation;	}

	public void addHostelReservation(HostelReservation hostelReservation) {
		hostelDao.addHostelReservation(hostelReservation);
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {
		hostelDao.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(String fileNo) {
		hostelDao.deleteHostelReservation("fileNo");
	}



//RoomTypeDetail	
	

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		RoomTypeDetail roomTypeDetail = hostelDao.getRoomTypeDetail("typeCode");
		return roomTypeDetail;
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		hostelDao.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		hostelDao.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {
		hostelDao.deleteRoomTypeDetail("typeCode");
	}

}
