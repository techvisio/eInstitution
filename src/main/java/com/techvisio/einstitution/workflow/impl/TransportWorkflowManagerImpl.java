package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;
import com.techvisio.einstitution.manager.impl.TransportManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;

public class TransportWorkflowManagerImpl implements TransportWorkflowManager{

	AdmissionManager admissionManager = AdmissionManagerImpl.getInstance();
	FeeManager feeManager=FeeManagerImpl.getInstance();
	TransportManager transportManager = TransportManagerImpl.getInstance();
	
	public List<AvailableTransport> getAvailableTransport() {

		return transportManager.getAvailableTransport();
	}

	public TransportAllocation getTransportAllocationDtl(String fileNo) {

		return transportManager.getTransportAllocationDtl(fileNo);
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		transportManager.addTransportAllocationDtl(transportAllocation);
	}

	public void updateTransportAllocationDtl(
			TransportAllocation transportAllocation) {

		transportManager.updateTransportAllocationDtl(transportAllocation);
	}

	public void deleteTransportAllocationDtl(String fileNo) {

		transportManager.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(String fileNo) {

		return transportManager.getTransportReservationDtl(fileNo);
	}

	public String addTransportReservationDtl(
			TransportReservation transportReservation) {


        String fileNo=transportReservation.getFileNo();
		
		//if file No is missing create student
		if(fileNo == null ){
			StudentDetail newStudentDetail=new StudentDetail();
			fileNo=admissionManager.addStudentDtl(newStudentDetail);
		}
		
		//reserve a transport for student
		transportReservation.setFileNo(fileNo);
		transportManager.addTransportReservationDtl(transportReservation);
		
		TransportReservation reservedObject=transportManager.getTransportReservationDtl(fileNo);
		
		//create a staging fee entry
		StudentFeeStaging stagingFee=new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setFeeHeadId(AppConstants.TRANSPORT_FEE_ID);
		stagingFee.setAmount(reservedObject.getPrice());
		feeManager.addStudentFeeStaging(stagingFee);
		
		return fileNo;
	}

	public void updateTransportReservationDtl(
			TransportReservation transportReservation) {

		transportManager.updateTransportReservationDtl(transportReservation);
	}

	public void deleteTransportReservationDtl(String fileNo) {

		transportManager.deleteTransportReservationDtl(fileNo);
	}

	public VehicleDetail getVehicleDetail(Long vehicleId) {

		return transportManager.getVehicleDetail(vehicleId);
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {

		transportManager.addVehicleDetail(vehicleDetail);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {

		transportManager.updateVehicleDetail(vehicleDetail);
	}

	public void deleteVehicleDetail(Long vehicleId) {

		transportManager.deleteVehicleDetail(vehicleId);
	}

}
