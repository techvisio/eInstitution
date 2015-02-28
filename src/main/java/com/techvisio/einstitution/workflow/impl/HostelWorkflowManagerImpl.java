package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.manager.impl.FeeManagerImpl;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;

public class HostelWorkflowManagerImpl implements HostelWorkflowManager {

	AdmissionManager admissionManager = AdmissionManagerImpl.getInstance();
	HostelManager hostelManager=HostelManagerImpl.getInstance();
	FeeManager feeManager=FeeManagerImpl.getInstance();
	
	public List<HostelAvailability> getHostelAvailability() {
		return hostelManager.getHostelAvailability();
	}

	public HostelAllocation getHostelAllocation(String fileNo) {

		return hostelManager.getHostelAllocation(fileNo);
	}

	public void addHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.addHostelAllocation(hostelAllocation);
	}

	public void updateHostelAllocation(HostelAllocation hostelAllocation) {

		hostelManager.updateHostelAllocation(hostelAllocation);
	}

	public void deleteHostelAllocation(String fileNo) {

		hostelManager.deleteHostelAllocation(fileNo);
	}

	public HostelReservation getHostelReservation(String fileNo) {

		return hostelManager.getHostelReservation(fileNo);
	}

	public String addHostelReservation(HostelReservation hostelReservation) {

		String fileNo=hostelReservation.getFileNo();
		
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
		StudentFeeStaging stagingFee=new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setFeeHeadId(AppConstants.HOSTEL_FEE_ID);
		stagingFee.setAmount(reservedObject.getPrice());
		feeManager.addStudentFeeStaging(stagingFee);
		
		return fileNo;
	
	}

	public String updateHostelReservation(HostelReservation hostelReservation) {

             String fileNo=hostelReservation.getFileNo();
		
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
		StudentFeeStaging stagingFee=new StudentFeeStaging();
		stagingFee.setFileNo(fileNo);
		stagingFee.setFeeHeadId(AppConstants.HOSTEL_FEE_ID);
		stagingFee.setAmount(reservedObject.getPrice());
		feeManager.updateStudentFeeStaging(stagingFee);
		
		return fileNo;

		
	}

	public void deleteHostelReservation(String fileNo) {

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

}