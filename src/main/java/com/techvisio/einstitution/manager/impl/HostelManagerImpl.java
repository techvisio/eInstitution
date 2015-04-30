package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.util.ContextProvider;
@Component
public class HostelManagerImpl implements HostelManager {
	
	@Autowired
	HostelDao hostelDao ;
//HostelAllocation
	
	
	private static HostelManagerImpl instance=null;
	public static synchronized HostelManagerImpl getInstance()
	{
		if(instance == null){
			instance=new HostelManagerImpl();
		}
		
		return instance;
	}
	
	private HostelManagerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HostelAllocation getHostelAllocation(Long fileNo) {
		HostelAllocation h = hostelDao.getHostelAllocation(fileNo);
		return h;
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {
		hostelDao.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(Long fileNo) {
		hostelDao.deleteHostelAllocation(fileNo);
	}

	
//HostelReservation
	
	
	public HostelReservation getHostelReservation(Long fileNo) {
		HostelReservation hostelReservation = hostelDao.getHostelReservation(fileNo);
		return hostelReservation;	}

	public void addHostelReservation(HostelReservation hostelReservation) {
		hostelDao.addHostelReservation(hostelReservation);
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {
		hostelDao.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(Long fileNo) {
		hostelDao.deleteHostelReservation(fileNo);
	}



//RoomTypeDetail	
	

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		RoomTypeDetail roomTypeDetail = hostelDao.getRoomTypeDetail(typeCode);
		return roomTypeDetail;
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		hostelDao.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		hostelDao.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {
		hostelDao.deleteRoomTypeDetail(typeCode);
	}

	
// HsotelAvailability	
	
	
	public List<HostelAvailability> getHostelAvailability() {
		List<HostelAvailability>  h=null;
		h= hostelDao.getHostelAvailability();
		
		return h;
	}

@Override
public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
	
	HostelAllocation hostelAllocation = hostelAllocationAdmissionBean.getHostelAllocation();
	addHostelAllocation(hostelAllocation);
}

@Override
public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
	
	HostelAllocation hostelAllocation = hostelAllocationAdmissionBean.getHostelAllocation();
	updateHostelAllocation(hostelAllocation);
}

}
