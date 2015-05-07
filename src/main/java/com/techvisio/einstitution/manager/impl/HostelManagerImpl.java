package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class HostelManagerImpl implements HostelManager {
	private static CustomLogger logger = CustomLogger.getLogger(HostelManagerImpl.class);	
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
	
	
	public RoomAllocationDetail getHostelAllocation(Long fileNo) {
		logger.info("{} : calling getHostelAllocation method by passing fileNo:{} ",this.getClass().getName(), fileNo);
		RoomAllocationDetail h = hostelDao.getHostelAllocation(fileNo);
		return h;
	}

	public void addHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : calling addHostelAllocation method for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
		hostelDao.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : calling updateHostelAllocation method for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
		hostelDao.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(Long fileNo) {
		logger.info("{} : calling deleteHostelAllocation method by passing fileNo:{} ",this.getClass().getName(), fileNo);
		hostelDao.deleteHostelAllocation(fileNo);
	}

	
//HostelReservation
	
	
	public HostelReservation getHostelReservation(Long fileNo) {
		logger.info("{} : calling getHostelReservation method by passing fileNo:{} ",this.getClass().getName(), fileNo);
		HostelReservation hostelReservation = hostelDao.getHostelReservation(fileNo);
		return hostelReservation;	}

	public void addHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : calling addHostelReservation method for fileNo:{} ",this.getClass().getName(),hostelReservation.getFileNo());
		hostelDao.addHostelReservation(hostelReservation);
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : calling updateHostelReservation method for fileNo:{} ",this.getClass().getName(),hostelReservation.getFileNo());
		hostelDao.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(Long fileNo) {
		logger.info("{} : calling deleteHostelReservation method by passing fileNo:{} ",this.getClass().getName(),fileNo);
		hostelDao.deleteHostelReservation(fileNo);
	}



//RoomTypeDetail	
	

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		logger.info("{} : calling getRoomTypeDetail method by passing typeCode:{} ",this.getClass().getName(),typeCode);
		RoomTypeDetail roomTypeDetail = hostelDao.getRoomTypeDetail(typeCode);
		return roomTypeDetail;
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : calling addRoomTypeDetail method for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
		hostelDao.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : calling updateRoomTypeDetail method for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
		hostelDao.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {
		logger.info("{} : calling deleteRoomTypeDetail method by passing typeCode:{} ",this.getClass().getName(),typeCode);
		hostelDao.deleteRoomTypeDetail(typeCode);
	}

	
// HsotelAvailability	
	
	
	public List<HostelAvailability> getHostelAvailability() {
		logger.info("{} : calling getHostelAvailability method } ",this.getClass().getName());
		List<HostelAvailability>  h=null;
		h= hostelDao.getHostelAvailability();
		
		return h;
	}

@Override
public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
	logger.info("{} : calling addHostelAllocation method for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());	
	RoomAllocationDetail hostelAllocation = hostelAllocationAdmissionBean.getHostelAllocation();
	addHostelAllocation(hostelAllocation);
}

@Override
public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
	logger.info("{} : calling updateHostelAllocation method for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());	
	RoomAllocationDetail hostelAllocation = hostelAllocationAdmissionBean.getHostelAllocation();
	updateHostelAllocation(hostelAllocation);
}

}
