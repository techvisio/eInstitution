package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
@Component
public class TransportWorkflowManagerImpl implements TransportWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(TransportWorkflowManagerImpl.class);

	@Autowired
	TransportManager transportManager ;

	public List<AvailableTransport> getAvailableTransport() {
		logger.info("{} : calling getAvailableTransport ",this.getClass().getName());
		return transportManager.getAvailableTransport();
	}
	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling getTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return transportManager.getTransportReservationDtl(fileNo);
	}

	public void saveTransportReservationDtl(TransportReservation transportReservation) {
         transportManager.saveTransportReservationDtl(transportReservation);		
	}

	public void deleteTransportReservationDtl(Long fileNo) {
		logger.info("{} : deleteTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		transportManager.deleteTransportReservationDtl(fileNo);
	}

}
