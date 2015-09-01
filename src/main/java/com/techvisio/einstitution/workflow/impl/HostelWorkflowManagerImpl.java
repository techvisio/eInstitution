package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
@Transactional
@Component
public class HostelWorkflowManagerImpl implements HostelWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(HostelWorkflowManagerImpl.class);

	@Autowired
	HostelManager hostelManager;
	
	@Autowired
	CacheManager cacheManager;

	@Override
	public List<HostelAvailability> getHostelAvailability() {
		logger.info("{} : calling getHostelAvailability method } ",this.getClass().getName());
		List<HostelAvailability>  hostelAvailabilities=null;
		hostelAvailabilities=hostelManager.getHostelAvailability();
		
		return hostelAvailabilities;
	}
	
	@Override
	public HostelReservation getHostelReservation(Long fileNo) {
		logger.info("{} : calling getHostelReservation method by passing fileNo:{} ",this.getClass().getName(), fileNo);
		HostelReservation hostelReservation = hostelManager.getHostelReservation(fileNo);
		return hostelReservation;	}

	@Override
	public void saveHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : calling addHostelReservation method for fileNo:{} ",this.getClass().getName(),hostelReservation.getFileNo());
		RoomType roomType = cacheManager.getRoomTypeMasterByTypeCode(hostelReservation.getTypeCode());
		hostelReservation.setPrice(roomType.getPrice());
		hostelReservation.setDescription(roomType.getDescription());
		hostelManager.saveHostelReservation(hostelReservation);
	}

	@Override
	public void deleteHostelReservation(Long fileNo) {
		logger.info("{} : calling deleteHostelReservation method by passing fileNo:{} ",this.getClass().getName(),fileNo);
		hostelManager.deleteHostelReservation(fileNo);
	}

}
