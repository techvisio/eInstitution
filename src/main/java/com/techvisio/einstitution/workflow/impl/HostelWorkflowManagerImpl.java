package com.techvisio.einstitution.workflow.impl;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.workflow.HostelWorkflowManager;
@Component
public class HostelWorkflowManagerImpl implements HostelWorkflowManager {
//	private static CustomLogger logger=CustomLogger.getLogger(HostelWorkflowManagerImpl.class);
//	@Autowired
//	AdmissionWorkflowManager admissionWorkflowManager ;
//
//	@Autowired
//	AdmissionManager admissionManager ;
//
//	@Autowired
//	HostelManager hostelManager;
//
//	@Autowired
//	FeeManager feeManager;
//
//	@Autowired
//	CacheManager cacheManager;
//
//	public List<HostelAvailability> getHostelAvailability() {
//		logger.info("{} : calling getHostelAvailability ",this.getClass().getName());
//		return hostelManager.getHostelAvailability();
//	}
//
//	public RoomAllocation getHostelAllocation(Long fileNo) {
//		logger.info("{} : calling getHostelAllocation by passing fileNo:{} ",this.getClass().getName(), fileNo);
//		return hostelManager.getHostelAllocation(fileNo);
//	}
//
//	public void addHostelAllocation(RoomAllocation hostelAllocation) {
//		logger.info("{} : calling addHostelAllocation for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
//		hostelManager.addHostelAllocation(hostelAllocation);
//	}
//
//	//	public void updateHostelAllocation(RoomAllocationDetail hostelAllocation) {
//	//		logger.info("{} : calling updateHostelAllocation for fileNo:{} ",this.getClass().getName(), hostelAllocation.getFileNo());
//	//		hostelManager.updateHostelAllocation(hostelAllocation);
//	//	}
//
//	public void deleteHostelAllocation(Long fileNo) {
//		logger.info("{} : calling deleteHostelAllocation by passing fileNo:{} ",this.getClass().getName(), fileNo);
//		hostelManager.deleteHostelAllocation(fileNo);
//	}
//
//	public HostelReservation getHostelReservation(Long fileNo) {
//		logger.info("{} : calling getHostelReservation by passing fileNo:{} ",this.getClass().getName(), fileNo);
//		return hostelManager.getHostelReservation(fileNo);
//	}
//
//	public Long addHostelReservation(HostelReservation hostelReservation) {
//		logger.info("{} : addHostelReservation for fileNo:{} ",this.getClass().getName(), hostelReservation.getFileNo());
//		Long fileNo=hostelReservation.getFileNo();
//
//		//if file No is missing create student
//		if(fileNo == null ){
//			Student newStudentDetail=new Student();
//			fileNo=admissionManager.addStudentDtl(newStudentDetail);
//		}
//
//		//reserve a hostel for student
//		hostelReservation.setFileNo(fileNo);
//		hostelManager.addHostelReservation(hostelReservation);
//
//		HostelReservation reservedObject=hostelManager.getHostelReservation(fileNo);
//
//		//create a staging fee entry
//
//		FeeDiscountHead discountHead =  cacheManager.getFeeDiscountById(AppConstants.HOSTEL_FEE_ID);
//
//		StudentFeeStaging stagingFee=new StudentFeeStaging();
//		stagingFee.setFileNo(fileNo);
//		stagingFee.setDiscountHead(discountHead);
//		stagingFee.setAmount(reservedObject.getPrice());
//
//		feeManager.saveStudentFeeStaging(stagingFee);
//
//		return fileNo;
//
//	}
//
//	public Long updateHostelReservation(HostelReservation hostelReservation) {
//		logger.info("{} : updateHostelReservation for fileNo:{} ",this.getClass().getName(), hostelReservation.getFileNo());
//		Long fileNo=hostelReservation.getFileNo();
//
//		//if file No is missing create student
//		if(fileNo == null ){
//			Student newStudentDetail=new Student();
//			fileNo=admissionManager.addStudentDtl(newStudentDetail);
//		}
//
//		//updating hostel for student
//		hostelReservation.setFileNo(fileNo);
//		hostelManager.updateHostelReservation(hostelReservation);
//
//		HostelReservation reservedObject=hostelManager.getHostelReservation(fileNo);
//
//		//updating staging fee entry
//		FeeDiscountHead discountHead =  cacheManager.getFeeDiscountById(AppConstants.HOSTEL_FEE_ID);
//		StudentFeeStaging stagingFee=new StudentFeeStaging();
//		stagingFee.setFileNo(fileNo);
//		stagingFee.setDiscountHead(discountHead);;
//		stagingFee.setAmount(reservedObject.getPrice());
//
//		feeManager.saveStudentFeeStaging(stagingFee);
//
//		return fileNo;
//
//	}
//
//	public void deleteHostelReservation(Long fileNo) {
//		logger.info("{} : calling deleteHostelReservation by passing fileNo:{} ",this.getClass().getName(), fileNo);
//		hostelManager.deleteHostelReservation(fileNo);
//	}
//
//	public RoomTypeDetail getRoomTypeDetail(String typeCode) {
//		logger.info("{} : calling getRoomTypeDetail by passing typeCode:{} ",this.getClass().getName(), typeCode);
//		return hostelManager.getRoomTypeDetail(typeCode);
//	}
//
//	public void addRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
//		logger.info("{} : calling addRoomTypeDetail for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
//		hostelManager.addRoomTypeDetail(roomTypeDetail);
//	}
//
//	public void updateRoomTypeDetail(RoomTypeDetail roomTypeDetail) {
//		logger.info("{} : calling updateRoomTypeDetail for typeCode:{} ",this.getClass().getName(), roomTypeDetail.getTypeCode());
//		hostelManager.updateRoomTypeDetail(roomTypeDetail);
//	}
//
//	public void deleteRoomTypeDetail(String typeCode) {
//		logger.info("{} : calling deleteRoomTypeDetail by passing typeCode:{} ",this.getClass().getName(), typeCode);
//		hostelManager.deleteRoomTypeDetail(typeCode);
//	}
//
//	
//
//	@Override
//	public void saveRoomDetail(RoomAllocation newRoomAllocation){
//
//		RoomAllocation oldRoomAllocation = getActiveRoomAllocationDetail(newRoomAllocation.getFileNo());
//		HostelReservation hostelReservation = getReservationfromAllocation(newRoomAllocation);
//		if(oldRoomAllocation == null ){
//			 addHostelReservation(hostelReservation);
//		}
//		if(oldRoomAllocation!=null && !oldRoomAllocation.getRoomTypeDetail().getTypeCode().equals(newRoomAllocation.getRoomTypeDetail().getTypeCode()))
//		{
//			updateHostelReservation(hostelReservation);
//		}
//		hostelManager.saveAllocationDetails(newRoomAllocation,oldRoomAllocation);
//	}
//
//	private HostelReservation getReservationfromAllocation(
//			RoomAllocation newRoomAllocation) {
//		HostelReservation hostelReservation = new HostelReservation();
//		String typeCode=newRoomAllocation.getRoomTypeDetail().getTypeCode();
//		RoomType roomTypeMaster = cacheManager.getRoomTypeMasterByTypeCode(typeCode);
//		
//		hostelReservation.setPrice(roomTypeMaster.getPrice());
//		hostelReservation.setDescription(roomTypeMaster.getDescription());
//		hostelReservation.setFileNo(newRoomAllocation.getFileNo());
//		hostelReservation.setTypeCode(newRoomAllocation.getRoomTypeDetail().getTypeCode());
//		return hostelReservation;
//	}
//
//	@Override
//	public HostelAllocationAdmission getHostelAllocationAdmissiondtl(Long fileNo){
//		logger.info("{} :  getHostelAllocationAdmissiondtl for fileno:{} ",this.getClass().getName(), fileNo);	
//		HostelAllocationAdmission hostelAllocationAdmissionBean =  new HostelAllocationAdmission();
//
//		StudentBasicInfo basicInfo=admissionWorkflowManager.getStudentBsInfo(fileNo);
//		hostelAllocationAdmissionBean.setBasicInfo(basicInfo);
//
//		RoomAllocation hostelAllocation = getHostelAllocation(fileNo);
//		hostelAllocationAdmissionBean.setHostelAllocation(hostelAllocation);
//
//		return hostelAllocationAdmissionBean;
//	}
//
//	@Override
//	public void saveHostelAllocationAdmissionDtl(RoomAllocation roomAllocationDetail){
////		logger.info("{} : calling addHostelAllocation method for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());	
//
//		saveRoomDetail(roomAllocationDetail);
//
//	}
//
//	//	@Override
//	//	public void updateHostelAllocationAdmissionDtl(HostelAllocationAdmissionBean hostelAllocationAdmissionBean){
//	//		logger.info("{} : calling updateHostelAllocationAdmissionDtl for Student:{} ",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFirstName()+hostelAllocationAdmissionBean.getBasicInfo().getLastName());		
//	//		hostelManager.updateHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
//	//	}
//
//	@Override
//	public RoomAllocationDetailForRoom getCurrentAllocationByRoom(String roomNo) {
//
//		return hostelManager.getCurrentAllocationByRoom(roomNo);
//	}
//
//	@Override
//	public RoomAllocation getActiveRoomAllocationDetail(Long fileNo) {
//
//		return hostelManager.getActiveRoomAllocationDetail(fileNo);
//	}
//
//	@Override
//	public List<RoomAllocation> getPreviousAllocatedDetail(Long fileNo) {
//
//		return hostelManager.getPreviousAllocatedDetail(fileNo);
//	}
//
//	@Override
//	public RoomAllocationForStudent getAllocationForStudent(Long fileNo){
//
//		RoomAllocationForStudent allocationForStudent = new RoomAllocationForStudent();
//
//		RoomAllocation activeAllocationDetail = getActiveRoomAllocationDetail(fileNo);
//		allocationForStudent.setActiveAllocation(activeAllocationDetail);
//
//		List<RoomAllocation> previousAllocationDetails = getPreviousAllocatedDetail(fileNo);
//		allocationForStudent.setPreviousAllocation(previousAllocationDetails);
//
//		StudentBasicInfo basicInfo = admissionManager.getStudentBsInfo(fileNo);
//		allocationForStudent.setBasicInfo(basicInfo);
//
//		return allocationForStudent;
//	}


}
