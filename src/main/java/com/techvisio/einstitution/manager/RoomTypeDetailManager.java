package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.RoomTypeDetail;

public interface RoomTypeDetailManager {
	public RoomTypeDetail getRoomTypeDetail(String typeCode);
	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail);
	public void deleteRoomTypeDetail(String typeCode);
}
