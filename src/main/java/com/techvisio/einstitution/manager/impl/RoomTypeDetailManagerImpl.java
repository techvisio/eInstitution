package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.RoomTypeDetailManager;
import com.techvisio.einstitution.util.ContextProvider;

public class RoomTypeDetailManagerImpl implements RoomTypeDetailManager {
	HostelDao hostelDao = ContextProvider.getContext().getBean(HostelDao.class);
	
	
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
