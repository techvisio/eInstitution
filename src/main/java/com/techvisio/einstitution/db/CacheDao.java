package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Amenities;
import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Block;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.CodeMapping;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.Floor;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.RoomType;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Semester;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.beans.VehicleType;
import com.techvisio.einstitution.beans.Wing;

@Component
public interface CacheDao {

	public List<State> getState();

	public List<Branch> getBranch();

	public List<Course> getCourse();

	public List<CasteCategory> getCatagory();

	public List<CounsellingBody> getCounsellingBody();

	public List<Subject> getSubject();

	public List<Qualification> getQualification();

	public List<QuotaCode> getQuotaCode();

	public List<Consultant> getConsultant();
	
	public List<FeeDiscountHead> getFeeDiscountHeadMaster();
	
	public List<Semester> getSemester();

	public List<CodeMapping> getCodeMapping();

	public List<Batch> getBatch();
	
	public List<Session> getSession();
		
	public List<Centre> getCentre();
	
	public List<Shift> getShift();
	
	public List<Section> getSection();
	
	public List<Wing> getWing();
	
	public List<Floor> getFloor();
	
	public List<Block> getBlock();
	
	public List<RoomTypeDetail> getRoomTypeDetail();
	
	public List<VehicleDetail> getVehicleDetail();

	List<VehicleType> getVehicleTypes();
	
	public List<RoomType> getRoomType();
	
	public List<Transport> getTransport();

	public List<Amenities> getAmenitiesCharges();
}
