package com.techvisio.einstitution.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
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
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
@Component
public class HostelWorkflowManagerImpl implements HostelWorkflowManager {

	@Autowired
	AdmissionManager admissionManager;
	
	@Autowired
	HostelManager hostelManager;
	
	@Autowired
	FeeManager feeManager;
	
	@Autowired
	CacheManager cacheManager;
	
	public List<HostelAvailability> getHostelAvailability() {
		return hostelManager.getHostelAvailability();
	}

	public HostelAllocation getHostelAllocation(Long fileNo) {

		return hostelManager.getHostelAllocation(fileNo);
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(Long fileNo) {

		hostelManager.deleteHostelAllocation(fileNo);
	}

	public HostelReservation getHostelReservation(Long fileNo) {

		return hostelManager.getHostelReservation(fileNo);
	}

	public Long addHostelReservation(HostelReservation hostelReservation) {

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

		hostelManager.deleteHostelReservation(fileNo);
	}

	public RoomTypeDetail getRoomTypeDetail(String typeCode) {

		return hostelManager.getRoomTypeDetail(typeCode);
	}

	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		hostelManager.addRoomTypeDetail(roomTypeDetail);
	}

	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {

		hostelManager.updateRoomTypeDetail(roomTypeDetail);
	}

	public void deleteRoomTypeDetail(String typeCode) {

		hostelManager.deleteRoomTypeDetail(typeCode);
	}

	@Override
	public HostelAllocationAdmissionBean getHostelAllocationAdmissiondtl(Long fileNo){
	
		AdmissionWorkflowManager admissionWorkflowManager = new AdmissionWorkflowManagerImpl();
        HostelAllocationAdmissionBean hostelAllocationAdmissionBean =  new HostelAllocationAdmissionBean();
        
		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
		hostelAllocationAdmissionBean.setBasicInfo(basicInfo);
		
		HostelAllocation hostelAllocation = getHostelAllocation(fileNo);
		hostelAllocationAdmissionBean.setHostelAllocation(hostelAllocation);
		
		return hostelAllocationAdmissionBean;
	}
	
	@Override
	public void addHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
		
		hostelManager.addHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
	} 
	
	@Override
	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
		
		hostelManager.updateHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
	}
}