package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Amenities;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Block;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.Floor;
import com.techvisio.einstitution.beans.MasterData;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.beans.VehicleType;
import com.techvisio.einstitution.beans.Wing;

@Component
public interface CacheManager {

	public List<MasterData> getBranchAsMasterdata();

	public List<MasterData> getCategoryAsMasterdata();

	public List<MasterData> getCourseAsMasterdata();

	public List<MasterData> getStateAsMasterdata();

	public List<MasterData> getQualificationAsMasterdata();

	public List<MasterData> getCounsellingBodyAsMasterdata();
	
	public List<MasterData> getQuotaCodeAsMasterdata();
	
	public List<MasterData> getSubjectAsMasterdata();
	
	public List<MasterData> getConsultantAsMasterdata();
	
	public List<MasterData> getFeeDiscountAsMasterdata();
	
	public List<MasterData> getSemesterAsMasterdata();

	FeeDiscountHead getFeeDiscountById(Long headId);

	void refreshCacheList(String entity);
	
	public List<MasterData> getCodeMappingAsMasterdata();

	Course getCourseById(Long courseId);

	Branch getBranchById(Long branchId);

	String getCodeMappingByName(String name);

	List<MasterData> getBatchAsMasterdata();
	
	public List<MasterData> getSessionAsMasterdata();

	public List<MasterData> getCentreAsMasterdata();

	public List<MasterData> getShiftAsMasterdata();
	
	public List<MasterData> getSectionAsMasterdata();

	public <T> List<T> getEntityList(String entity);

	Batch getBatchByBatchId(Long batchId);

	Centre getCentreByCentreId(Long centreId);

	Section getSectionBySectionId(Long sectionId);

	Session getSessionBySessionId(Long sessionId);

	Shift getShiftByShiftId(Long shiftId);

	CasteCategory getCategoryId(Long categoryId);

	Consultant getConsultantId(Long consultantId);

	CounsellingBody getCounsellingBodiesId(Long id);

	Qualification getQualificationId(Long id);

	QuotaCode getQuotaCodeId(Long id);

	State getStateId(Long id);

	Subject getSubjectId(Long id);

	List<MasterData> getWingAsMasterdata();

	List<MasterData> getFloorAsMasterdata();

	List<MasterData> getBlockAsMasterdata();

	Wing getWingByWingId(Long id);

	Floor getFloorByFloorId(Long id);

	Block getBlockByBlockId(Long id);

	RoomTypeDetail getroomDetailByRoomNo(String roomNo);

	List<RoomTypeDetail> getRoomNoAsMasterdata();

	VehicleDetail getVehicleDeatilByVehicleId(Long vehicleId);

	List<MasterData> getVehicleTypeIdAsMasterdata();

	VehicleType getVehicleTypeByTypeId(Long typeId);

	List<VehicleDetail> getVehicleDetailAsMasterdata();

	List<MasterData> getRoomTypeCodeAsMasterdata();

	RoomType getRoomTypeMasterByTypeCode(String typeCode);

	List<MasterData> getTransportRouteCodeAsMasterdata();

	List<Amenities> getAmenitiesAsMasterdata();

	Transport getTransportByRouteCode(String routeCode);

	Amenities getAmentiesByFeeId(Long feeId);
}
