package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmissionBean;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportAllocationForStudent;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
@Component
public class TransportWorkflowManagerImpl implements TransportWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(TransportWorkflowManagerImpl.class);
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
		logger.info("{} : calling getAvailableTransport ",this.getClass().getName());
		return transportManager.getAvailableTransport();
	}

	public TransportAllocation getTransportAllocationDtl(Long fileNo) {
		logger.info("{} : calling getTransportAllocationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return transportManager.getTransportAllocationDtl(fileNo);
	}

	public void addTransportAllocationDtl(
			TransportAllocation transportAllocation) {
		logger.info("{} : calling addTransportAllocationDtl for fileNo:{} ",this.getClass().getName(), transportAllocation.getFileNo());
		transportManager.addTransportAllocationDtl(transportAllocation);
	}
//
//	public void updateTransportAllocationDtl(
//			TransportAllocation transportAllocation) {
//		logger.info("{} : calling updateTransportAllocationDtl for fileNo:{} ",this.getClass().getName(), transportAllocation.getFileNo());
//		transportManager.updateTransportAllocationDtl(transportAllocation);
//	}

	public void deleteTransportAllocationDtl(Long fileNo) {
		logger.info("{} : calling deleteTransportAllocationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		transportManager.deleteTransportAllocationDtl(fileNo);
	}

	public TransportReservation getTransportReservationDtl(Long fileNo) {
		logger.info("{} : calling getTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return transportManager.getTransportReservationDtl(fileNo);
	}

	public Long addTransportReservationDtl(
			TransportReservation transportReservation) {
		logger.info("{} : addTransportReservationDtl for fileNo:{} ",this.getClass().getName(), transportReservation.getFileNo());
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
		logger.info("{} : updateTransportReservationDtl for fileNo:{} ",this.getClass().getName(), transportReservation.getFileNo());
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
		logger.info("{} : deleteTransportReservationDtl by passing fileNo:{} ",this.getClass().getName(), fileNo);
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
		logger.info("{} : calling getVehicleDetail by passing vehicleId:{} ",this.getClass().getName(), vehicleId);
		return transportManager.getVehicleDetail(vehicleId);
	}

	public void addVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : calling addVehicleDetail for vehicleId:{} ",this.getClass().getName(), vehicleDetail.getVehicleId());
		transportManager.addVehicleDetail(vehicleDetail);
	}

	public void updateVehicleDetail(VehicleDetail vehicleDetail) {
		logger.info("{} : calling updateVehicleDetail for vehicleId:{} ",this.getClass().getName(), vehicleDetail.getVehicleId());
		transportManager.updateVehicleDetail(vehicleDetail);
	}

	public void deleteVehicleDetail(Long vehicleId) {
		logger.info("{} : calling deleteVehicleDetail by passing vehicleId:{} ",this.getClass().getName(), vehicleId);
		transportManager.deleteVehicleDetail(vehicleId);
	}

	@Override
	public TransportAllocationAdmissionBean getTransportAllocationAdmissiondtl(Long fileNo){
		logger.info("{} : getTransportAllocationAdmissiondtl for fileno:{} ",this.getClass().getName(), fileNo);	
        TransportAllocationAdmissionBean transportAllocationAdmissionBean = new TransportAllocationAdmissionBean();
        
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		transportAllocationAdmissionBean.setBasicInfo(basicInfo);
		
		TransportAllocation transportAllocation = getTransportAllocationDtl(fileNo);
		transportAllocationAdmissionBean.setTransportAllocation(transportAllocation);
		
		return transportAllocationAdmissionBean;
	}
	
	@Override
	public void addTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
		logger.info("{} : calling addTransportAllocationAdmissionDtl for Student:{} ",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+transportAllocationAdmissionBean.getBasicInfo().getLastName());		
		transportManager.addTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
	} 
	
//	@Override
//	public void updateTransportAllocationAdmissionDtl(TransportAllocationAdmissionBean transportAllocationAdmissionBean){
//		logger.info("{} : calling updateTransportAllocationAdmissionDtl for Student:{} ",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+transportAllocationAdmissionBean.getBasicInfo().getLastName());		
//		transportManager.updateTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
//	}

//	@Override
//	public TransportAllocationDtlForVehicle getCurrentAllocationByVehichleId(
//			Long vehicleId) {
//		logger.info("{} : calling getCurrentAllocationByVehichleId.  Vehicle Id:{} ",this.getClass().getName(), vehicleId);
//		return transportManager.getCurrentAllocationByVehichleId(vehicleId);
//	}

	@Override
	public TransportAllocation getActiveTransportAllocationDetail(Long fileNo) {
		logger.info("{} : calling getVehicleAllocatedDetail by passing  file no:{} ",this.getClass().getName(), fileNo);
		return transportManager.getActiveTransportAllocationDetail(fileNo);
	}

	@Override
	public List<TransportAllocation> getPreviousAllocatedDetail(Long fileNo) {
		logger.info("{} : calling getPreviousAllocatedDetail by passing  file no:{} ",this.getClass().getName(), fileNo);
		return transportManager.getPreviousAllocatedDetail(fileNo);
	}

	@Override
	public TransportAllocationForStudent getAllocationForStudent(Long fileNo) {
		TransportAllocationForStudent allocationForStudent = new TransportAllocationForStudent();
		
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		allocationForStudent.setBasicInfo(basicInfo);
		
		TransportAllocation activeAllocation = getActiveTransportAllocationDetail(fileNo);
		allocationForStudent.setActiveAllocation(activeAllocation);
		
		List<TransportAllocation> previousAllocation = getPreviousAllocatedDetail(fileNo);
		allocationForStudent.setPreviousAllocation(previousAllocation);
		
		return allocationForStudent;
	}
}
