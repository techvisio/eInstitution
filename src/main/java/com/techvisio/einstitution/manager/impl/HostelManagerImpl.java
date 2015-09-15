package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class HostelManagerImpl implements HostelManager {
	private static CustomLogger logger = CustomLogger.getLogger(HostelManagerImpl.class);	
	
	@Autowired
	HostelDao hostelDao ;
	
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

	@Override
	public List<HostelAvailability> getHostelAvailability() {
		logger.info("{} : calling getHostelAvailability method } ",this.getClass().getName());
		List<HostelAvailability>  hostelAvailabilities=null;
		hostelAvailabilities=hostelDao.getHostelAvailability();
		
		return hostelAvailabilities;
	}
	
	@Override
	public HostelReservation getHostelReservation(Long fileNo) {
		logger.info("{} : calling getHostelReservation method by passing fileNo:{} ",this.getClass().getName(), fileNo);
		HostelReservation hostelReservation = hostelDao.getHostelReservation(fileNo);
		return hostelReservation;	}

	@Override
	public void saveHostelReservation(HostelReservation hostelReservation, Long fileNo) {
		logger.info("{} : calling addHostelReservation method for fileNo:{} ",this.getClass().getName(),hostelReservation.getFileNo());
		hostelDao.saveHostelReservation(hostelReservation, fileNo);
	}

	@Override
	public void deleteHostelReservation(Long fileNo) {
		logger.info("{} : calling deleteHostelReservation method by passing fileNo:{} ",this.getClass().getName(),fileNo);
		hostelDao.deleteHostelReservation(fileNo);
	}

	@Override
	public List<StudentBasicInfo> getStudentDtlBySearchCriteria(
			SearchCriteria searchCriteria) {
		
		List<StudentBasicInfo> basicInfos = hostelDao.getStudentDtlBySearchCriteria(searchCriteria);
		
		return basicInfos;
	}
	
	@Override
	public StudentBasicInfo getStudentBsInfo(Long fileNo) {
		
		StudentBasicInfo basicInfo = hostelDao.getStudentBsInfo(fileNo);
		return basicInfo;
	}

	@Override
	public RoomAllocation getRoomAllocation(Long fileNo) {
	    RoomAllocation allocation = hostelDao.getActiveRoomAllocation(fileNo);
		return allocation;
	}

	@Override
	public void saveRoomAllocation(RoomAllocation roomAllocation, Long fileNo) {
        hostelDao.saveRoomAllocation(roomAllocation, fileNo);		
	}

	@Override
	public void deleteRoomAllocation(Long fileNo) {
		hostelDao.deleteRoomAllocation(fileNo);
	}
	
	@Override
	public List<RoomTypeDetail> getAvailableRooms(){
		List<RoomTypeDetail> roomTypeDetails = hostelDao.getAvailableRooms();
		return roomTypeDetails;
	}
}
