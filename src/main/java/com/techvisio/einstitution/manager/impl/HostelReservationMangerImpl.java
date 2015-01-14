package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.db.HostelDao;
import com.techvisio.einstitution.manager.HostelReservationManger;
import com.techvisio.einstitution.util.ContextProvider;

public class HostelReservationMangerImpl implements HostelReservationManger{
	HostelDao hostelDao = ContextProvider.getContext().getBean(HostelDao.class);
	
	public HostelReservation getHostelReservation(String fileNo) {
		HostelReservation hostelReservation = hostelDao.getHostelReservation("fileNo");
		return hostelReservation;
	}

	public void addHostelReservation(HostelReservation hostelReservation) {
		hostelDao.addHostelReservation(hostelReservation);
	}

	public void updateHostelReservation(HostelReservation hostelReservation) {
		hostelDao.updateHostelReservation(hostelReservation);
	}

	public void deleteHostelReservation(String fileNo) {
		hostelDao.deleteHostelReservation("fileNo");
	}

}
