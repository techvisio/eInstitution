package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.HostelReservation;

public interface HostelReservationManger {
	public HostelReservation getHostelReservation(String fileNo);
	public void addHostelReservation(HostelReservation hostelReservation);
	public void updateHostelReservation(HostelReservation hostelReservation);
	public void deleteHostelReservation(String fileNo);

}
