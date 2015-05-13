package com.techvisio.einstitution.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.CacheManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
@Component
public class HostelWorkflowManagerImpl implements HostelWorkflowManager {
	private static CustomLogger logger=CustomLogger.getLogger(HostelWorkflowManagerImpl.class);
	@Autowired
	AdmissionWorkflowManager admissionWorkflowManager ;

	@Autowired
	AdmissionManager admissionManager ;

	@Autowired
	HostelManager hostelManager;

	@Autowired
	FeeManager feeManager;

	@Autowired
	CacheManager cacheManager;

	public List<HostelAvailability> getHostelAvailability() {
		logger.info("{} : calling getHostelAvailability ",this.getClass().getName());
		return hostelManager.getHostelAvailability();
	}

	public RoomAllocationDetail getHostelAllocation(Long fileNo) {
		logger.info("{} : calling getHostelAllocation by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return hostelManager.getHostelAllocation(fileNo);
	}

	public void addHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : calling addHostelAllocation for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
		hostelManager.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation) {
		logger.info("{} : calling updateHostelAllocation for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
		hostelManager.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(Long fileNo) {
		logger.info("{} : calling deleteHostelAllocation by passing fileNo:{} ",this.getClass().getName(), fileNo);
		hostelManager.deleteHostelAllocation(fileNo);
	}

	public HostelReservation getHostelReservation(Long fileNo) {
		logger.info("{} : calling getHostelReservation by passing fileNo:{} ",this.getClass().getName(), fileNo);
		return hostelManager.getHostelReservation(fileNo);
	}

	public Long addHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : addHostelReservation for fileNo:{} ",this.getClass().getName(), hostelReservation.getFileNo());
		Long fileNo=hostelReservation.getFileNo();

		//if file No is missing create student
		if(fileNo == null ){
			StudentDetail newStudentDetail=new StudentDetail();
			fileNo=admissionManager.addStudentDtl(newStudentDetail);
		}

		//reserve a hostel for student
		hostelReservation.setFileNo(fileNo);
		hostelManager.addHostelReservation(hostelReservation);

		HostelReservation reservedObject=hostelManager.getHostelReservation(fileNo);

		//create a staging fee entry

		FeeDiscountHead discountHead =  cacheManager.getFeeDiscountById(AppConstants.HOSTEL_FEE_ID);

		StudentFeeStaging stagingFee=new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setDiscountHead(discountHead);
		stagingFee.setAmount(reservedObject.getPrice());

		feeManager.saveStudentFeeStaging(stagingFee);

		return fileNo;

	}

	public Long updateHostelReservation(HostelReservation hostelReservation) {
		logger.info("{} : updateHostelReservation for fileNo:{} ",this.getClass().getName(), hostelReservation.getFileNo());
		Long fileNo=hostelReservation.getFileNo();

		//if file No is missing create student
		if(fileNo == null ){
			StudentDetail newStudentDetail=new StudentDetail();
			fileNo=admissionManager.addStudentDtl(newStudentDetail);
		}

		//updating hostel for student
		hostelReservation.setFileNo(fileNo);
		hostelManager.updateHostelReservation(hostelReservation);

		HostelReservation reservedObject=hostelManager.getHostelReservation(fileNo);

		//updating staging fee entry
		FeeDiscountHead discountHead =  cacheManager.getFeeDiscountById(AppConstants.HOSTEL_FEE_ID);
		StudentFeeStaging stagingFee=new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setDiscountHead(discountHead);;
		stagingFee.setAmount(reservedObject.getPrice());

		feeManager.saveStudentFeeStaging(stagingFee);

		return fileNo;

	}

	public void deleteHostelReservation(Long fileNo) {
		logger.info("{} : calling deleteHostelReservation by passing fileNo:{} ",this.getClass().getName(), fileNo);
		hostelManager.deleteHostelReservation(fileNo);
	}

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
		logger.info("{} : calling getRoomTypeDetail by passing typeCode:{} ",this.getClass().getName(), typeCode);
		return hostelManager.getRoomTypeDetail(typeCode);
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : calling addRoomTypeDetail for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
		hostelManager.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
		logger.info("{} : calling updateRoomTypeDetail for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
		hostelManager.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {
		logger.info("{} : calling deleteRoomTypeDetail by passing typeCode:{} ",this.getClass().getName(), typeCode);
		hostelManager.deleteRoomTypeDetail(typeCode);
	}

	@Override
	public HostelAllocationAdmissionBean getHostelAllocationAdmissiondtl(Long fileNo){
		logger.info("{} :  getHostelAllocationAdmissiondtl for fileno:{} ",this.getClass().getName(), fileNo);	
		HostelAllocationAdmissionBean hostelAllocationAdmissionBean =  new HostelAllocationAdmissionBean();

		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		hostelAllocationAdmissionBean.setBasicInfo(basicInfo);

		RoomAllocationDetail hostelAllocation = getHostelAllocation(fileNo);
		hostelAllocationAdmissionBean.setHostelAllocation(hostelAllocation);

		return hostelAllocationAdmissionBean;
	}



	@Override
	public void saveRoomDetail(RoomAllocationDetail roomAllocationDetail) {

		hostelManager.saveRoomDetail(roomAllocationDetail);
	}


	@Override
	public void saveHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
		logger.info("{} : calling addHostelAllocationAdmissionDtl for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());		

		hostelManager.addHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
	} 

	@Override
	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
		logger.info("{} : calling updateHostelAllocationAdmissionDtl for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());		
		hostelManager.updateHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
	}

	@Override
	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo) {

		return hostelManager.getCurrentAllocationByRoom(roomNo);
	}

	@Override
	public RoomAllocationDetail getRoomAllocatedDetailForStudent(Long fileNo) {

		return hostelManager.getRoomAllocatedDetailForStudent(fileNo);
	}

	@Override
	public List<RoomAllocationDetail> getPreviousAllocatedDetail(Long fileNo) {

		return hostelManager.getPreviousAllocatedDetail(fileNo);
	}

	@Override
	public RoomAllocationForStudent getAllocationForStudent(Long fileNo){

		RoomAllocationForStudent allocationForStudent = new RoomAllocationForStudent();

		RoomAllocationDetail activeAllocationDetail = getRoomAllocatedDetailForStudent(fileNo);
		allocationForStudent.setActiveAllocation(activeAllocationDetail);

		List<RoomAllocationDetail> previousAllocationDetails = getPreviousAllocatedDetail(fileNo);
		allocationForStudent.setPreviousAllocation(previousAllocationDetails);

		StudentBasicInfo basicInfo = admissionManager.getStudentBsInfo(fileNo);
		allocationForStudent.setBasicInfo(basicInfo);

		return allocationForStudent;
	}


}
