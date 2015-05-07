package com.techvisio.einstitution.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmissionBean;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.CacheManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.manager.impl.TransportManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
@Component
public class TransportWorkflowManagerImpl implements TransportWorkflowManager {

	@Autowired
	AdmissionManager admissionManager;
	
	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager;
	
	@Autowired
	FeeManager feeManager;
	
	@Autowired
	TransportManager transportManager ;

	
	@Autowired
	CacheManager cacheManager;

	public List<AvailableTransport> getAvailableTransport() {

		return transportManager.getAvailableTransport();
	}

	public TransportAllocation getTransportAllocationDtl(Long fileNo) {

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

	public void deleteTransportAllocationDtl(Long fileNo) {

		transportManager.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {

		return transportManager.getTransportReservationDtl(fileNo);
	}

	public Long addTransportReservationDtl(
			TransportReservation transportReservation) {

		Long fileNo = transportReservation.getFileNo();

		// if file No is missing create student
		if (fileNo == null) {
			StudentDetail newStudentDetail = new StudentDetail();
			fileNo = admissionManager.addStudentDtl(newStudentDetail);
		}

		// reserve a transport for student
		transportReservation.setFileNo(fileNo);
		transportManager.addTransportReservationDtl(transportReservation);

		TransportReservation reservedObject = transportManager
				.getTransportReservationDtl(fileNo);

		// create a staging fee entry
		FeeDiscountHead discountHead = cacheManager
				.getFeeDiscountById(AppConstants.TRANSPORT_FEE_ID);

		StudentFeeStaging stagingFee = new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setDiscountHead(discountHead);
		stagingFee.setAmount(reservedObject.getPrice());
		
		feeManager.saveStudentFeeStaging(stagingFee);

		return fileNo;
	}

	public Long updateTransportReservationDtl(
			TransportReservation transportReservation) {

		Long fileNo = transportReservation.getFileNo();

		if (fileNo == null) {
			StudentDetail newStudentDetail = new StudentDetail();
			fileNo = admissionManager.addStudentDtl(newStudentDetail);
		}

		// updating reserved transport for student
		transportReservation.setFileNo(fileNo);
		transportManager.updateTransportReservationDtl(transportReservation);

		TransportReservation reservedObject = transportManager
				.getTransportReservationDtl(fileNo);

		// updating fee staging entry
		FeeDiscountHead discountHead = cacheManager
				.getFeeDiscountById(AppConstants.TRANSPORT_FEE_ID);

		StudentFeeStaging stagingFee = new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setDiscountHead(discountHead);
		stagingFee.setAmount(reservedObject.getPrice());
		
		feeManager.saveStudentFeeStaging(stagingFee);

		return fileNo;
	}

	public void deleteTransportReservationDtl(Long fileNo) {

		TransportReservation reservedObject = transportManager
				.getTransportReservationDtl(fileNo);
		FeeDiscountHead discountHead = cacheManager
				.getFeeDiscountById(AppConstants.TRANSPORT_FEE_ID);

		StudentFeeStaging stagingFee = new StudentFeeStaging();
		stagingFee.setFileNo(reservedObject.getFileNo());
		stagingFee.setDiscountHead(discountHead);

		feeManager.deleteStudentFeeStagingbyfileNo(stagingFee);
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

	@Override
	public TransportAllocationAdmissionBean getTransportAllocationAdmissiondtl(Long fileNo){
	
        TransportAllocationAdmissionBean transportAllocationAdmissionBean = new TransportAllocationAdmissionBean();
        
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		transportAllocationAdmissionBean.setBasicInfo(basicInfo);
		
		TransportAllocation transportAllocation = getTransportAllocationDtl(fileNo);
		transportAllocationAdmissionBean.setTransportAllocation(transportAllocation);
		
		return transportAllocationAdmissionBean;
	}
	
	@Override
	public void addTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
		
		transportManager.addTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
	} 
	
	@Override
	public void updateTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
		
		transportManager.updateTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
	}
}
