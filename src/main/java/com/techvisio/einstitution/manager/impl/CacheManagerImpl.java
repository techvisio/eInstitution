package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Block;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.CodeMapping;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.Floor;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.RoomTypeMaster;
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
import com.techvisio.einstitution.controller.MasterDataService;
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.db.impl.CacheDaoImpl;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class CacheManagerImpl implements CacheManager {
	private static CustomLogger logger = CustomLogger.getLogger(CacheManagerImpl.class);
	@Autowired
	CacheDao cacheDao;


	public void setCacheDao(CacheDaoImpl cacheDao) {
		this.cacheDao = cacheDao;
	}

	@Autowired
	public CacheManagerImpl(CacheDao cacheDao){
		this.cacheDao = cacheDao;
		builtEntityListCache();
	}
	
	public CacheManagerImpl(){
	}
	
	private static Map<String,List> entityListMap=new HashMap<String, List>();
	private static Map<Long,FeeDiscountHead> feeDetailMap = new HashMap<Long, FeeDiscountHead>();
	private static Map<Long, Branch> branchMap = new HashMap<Long, Branch>();
	private static Map<String,String> codeMap=new HashMap<String, String>();
	private static Map<Long,Course> courseMap=new HashMap<Long, Course>();
	private static Map<Long,Batch> batchMap = new HashMap<Long, Batch>();
	private static Map<Long,Centre> centreMap = new HashMap<Long, Centre>();
	private static Map<Long,Section> sectionMap = new HashMap<Long, Section>();
	private static Map<Long,Session> sessionMap = new HashMap<Long, Session>();
	private static Map<Long,Shift> shiftMap = new HashMap<Long, Shift>();
	private static Map<Long, CasteCategory> categoryMap = new HashMap<Long, CasteCategory>();
	private static Map<Long, Consultant> consultantMap = new HashMap<Long, Consultant>();
	private static Map<Long, CounsellingBody> counsellingMap = new HashMap<Long, CounsellingBody>();
	private static Map<Long, Qualification> qualificationMap = new HashMap<Long, Qualification>();
	private static Map<Long, QuotaCode> quotaCodeMap = new HashMap<Long, QuotaCode>();
	private static Map<Long, State> stateMap = new HashMap<Long, State>();
	private static Map<Long, Subject> subjectMap = new HashMap<Long, Subject>();
	private static Map<Long, Wing> wingMap = new HashMap<Long, Wing>();	
	private static Map<Long, Floor> floorMap = new HashMap<Long, Floor>();
	private static Map<Long, Block> blockMap = new HashMap<Long, Block>();
	private static Map<String, RoomTypeDetail> roomDetailMap = new HashMap<String, RoomTypeDetail>();
	private static Map<Long, VehicleDetail> vehicleDetailMap  = new HashMap<Long, VehicleDetail>();
	private static Map<Long, VehicleType> vehicleTypeMap = new HashMap<Long, VehicleType>();
	private static Map<String, RoomTypeMaster> roomTypeMap = new HashMap<String, RoomTypeMaster>();
	private static Map<String, Transport> transportMap = new HashMap<String, Transport>();
	
	
	@SuppressWarnings("unchecked")
	public synchronized List<Branch> getBranchs(){
		logger.info("{} : Mapping work for get branches ",this.getClass().getName());

		if(entityListMap.get(AppConstants.BRANCH) == null||entityListMap.get(AppConstants.BRANCH).size()==0){
			refreshCacheList(AppConstants.BRANCH);
		}
		return (List<Branch>) entityListMap.get(AppConstants.BRANCH);
	}


	public List<MasterDataBean> getBranchAsMasterdata(){
		logger.info("{} : Get branch as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Branch branch:getBranchs()){
			MasterDataBean bean=new MasterDataBean(branch.getId().toString(), branch.getBranchName(), branch.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}


	@SuppressWarnings("unchecked")
	public synchronized  List<Course> getCourses() {
		logger.info("{} : Mapping work for get courses ",this.getClass().getName());
		if(entityListMap.get(AppConstants.COURSE) == null||entityListMap.get(AppConstants.COURSE).size()==0){
			refreshCacheList(AppConstants.COURSE);
		}

		return (List<Course>)entityListMap.get(AppConstants.COURSE);
	}

	public List<MasterDataBean> getCourseAsMasterdata(){
		logger.info("{} : Get course as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Course course:getCourses()){
			MasterDataBean bean=new MasterDataBean(course.getId().toString(), course.getCourse());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<CasteCategory> getCategories() {
		logger.info("{} : Mapping work for get categories ",this.getClass().getName());
		if(entityListMap.get(AppConstants.CATEGORY) == null||entityListMap.get(AppConstants.CATEGORY).size()==0){
			refreshCacheList(AppConstants.CATEGORY);
		}

		return (List<CasteCategory>)entityListMap.get(AppConstants.CATEGORY);
	}

	public List<MasterDataBean> getCategoryAsMasterdata(){
		logger.info("{} : Get category as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CasteCategory category:getCategories()){
			MasterDataBean bean=new MasterDataBean(category.getId().toString(), category.getCategoryName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<CounsellingBody> getCounsellingBodies() {
		logger.info("{} : Mapping work for get counsellingBodies ",this.getClass().getName());
		if(entityListMap.get(AppConstants.COUNSELLING) == null||entityListMap.get(AppConstants.COUNSELLING).size()==0){
			refreshCacheList(AppConstants.COUNSELLING);
		}
		return (List<CounsellingBody>)entityListMap.get(AppConstants.COUNSELLING);
	}

	public List<MasterDataBean> getCounsellingBodyAsMasterdata(){
		logger.info("{} : Get counsellingBody as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CounsellingBody body:getCounsellingBodies()){
			MasterDataBean bean=new MasterDataBean(body.getId().toString(), body.getCousellingBody());
			masterData.add(bean);
		}
		return masterData;
	}


	@SuppressWarnings("unchecked")
	public synchronized  List<Qualification> getQualifications() {
		logger.info("{} : Mapping work for get qualifications ",this.getClass().getName());
		if(entityListMap.get(AppConstants.QUALIFICATION) == null||entityListMap.get(AppConstants.QUALIFICATION).size()==0){
			refreshCacheList(AppConstants.QUALIFICATION);
		}
		return (List<Qualification>)entityListMap.get(AppConstants.QUALIFICATION);
	}

	public List<MasterDataBean> getQualificationAsMasterdata(){
		logger.info("{} : Get qualification as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Qualification qualification:getQualifications()){
			MasterDataBean bean=new MasterDataBean(qualification.getId().toString(), qualification.getQulaifyingExam());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<QuotaCode> getQuotaCodes() {
		logger.info("{} : Mapping work for get quota codes ",this.getClass().getName());
		if(entityListMap.get(AppConstants.QUOTACODE) == null||entityListMap.get(AppConstants.QUOTACODE).size()==0){
			refreshCacheList(AppConstants.QUOTACODE);
		}
		return (List<QuotaCode>)entityListMap.get(AppConstants.QUOTACODE);
	}

	public List<MasterDataBean> getQuotaCodeAsMasterdata(){
		logger.info("{} : Get quota code as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(QuotaCode quotaCode : getQuotaCodes()){
			MasterDataBean bean=new MasterDataBean(quotaCode.getId().toString(), quotaCode.getCode());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<State> getStates() {
		logger.info("{} : Mapping work for get states ",this.getClass().getName());
		if(entityListMap.get(AppConstants.STATE) == null||entityListMap.get(AppConstants.STATE).size()==0){
			refreshCacheList(AppConstants.STATE);
		}
		return (List<State>)entityListMap.get(AppConstants.STATE);
	}

	public List<MasterDataBean> getStateAsMasterdata(){
		logger.info("{} : Get state code as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(State state : getStates()){
			MasterDataBean bean=new MasterDataBean(state.getId().toString(), state.getStateName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<Consultant> getConsultant() {
		logger.info("{} : Mapping work for get consultant ",this.getClass().getName());
		if(entityListMap.get(AppConstants.CONSULTANT) == null||entityListMap.get(AppConstants.CONSULTANT).size()==0){
			refreshCacheList(AppConstants.CONSULTANT);
		}
		return (List<Consultant>)entityListMap.get(AppConstants.CONSULTANT);
	}


	public List<MasterDataBean> getConsultantAsMasterdata(){
		logger.info("{} : Get consultant as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Consultant consultant : getConsultant()){
			MasterDataBean bean=new MasterDataBean(consultant.getConsultantId().toString(), consultant.getName());
			masterData.add(bean);
		}
		return masterData;


	}

	@SuppressWarnings("unchecked")
	public synchronized  List<Subject> getSubjects() {
		logger.info("{} : Mapping work for get subjects ",this.getClass().getName());
		if(entityListMap.get(AppConstants.SUBJECT) == null||entityListMap.get(AppConstants.SUBJECT).size()==0){
			refreshCacheList(AppConstants.SUBJECT);
		}
		return (List<Subject>)entityListMap.get(AppConstants.SUBJECT);
	}


	public List<MasterDataBean> getSubjectAsMasterdata(){
		logger.info("{} : Get subject as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Subject subject : getSubjects()){
			MasterDataBean bean=new MasterDataBean(subject.getId().toString(), subject.getSubjectName());
			masterData.add(bean);
		}
		return masterData;
	}	


	@SuppressWarnings("unchecked")
	public synchronized  List<FeeDiscountHead> getFeeDiscountHeads() {
		logger.info("{} : Mapping work for get fee discount heads ",this.getClass().getName());
		if(entityListMap.get(AppConstants.FEEHEAD) == null||entityListMap.get(AppConstants.FEEHEAD).size()==0){
			refreshCacheList(AppConstants.FEEHEAD);
		}
		return (List<FeeDiscountHead>)entityListMap.get(AppConstants.FEEHEAD);
	}


	public List<MasterDataBean> getFeeDiscountAsMasterdata(){
		logger.info("{} : Get fee discount as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(FeeDiscountHead feeDiscountHead : getFeeDiscountHeads()){
			MasterDataBean bean=new MasterDataBean(feeDiscountHead.getHeadId().toString(), feeDiscountHead.getHead(), feeDiscountHead.getTransactionType());
			masterData.add(bean);
		}
		return masterData;
	}	


	@SuppressWarnings("unchecked")
	public synchronized  List<Semester> getSemester() {
		logger.info("{} : Mapping work for get semesters ",this.getClass().getName());
		if(entityListMap.get(AppConstants.SEMESTER) == null||entityListMap.get(AppConstants.SEMESTER).size()==0){
			refreshCacheList(AppConstants.SEMESTER);
		}
		return (List<Semester>)entityListMap.get(AppConstants.SEMESTER);
	}


	public List<MasterDataBean> getSemesterAsMasterdata(){
		logger.info("{} : Get semester as master data",this.getClass().getName());	
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Semester semester : getSemester()){
			MasterDataBean bean=new MasterDataBean(semester.getId().toString(), semester.getSemester(),semester.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}	

	@SuppressWarnings("unchecked")
	public synchronized  List<CodeMapping> getCodeMapping() {
		logger.info("{} : Mapping work for get code mapping ",this.getClass().getName());
		if(entityListMap.get(AppConstants.CODE_MAP) == null||entityListMap.get(AppConstants.CODE_MAP).size()==0){
			refreshCacheList(AppConstants.CODE_MAP);
		}
		return (List<CodeMapping>)entityListMap.get(AppConstants.CODE_MAP);
	}


	public List<MasterDataBean> getCodeMappingAsMasterdata(){
		logger.info("{} : Get code mapping as master data",this.getClass().getName());
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CodeMapping codeMapping : getCodeMapping()){
			MasterDataBean bean=new MasterDataBean(codeMapping.getName(), codeMapping.getName());
			masterData.add(bean);
		}
		return masterData;
	}	
	
	@SuppressWarnings("unchecked")
	public synchronized List<Batch> getBatch(){
		logger.info("{} : Mapping work for get batches ",this.getClass().getName());
		if(entityListMap.get(AppConstants.BATCH) == null || entityListMap.get(AppConstants.BATCH).size() == 0){
			refreshCacheList(AppConstants.BATCH);
		}
		return (List<Batch>)entityListMap.get(AppConstants.BATCH);
	}
	
	
	@Override
	public List<MasterDataBean> getBatchAsMasterdata() {
		logger.info("{} : Get batch as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Batch batch : getBatch()){
			MasterDataBean bean = new MasterDataBean(batch.getBatchId().toString(), batch.getBatch(), batch.getCourseId().toString());
			masterData.add(bean);
		}
		
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Session> getSession(){
		logger.info("{} : Mapping work for get sessions ",this.getClass().getName());
		if(entityListMap.get(AppConstants.SESSION) == null || entityListMap.get(AppConstants.SESSION).size() == 0){
			refreshCacheList(AppConstants.SESSION);
		}
		return (List<Session>)entityListMap.get(AppConstants.SESSION);
	}
	
	@Override
	public List<MasterDataBean> getSessionAsMasterdata() {
		logger.info("{} : Get session as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Session session : getSession()){
			MasterDataBean bean = new MasterDataBean(session.getSessionId().toString(), session.getSession(), session.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Centre> getCentre(){
		logger.info("{} : Mapping work for get centres ",this.getClass().getName());
		if(entityListMap.get(AppConstants.CENTRE) == null || entityListMap.get(AppConstants.CENTRE).size() == 0){
			refreshCacheList(AppConstants.CENTRE);
		}
	return (List<Centre>)entityListMap.get(AppConstants.CENTRE);
	}
	
	@Override
	public List<MasterDataBean> getCentreAsMasterdata() {
		logger.info("{} : Get centre as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Centre centre : getCentre()){
			MasterDataBean bean = new MasterDataBean(centre.getCentreId().toString(), centre.getCentreName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Shift> getShift(){
		logger.info("{} : Mapping work for get shifts ",this.getClass().getName());
		if(entityListMap.get(AppConstants.SHIFT) == null || entityListMap.get(AppConstants.SHIFT).size() == 0){
			refreshCacheList(AppConstants.SHIFT);
		}
	return (List<Shift>)entityListMap.get(AppConstants.SHIFT);
	}
	
	@Override
	public List<MasterDataBean> getShiftAsMasterdata() {
		logger.info("{} : Get shift as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Shift shift : getShift()){
			MasterDataBean bean = new MasterDataBean(shift.getShiftId().toString(), shift.getShiftName());
			masterData.add(bean);
		}
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Section> getSection(){
		logger.info("{} : Mapping work for get sections ",this.getClass().getName());
		if(entityListMap.get(AppConstants.SECTION) == null || entityListMap.get(AppConstants.SECTION).size() == 0){
			refreshCacheList(AppConstants.SECTION);
		}
	return (List<Section>)entityListMap.get(AppConstants.SECTION);
	}
	
	@Override
	public List<MasterDataBean> getSectionAsMasterdata() {
		logger.info("{} : Get section as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Section section : getSection()){
			MasterDataBean bean = new MasterDataBean(section.getSectionId().toString(), section.getSection(), section.getBranchId().toString());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Wing> getWing(){
		logger.info("{} : Mapping work for get wing ",this.getClass().getName());
		if(entityListMap.get(AppConstants.WING) == null || entityListMap.get(AppConstants.WING).size() == 0){
			refreshCacheList(AppConstants.WING);
		}
	return (List<Wing>)entityListMap.get(AppConstants.WING);
	}
	
	@Override
	public List<MasterDataBean> getWingAsMasterdata() {
		logger.info("{} : Get wing as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Wing wing : getWing()){
			MasterDataBean bean = new MasterDataBean(wing.getWingId().toString(), wing.getWing());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Floor> getFloor(){
		logger.info("{} : Mapping work for get Floor ",this.getClass().getName());
		if(entityListMap.get(AppConstants.FLOOR) == null || entityListMap.get(AppConstants.FLOOR).size() == 0){
			refreshCacheList(AppConstants.FLOOR);
		}
	return (List<Floor>)entityListMap.get(AppConstants.FLOOR);
	}
	
	@Override
	public List<MasterDataBean> getFloorAsMasterdata() {
		logger.info("{} : Get floor as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Floor floor: getFloor()){
			MasterDataBean bean = new MasterDataBean(floor.getFloorId().toString(), floor.getFloor());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Block> getBlock(){
		logger.info("{} : Mapping work for get Block ",this.getClass().getName());
		if(entityListMap.get(AppConstants.BLOCK) == null || entityListMap.get(AppConstants.BLOCK).size() == 0){
			refreshCacheList(AppConstants.BLOCK);
		}
	return (List<Block>)entityListMap.get(AppConstants.BLOCK);
	}
	
	@Override
	public List<MasterDataBean> getBlockAsMasterdata() {
		logger.info("{} : Get block as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Block block : getBlock()){
			MasterDataBean bean = new MasterDataBean(block.getBlockId().toString(), block.getBlock());
			masterData.add(bean);
		}
		return masterData;
	}


	@SuppressWarnings("unchecked")
	public synchronized List<RoomTypeDetail> getRoomTypeDetails(){
		logger.info("{} : Mapping work for get RoomTypeDetails ",this.getClass().getName());
		if(entityListMap.get(AppConstants.ROOMNO) == null || entityListMap.get(AppConstants.ROOMNO).size() == 0){
			refreshCacheList(AppConstants.ROOMNO);
		}
	return (List<RoomTypeDetail>)entityListMap.get(AppConstants.ROOMNO);
	}
	
	
	@Override
	public List<RoomTypeDetail> getRoomNoAsMasterdata() {
		logger.info("{} : Get room no as master data",this.getClass().getName());
		List<RoomTypeDetail> masterData = getRoomTypeDetails();
		
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<VehicleDetail> getVehicleDetails(){
		logger.info("{} : Mapping work for get VehicleTypeDetails ",this.getClass().getName());
		if(entityListMap.get(AppConstants.VEHICLE) == null || entityListMap.get(AppConstants.VEHICLE).size() == 0){
			refreshCacheList(AppConstants.VEHICLE);
		}
		return (List<VehicleDetail>)entityListMap.get(AppConstants.VEHICLE);
	}
	@Override
	public List<VehicleDetail> getVehicleDetailAsMasterdata(){
		logger.info("{} : Get vehicleId as master data",this.getClass().getName());
		List<VehicleDetail> masterData = getVehicleDetails();
		return masterData;
		
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<VehicleType> getVehicleTypes(){
		logger.info("{} : Mapping work for get VehicleType ",this.getClass().getName());	
		if(entityListMap.get(AppConstants.VEHICLETYPE) == null || entityListMap.get(AppConstants.VEHICLETYPE).size() == 0){
			refreshCacheList(AppConstants.VEHICLETYPE);
		}
		return (List<VehicleType>)entityListMap.get(AppConstants.VEHICLETYPE);
	}
	
	@Override
	public List<MasterDataBean> getVehicleTypeIdAsMasterdata(){
		logger.info("{} : Get vehicleType id as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(VehicleType vehicleType  : getVehicleTypes()){
			MasterDataBean bean = new MasterDataBean(vehicleType.getTypeId().toString(),vehicleType.getType());
			masterData.add(bean);
		}
		return masterData;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public synchronized List<RoomTypeMaster> getRoomTypes(){
		logger.info("{} : Mapping work for get RoomTypeMaster ",this.getClass().getName());		
		if(entityListMap.get(AppConstants.ROOMTYPE) == null || entityListMap.get(AppConstants.ROOMTYPE).size() == 0){
			refreshCacheList(AppConstants.ROOMTYPE);
		}
	return (List<RoomTypeMaster>)entityListMap.get(AppConstants.ROOMTYPE);
	}
	
	
	@Override
	public List<MasterDataBean> getRoomTypeCodeAsMasterdata(){
		logger.info("{} : Get RoomType Code as master data",this.getClass().getName());		
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(RoomTypeMaster roomTypeMaster : getRoomTypes()){
			MasterDataBean bean = new MasterDataBean(roomTypeMaster.getTypeCode(), roomTypeMaster.getDescription());
			masterData.add(bean);
		}
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Transport> getTransports(){
		logger.info("{} : Mapping work for get transport ",this.getClass().getName());		
		if(entityListMap.get(AppConstants.TRANSPORT) == null || entityListMap.get(AppConstants.TRANSPORT).size() == 0){
			refreshCacheList(AppConstants.TRANSPORT);
		}
		return (List<Transport>)entityListMap.get(AppConstants.TRANSPORT);
		
	}
	
	@Override
	public List<MasterDataBean> getTransportRouteCodeAsMasterdata(){
		logger.info("{} : Get transport route code as master data",this.getClass().getName());
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Transport transport: getTransports()){
			MasterDataBean bean = new MasterDataBean(transport.getRouteCode(), transport.getDescription());
			masterData.add(bean);
		}
		return masterData;
	}
	
	
	
	public void builtEntityListCache(){
		List<Branch> branchs =new ArrayList<Branch>();
		logger.info("{} : built entity list cache work for get branch ",this.getClass().getName());
		branchs=cacheDao.getBranch();
		entityListMap.put(AppConstants.BRANCH, branchs);

		
		List<Course> courses=new ArrayList<Course>();
		logger.info("{} : built entity list cache work for get course ",this.getClass().getName());
		courses=cacheDao.getCourse();
		entityListMap.put(AppConstants.COURSE, courses);

		List<CasteCategory> categories=new ArrayList<CasteCategory>();
		logger.info("{} : built entity list cache work for get catagory ",this.getClass().getName());
		categories=cacheDao.getCatagory();
		entityListMap.put(AppConstants.CATEGORY, categories);

		List<CounsellingBody> counsellingBodies=new ArrayList<CounsellingBody>();
		logger.info("{} : built entity list cache work for get CunsellingBody ",this.getClass().getName());
		counsellingBodies=cacheDao.getCounsellingBody();
		entityListMap.put(AppConstants.COUNSELLING, counsellingBodies);

		List<Qualification> qualifications=new ArrayList<Qualification>();
		logger.info("{} : built entity list cache work for get qualfication ",this.getClass().getName());
		qualifications=cacheDao.getQualification();
		entityListMap.put(AppConstants.QUALIFICATION, qualifications);

		List<QuotaCode> quotaCodes=new ArrayList<QuotaCode>();
		logger.info("{} : built entity list cache work for get QuotaCode ",this.getClass().getName());
		quotaCodes=cacheDao.getQuotaCode();
		entityListMap.put(AppConstants.QUOTACODE, quotaCodes);

		List<State> states=new ArrayList<State>();
		logger.info("{} : built entity list cache work for get state ",this.getClass().getName());
		states=cacheDao.getState();
		entityListMap.put(AppConstants.STATE, states);

		List<Consultant> consultants=new ArrayList<Consultant>();
		logger.info("{} : built entity list cache work for get consultant ",this.getClass().getName());
		consultants=cacheDao.getConsultant();
		entityListMap.put(AppConstants.CONSULTANT, consultants);

		List<Subject> subjectes=new ArrayList<Subject>();
		logger.info("{} : built entity list cache work for get subject ",this.getClass().getName());
		subjectes=cacheDao.getSubject();
		entityListMap.put(AppConstants.SUBJECT, subjectes);

		List<FeeDiscountHead> feeDiscountHeads=new ArrayList<FeeDiscountHead>();
		logger.info("{} : built entity list cache work for get fee discount head master ",this.getClass().getName());
		feeDiscountHeads=cacheDao.getFeeDiscountHeadMaster();
		entityListMap.put(AppConstants.FEEHEAD, feeDiscountHeads);

		List<Semester> semesters=new ArrayList<Semester>();
		logger.info("{} : built entity list cache work for get semester ",this.getClass().getName());
		semesters=cacheDao.getSemester();
		entityListMap.put(AppConstants.SEMESTER, semesters);
		
		List<CodeMapping> codeMappings=new ArrayList<CodeMapping>();
		logger.info("{} : built entity list cache work for get code mapping ",this.getClass().getName());
		codeMappings=cacheDao.getCodeMapping();
		entityListMap.put(AppConstants.CODE_MAP, codeMappings);

		List<Batch> batchs = new ArrayList<Batch>();
		logger.info("{} : built entity list cache work for get batch ",this.getClass().getName());
		batchs = cacheDao.getBatch();
		entityListMap.put(AppConstants.BATCH, batchs);
		
		List<Session> sessions = new ArrayList<Session>();
		logger.info("{} : built entity list cache work for get session ",this.getClass().getName());
		sessions = cacheDao.getSession();
		entityListMap.put(AppConstants.SESSION, sessions);
		
		List<Centre> centres = new ArrayList<Centre>();
		logger.info("{} : built entity list cache work for get centre ",this.getClass().getName());
		centres = cacheDao.getCentre();
		entityListMap.put(AppConstants.CENTRE, centres);
		
		List<Shift> shifts= new ArrayList<Shift>();
		logger.info("{} : built entity list cache work for get shift ",this.getClass().getName());
		shifts = cacheDao.getShift();
		entityListMap.put(AppConstants.SHIFT, shifts);
		
		List<Section> sections = new ArrayList<Section>();
		logger.info("{} : built entity list cache work for get section ",this.getClass().getName());
		sections = cacheDao.getSection();
		entityListMap.put(AppConstants.SECTION, sections);
		
		List<Wing> wings = new ArrayList<Wing>();
		logger.info("{} : built entity list cache work for get wing ",this.getClass().getName());
		wings = cacheDao.getWing();
		entityListMap.put(AppConstants.WING, wings);
		
		List<Floor> floors = new ArrayList<Floor>();
		logger.info("{} : built entity list cache work for get floor ",this.getClass().getName());
		floors = cacheDao.getFloor();
		entityListMap.put(AppConstants.FLOOR, floors);
		
		List<Block> blocks = new ArrayList<Block>();
		logger.info("{} : built entity list cache work for get block ",this.getClass().getName());
		blocks = cacheDao.getBlock();
		entityListMap.put(AppConstants.BLOCK, blocks);
		
		List<RoomTypeDetail> details = new ArrayList<RoomTypeDetail>();
		logger.info("{} : built entity list cache work for get roomTypeDetail ",this.getClass().getName());
		details = cacheDao.getRoomTypeDetail();
		entityListMap.put(AppConstants.ROOMNO, details);
		
		List<VehicleDetail> vehicleDetails = new ArrayList<VehicleDetail>();
		logger.info("{} : built entity list cache work for get vehichleDetail ",this.getClass().getName());
		vehicleDetails = cacheDao.getVehicleDetail();
		entityListMap.put(AppConstants.VEHICLE, vehicleDetails);
		
		List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
		logger.info("{} : built entity list cache work for get VehicleType ",this.getClass().getName());
		vehicleTypes = cacheDao.getVehicleTypes();
		entityListMap.put(AppConstants.VEHICLETYPE, vehicleTypes);
		
		List<RoomTypeMaster> roomTypeMasters = new ArrayList<RoomTypeMaster>();
		logger.info("{} : built entity list cache work for get RoomTypeMaster ",this.getClass().getName());
		roomTypeMasters = cacheDao.getRoomType();
		entityListMap.put(AppConstants.ROOMTYPE, roomTypeMasters);

		List<Transport> transports = new ArrayList<Transport>();
		logger.info("{} : built entity list cache work for get transport ",this.getClass().getName());
		transports = cacheDao.getTransport();
		entityListMap.put(AppConstants.TRANSPORT, transports);
		
		
		buildEntityMap();

	}

	
	
	@Override
	public void refreshCacheList(final String entity){
		switch (entity) {
		case AppConstants.BRANCH:
			logger.info("{} : refresh cache list work for get branch ",this.getClass().getName());
			List<Branch> branchs =new ArrayList<Branch>();
			branchs=cacheDao.getBranch();
			entityListMap.put(AppConstants.BRANCH, branchs);
			break;

		case AppConstants.COURSE:	
			logger.info("{} : refresh cache list work for get course ",this.getClass().getName());
			List<Course> courses=new ArrayList<Course>();
			courses=cacheDao.getCourse();
			entityListMap.put(AppConstants.COURSE, courses);
			break;

		case AppConstants.CATEGORY:
			logger.info("{} :  refresh cache list work for get catagory ",this.getClass().getName());
			List<CasteCategory> categories=new ArrayList<CasteCategory>();
			categories=cacheDao.getCatagory();
			entityListMap.put(AppConstants.CATEGORY, categories);
			break;

		case AppConstants.COUNSELLING:
			logger.info("{} :  refresh cache list work for get CunsellingBody ",this.getClass().getName());
			List<CounsellingBody> counsellingBodies=new ArrayList<CounsellingBody>();
			counsellingBodies=cacheDao.getCounsellingBody();
			entityListMap.put(AppConstants.COUNSELLING, counsellingBodies);
			break;

		case AppConstants.QUALIFICATION:
			logger.info("{} :refresh cache list work for get qualfication ",this.getClass().getName());
			List<Qualification> qualifications=new ArrayList<Qualification>();
			qualifications=cacheDao.getQualification();
			entityListMap.put(AppConstants.QUALIFICATION, qualifications);

		case AppConstants.QUOTACODE:
			logger.info("{} : refresh cache list work for get QuotaCode ",this.getClass().getName());
			List<QuotaCode> quotaCodes=new ArrayList<QuotaCode>();
			quotaCodes=cacheDao.getQuotaCode();
			entityListMap.put(AppConstants.QUOTACODE, quotaCodes);
			break;

		case AppConstants.STATE:
			logger.info("{} : refresh cache list work for get state ",this.getClass().getName());
			List<State> states=new ArrayList<State>();
			states=cacheDao.getState();
			entityListMap.put(AppConstants.STATE, states);
			break;

		case AppConstants.CONSULTANT:
			logger.info("{} : refresh cache list work for get consultant ",this.getClass().getName());
			List<Consultant> consultants=new ArrayList<Consultant>();
			consultants=cacheDao.getConsultant();
			entityListMap.put(AppConstants.CONSULTANT, consultants);
			break;

		case AppConstants.SUBJECT:
			logger.info("{} : refresh cache list work for get subject ",this.getClass().getName());
			List<Subject> subjectes=new ArrayList<Subject>();
			subjectes=cacheDao.getSubject();
			entityListMap.put(AppConstants.SUBJECT, subjectes);
			break;

		case AppConstants.FEEHEAD:
			logger.info("{} : refresh cache list work for get fee discount head master ",this.getClass().getName());
			List<FeeDiscountHead> feeDiscountHeads=new ArrayList<FeeDiscountHead>();
			feeDiscountHeads=cacheDao.getFeeDiscountHeadMaster();
			entityListMap.put(AppConstants.FEEHEAD, feeDiscountHeads);
			break;

		case AppConstants.SEMESTER:
			logger.info("{} :refresh cache list work for get semester ",this.getClass().getName());
			List<Semester> semesters=new ArrayList<Semester>();
			semesters=cacheDao.getSemester();
			entityListMap.put(AppConstants.SEMESTER, semesters);
			break;
			
		case AppConstants.BATCH:
			logger.info("{} : refresh cache list work for get batch ",this.getClass().getName());
			List<Batch> batchs = new ArrayList<Batch>();
			batchs = cacheDao.getBatch();
			entityListMap.put(AppConstants.BATCH, batchs);
			break;
			
		case AppConstants.SESSION:
			logger.info("{} :refresh cache list work for get session ",this.getClass().getName());
			List<Session> sessions = new ArrayList<Session>();
			sessions = cacheDao.getSession();
			entityListMap.put(AppConstants.SESSION, sessions);
			break;
		
		case AppConstants.CENTRE:
			logger.info("{} :refresh cache list work for get centre ",this.getClass().getName());
			List<Centre> centres = new ArrayList<Centre>();
			centres = cacheDao.getCentre();
			entityListMap.put(AppConstants.CENTRE, centres);
			break;
		
		case AppConstants.SHIFT:
			logger.info("{} : refresh cache list work for get shift ",this.getClass().getName());
			List<Shift> shifts = new ArrayList<Shift>();
			shifts = cacheDao.getShift();
			entityListMap.put(AppConstants.SHIFT, shifts);
			break;
			
		case AppConstants.SECTION:
			logger.info("{} : refresh cache list work for get section ",this.getClass().getName());
			List<Section> sections = new ArrayList<Section>();
			sections = cacheDao.getSection();
			entityListMap.put(AppConstants.SECTION, sections);
			
		case AppConstants.WING:
			logger.info("{} : refresh cache list work for get wing ",this.getClass().getName());
			List<Wing> wings = new ArrayList<Wing>();
			wings = cacheDao.getWing();
			entityListMap.put(AppConstants.WING, wings);
			
		case AppConstants.FLOOR:
			logger.info("{} : refresh cache list work for get floor ",this.getClass().getName());
			List<Floor> floors = new ArrayList<Floor>();
			floors = cacheDao.getFloor();
			entityListMap.put(AppConstants.FLOOR, floors);
			
		case AppConstants.BLOCK:
			logger.info("{} : refresh cache list work for get block ",this.getClass().getName());
			List<Block> blocks = new ArrayList<Block>();
			blocks = cacheDao.getBlock();
			entityListMap.put(AppConstants.BLOCK, blocks);
			
		case AppConstants.ROOMNO:
			logger.info("{} : refresh cache list work for get roomno ",this.getClass().getName());
			List<RoomTypeDetail> details = new ArrayList<RoomTypeDetail>();
			details = cacheDao.getRoomTypeDetail();
			entityListMap.put(AppConstants.ROOMNO, details);
			
		case AppConstants.VEHICLE:
			logger.info("{} : refresh cache list work for get vehicle ",this.getClass().getName());
			List<VehicleDetail> vehicleDetails = new ArrayList<VehicleDetail>();
			vehicleDetails = cacheDao.getVehicleDetail();
			entityListMap.put(AppConstants.VEHICLE,vehicleDetails);
	
		case AppConstants.VEHICLETYPE:
			logger.info("{} : refresh cache list work for get vehicle type ",this.getClass().getName());
		List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
		vehicleTypes = cacheDao.getVehicleTypes();
		entityListMap.put(AppConstants.VEHICLETYPE, vehicleTypes);
		
		
		case AppConstants.ROOMTYPE:
			logger.info("{} : refresh cache list work for get room type master ",this.getClass().getName());
			List<RoomTypeMaster> roomTypeMasters = new ArrayList<RoomTypeMaster>();
			roomTypeMasters = cacheDao.getRoomType();
			entityListMap.put(AppConstants.ROOMTYPE, roomTypeMasters);
		
		case AppConstants.TRANSPORT:
			logger.info("{} : refresh cache list work for get transport ",this.getClass().getName());			
			List<Transport> transports =new ArrayList<Transport>();
			transports = cacheDao.getTransport();
			entityListMap.put(AppConstants.TRANSPORT, transports);

		
		default:

		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntityList(String entity){
		logger.info("{} : Get entity list by entity:{} ",this.getClass().getName(), entity);
		return (List<T>)entityListMap.get(entity);
	}

	private void buildEntityMap(){
		logger.info("{} : Build Entity Map ",this.getClass().getName());
		for(FeeDiscountHead feeDiscountHead:getFeeDiscountHeads()){
			feeDetailMap.put(feeDiscountHead.getHeadId(), feeDiscountHead);
		}
		
		for(Course course:getCourses()){
			courseMap.put(course.getId(), course);
		}
		
		for(CasteCategory category:getCategories()){
			categoryMap.put(category.getId(), category);
		}
		
		for(Branch branch:getBranchs()){
			branchMap.put(branch.getId(), branch);
		}
	
		for(CodeMapping codeMapping:getCodeMapping()){
			codeMap.put(codeMapping.getName(), codeMapping.getCode());
		}
		
		for(Batch batch : getBatch()){
			batchMap.put(batch.getBatchId(),batch);
		}
		
		for(Centre centre : getCentre()){
			centreMap.put(centre.getCentreId(), centre);
		}
		
		for(Section section : getSection()){
			sectionMap.put(section.getSectionId(), section);
		}
		
		for(Session session : getSession()){
			sessionMap.put(session.getSessionId(), session);
		}
		
		for(Shift shift : getShift()){
			shiftMap.put(shift.getShiftId(), shift);
		}
		
		for(Consultant consultant : getConsultant()){
			consultantMap.put(consultant.getConsultantId(), consultant);
		}
		
		for(CounsellingBody counselling : getCounsellingBodies()){
			counsellingMap.put(counselling.getId(), counselling);
		}
		
		for(Qualification qualification : getQualifications()){
			qualificationMap.put(qualification.getId(), qualification);
		}
		
		for(QuotaCode quotaCode : getQuotaCodes()){
			quotaCodeMap.put(quotaCode.getId(), quotaCode);
		}
		
		for(State state : getStates()){
			stateMap.put(state.getId(), state);
		}
		
		for(Subject subject : getSubjects()){
			subjectMap.put(subject.getId(), subject);
		}
		
		for(Wing wing : getWing()){
			wingMap.put(wing.getWingId(), wing);
		}
		
		for(Floor floor : getFloor()){
			floorMap.put(floor.getFloorId(), floor);
		}
		
		for(Block block : getBlock()){
			blockMap.put(block.getBlockId(), block);
		}
		
		for(RoomTypeDetail roomDetail : cacheDao.getRoomTypeDetail()){
			roomDetailMap.put(roomDetail.getRoomNo(), roomDetail);
		}
		
		for(VehicleDetail vehicleDetail : cacheDao.getVehicleDetail()){
			vehicleDetailMap.put(vehicleDetail.getVehicleId(), vehicleDetail);
		}
		
		for(VehicleType vehicleType : cacheDao.getVehicleTypes()){
			vehicleTypeMap.put(vehicleType.getTypeId(), vehicleType);
		}
		
		for (RoomTypeMaster roomTypeMaster : cacheDao.getRoomType()) {
			roomTypeMap.put(roomTypeMaster.getTypeCode(), roomTypeMaster);
		}
		
		for(Transport transport : cacheDao.getTransport()){
			transportMap.put(transport.getRouteCode(), transport);
		}
		
	}
    
	@Override
	public FeeDiscountHead getFeeDiscountById(Long headId){
		logger.info("{} : Get fee discount by head id:{} ",this.getClass().getName(), headId);
		return feeDetailMap.get(headId);

	}

	@Override
	public Course getCourseById(Long courseId){
		logger.info("{} : Get course by course id:{} ",this.getClass().getName(), courseId);	
		return courseMap.get(courseId);
	}
	
	@Override
	public Branch getBranchById(Long branchId){
		logger.info("{} : Get branch by branch id:{} ",this.getClass().getName(), branchId);
		return branchMap.get(branchId);
		
	}
	
	@Override
	public String getCodeMappingByName(String name){
		logger.info("{} : Get code mapping by name:{} ",this.getClass().getName(), name);
		return codeMap.get(name);
	}

	
	@Override
	public Batch getBatchByBatchId(Long batchId){
		logger.info("{} : Get batch by id:{} ",this.getClass().getName(), batchId);
		return batchMap.get(batchId);
	}
	
	@Override
	public Centre getCentreByCentreId(Long centreId){
		logger.info("{} : Get centre by centre id:{} ",this.getClass().getName(), centreId);
		return centreMap.get(centreId);
	}
	
	@Override
	public Section getSectionBySectionId(Long sectionId){
		logger.info("{} : Get section by section id:{} ",this.getClass().getName(), sectionId);
		return sectionMap.get(sectionId);
	}
	
	@Override
	public Session getSessionBySessionId(Long sessionId){
		logger.info("{} : Get session by session id:{} ",this.getClass().getName(), sessionId);
		return sessionMap.get(sessionId);
	}
	
	@Override
	public Shift getShiftByShiftId(Long shiftId){
		logger.info("{} : Get shift by shift id:{} ",this.getClass().getName(), shiftId);
		return shiftMap.get(shiftId);
	}
	
	@Override
	public CasteCategory getCategoryId(Long categoryId){
		logger.info("{} : Get category by category id:{} ",this.getClass().getName(), categoryId);
		return categoryMap.get(categoryId);
	}
	
	@Override
	public Consultant getConsultantId(Long consultantId){
		logger.info("{} : Get consultant by consultant id:{} ",this.getClass().getName(), consultantId);
		return consultantMap.get(consultantId);
	}
	
	@Override
	public CounsellingBody getCounsellingBodiesId(Long id){
		logger.info("{} : Get counsellingBody by counselling id:{} ",this.getClass().getName(), id);
		return counsellingMap.get(id);
	}
	
	@Override
	public Qualification getQualificationId(Long id){
		logger.info("{} : Get qualification  by qualification id:{} ",this.getClass().getName(), id);
		return qualificationMap.get(id);
	}
	
	@Override
	public QuotaCode getQuotaCodeId(Long id){
		logger.info("{} : Get quota code  by quota code id:{} ",this.getClass().getName(), id);
		return quotaCodeMap.get(id);
		
	}
	
	@Override
	public State getStateId(Long id){
		logger.info("{} : Get state by state id:{} ",this.getClass().getName(), id);
		return stateMap.get(id);
	}
	
	@Override
	public Subject getSubjectId(Long id){
		logger.info("{} : Get subject by subject id:{} ",this.getClass().getName(), id);
		return subjectMap.get(id);
	}
	
	@Override
	public Wing getWingByWingId(Long id){
		logger.info("{} : Get wing by wing id:{} ",this.getClass().getName(), id);
		return wingMap.get(id);
	}
	
	@Override
	public Floor getFloorByFloorId(Long id){
		logger.info("{} : Get floor by floor id:{} ",this.getClass().getName(), id);
		return floorMap.get(id);
	}
	
	@Override
	public Block getBlockByBlockId(Long id){
		logger.info("{} : Get block by block id:{} ",this.getClass().getName(), id);
		return blockMap.get(id);
	}
	
	@Override
	public RoomTypeDetail getroomDetailByRoomNo(String roomNo){
		logger.info("{} : Get room type detail by room no:{} ",this.getClass().getName(), roomNo);
		return roomDetailMap.get(roomNo);
	}
	
	@Override
	public VehicleDetail getVehicleDeatilByVehicleId(Long vehicleId ){
		logger.info("{} : Get vehicle deatil by vehicle id:{} ",this.getClass().getName(), vehicleId);
		return vehicleDetailMap.get(vehicleId);
		
	}
	
	@Override
	public VehicleType getVehicleTypeByTypeId(Long typeId){
		logger.info("{} : Get Vehicle Type By Type Id:{} ",this.getClass().getName(), typeId);		
		return vehicleTypeMap.get(typeId);
	}

	@Override
	public RoomTypeMaster getRoomTypeMasterByTypeCode(String typeCode){
		logger.info("{} : Get Room Type Master By Type Code:{} ",this.getClass().getName(), typeCode);		
		return roomTypeMap.get(typeCode);
	}
	
	
	public Transport getTransportByRouteCode(String routeCode){
		logger.info("{} : Get transport By route Code:{} ",this.getClass().getName(), routeCode);
		return transportMap.get(routeCode);
	}

}

