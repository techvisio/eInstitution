package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.CodeMapping;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Semester;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.db.impl.CacheDaoImpl;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;

public class CacheManagerImpl implements CacheManager {

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
	
	@SuppressWarnings("unchecked")
	public synchronized List<Branch> getBranchs(){

		if(entityListMap.get(AppConstants.BRANCH) == null||entityListMap.get(AppConstants.BRANCH).size()==0){
			refreshCacheList(AppConstants.BRANCH);
		}
		return (List<Branch>) entityListMap.get(AppConstants.BRANCH);
	}


	public List<MasterDataBean> getBranchAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Branch branch:getBranchs()){
			MasterDataBean bean=new MasterDataBean(branch.getId().toString(), branch.getBranchName(), branch.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}


	@SuppressWarnings("unchecked")
	public synchronized  List<Course> getCourses() {
		if(entityListMap.get(AppConstants.COURSE) == null||entityListMap.get(AppConstants.COURSE).size()==0){
			refreshCacheList(AppConstants.COURSE);
		}

		return (List<Course>)entityListMap.get(AppConstants.COURSE);
	}

	public List<MasterDataBean> getCourseAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Course course:getCourses()){
			MasterDataBean bean=new MasterDataBean(course.getId().toString(), course.getCourse());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<CasteCategory> getCategories() {
		if(entityListMap.get(AppConstants.CATEGORY) == null||entityListMap.get(AppConstants.CATEGORY).size()==0){
			refreshCacheList(AppConstants.CATEGORY);
		}

		return (List<CasteCategory>)entityListMap.get(AppConstants.CATEGORY);
	}

	public List<MasterDataBean> getCategoryAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CasteCategory category:getCategories()){
			MasterDataBean bean=new MasterDataBean(category.getId().toString(), category.getCategoryName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<CounsellingBody> getCounsellingBodies() {
		if(entityListMap.get(AppConstants.COUNSELLING) == null||entityListMap.get(AppConstants.COUNSELLING).size()==0){
			refreshCacheList(AppConstants.COUNSELLING);
		}
		return (List<CounsellingBody>)entityListMap.get(AppConstants.COUNSELLING);
	}

	public List<MasterDataBean> getCounsellingBodyAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CounsellingBody body:getCounsellingBodies()){
			MasterDataBean bean=new MasterDataBean(body.getId().toString(), body.getCousellingBody());
			masterData.add(bean);
		}
		return masterData;
	}


	@SuppressWarnings("unchecked")
	public synchronized  List<Qualification> getQualifications() {
		if(entityListMap.get(AppConstants.QUALIFICATION) == null||entityListMap.get(AppConstants.QUALIFICATION).size()==0){
			refreshCacheList(AppConstants.QUALIFICATION);
		}
		return (List<Qualification>)entityListMap.get(AppConstants.QUALIFICATION);
	}

	public List<MasterDataBean> getQualificationAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Qualification qualification:getQualifications()){
			MasterDataBean bean=new MasterDataBean(qualification.getId().toString(), qualification.getQulaifyingExam());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<QuotaCode> getQuotaCodes() {
		if(entityListMap.get(AppConstants.QUOTACODE) == null||entityListMap.get(AppConstants.QUOTACODE).size()==0){
			refreshCacheList(AppConstants.QUOTACODE);
		}
		return (List<QuotaCode>)entityListMap.get(AppConstants.QUOTACODE);
	}

	public List<MasterDataBean> getQuotaCodeAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(QuotaCode quotaCode : getQuotaCodes()){
			MasterDataBean bean=new MasterDataBean(quotaCode.getId().toString(), quotaCode.getCode());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<State> getStates() {
		if(entityListMap.get(AppConstants.STATE) == null||entityListMap.get(AppConstants.STATE).size()==0){
			refreshCacheList(AppConstants.STATE);
		}
		return (List<State>)entityListMap.get(AppConstants.STATE);
	}

	public List<MasterDataBean> getStateAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(State state : getStates()){
			MasterDataBean bean=new MasterDataBean(state.getId().toString(), state.getStateName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized  List<Consultant> getConsultant() {
		if(entityListMap.get(AppConstants.CONSULTANT) == null||entityListMap.get(AppConstants.CONSULTANT).size()==0){
			refreshCacheList(AppConstants.CONSULTANT);
		}
		return (List<Consultant>)entityListMap.get(AppConstants.CONSULTANT);
	}


	public List<MasterDataBean> getConsultantAsMasterdata(){

		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Consultant consultant : getConsultant()){
			MasterDataBean bean=new MasterDataBean(consultant.getConsultantId().toString(), consultant.getName());
			masterData.add(bean);
		}
		return masterData;


	}

	@SuppressWarnings("unchecked")
	public synchronized  List<Subject> getSubjects() {
		if(entityListMap.get(AppConstants.SUBJECT) == null||entityListMap.get(AppConstants.SUBJECT).size()==0){
			refreshCacheList(AppConstants.SUBJECT);
		}
		return (List<Subject>)entityListMap.get(AppConstants.SUBJECT);
	}


	public List<MasterDataBean> getSubjectAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Subject subject : getSubjects()){
			MasterDataBean bean=new MasterDataBean(subject.getId().toString(), subject.getSubjectName());
			masterData.add(bean);
		}
		return masterData;
	}	


	@SuppressWarnings("unchecked")
	public synchronized  List<FeeDiscountHead> getFeeDiscountHeads() {
		if(entityListMap.get(AppConstants.FEEHEAD) == null||entityListMap.get(AppConstants.FEEHEAD).size()==0){
			refreshCacheList(AppConstants.FEEHEAD);
		}
		return (List<FeeDiscountHead>)entityListMap.get(AppConstants.FEEHEAD);
	}


	public List<MasterDataBean> getFeeDiscountAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(FeeDiscountHead feeDiscountHead : getFeeDiscountHeads()){
			MasterDataBean bean=new MasterDataBean(feeDiscountHead.getHeadId().toString(), feeDiscountHead.getHead(), feeDiscountHead.getTransactionType());
			masterData.add(bean);
		}
		return masterData;
	}	


	@SuppressWarnings("unchecked")
	public synchronized  List<Semester> getSemester() {
		if(entityListMap.get(AppConstants.SEMESTER) == null||entityListMap.get(AppConstants.SEMESTER).size()==0){
			refreshCacheList(AppConstants.SEMESTER);
		}
		return (List<Semester>)entityListMap.get(AppConstants.SEMESTER);
	}


	public List<MasterDataBean> getSemesterAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Semester semester : getSemester()){
			MasterDataBean bean=new MasterDataBean(semester.getId().toString(), semester.getSemester(),semester.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}	

	@SuppressWarnings("unchecked")
	public synchronized  List<CodeMapping> getCodeMapping() {
		if(entityListMap.get(AppConstants.CODE_MAP) == null||entityListMap.get(AppConstants.CODE_MAP).size()==0){
			refreshCacheList(AppConstants.CODE_MAP);
		}
		return (List<CodeMapping>)entityListMap.get(AppConstants.CODE_MAP);
	}


	public List<MasterDataBean> getCodeMappingAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(CodeMapping codeMapping : getCodeMapping()){
			MasterDataBean bean=new MasterDataBean(codeMapping.getName(), codeMapping.getName());
			masterData.add(bean);
		}
		return masterData;
	}	
	
	@SuppressWarnings("unchecked")
	public synchronized List<Batch> getBatch(){
		if(entityListMap.get(AppConstants.BATCH) == null || entityListMap.get(AppConstants.BATCH).size() == 0){
			refreshCacheList(AppConstants.BATCH);
		}
		return (List<Batch>)entityListMap.get(AppConstants.BATCH);
	}
	
	
	@Override
	public List<MasterDataBean> getBatchAsMasterdata() {
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Batch batch : getBatch()){
			MasterDataBean bean = new MasterDataBean(batch.getBatchId().toString(), batch.getBatch(), batch.getCourseId().toString());
			masterData.add(bean);
		}
		
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Session> getSession(){
		if(entityListMap.get(AppConstants.SESSION) == null || entityListMap.get(AppConstants.SESSION).size() == 0){
			refreshCacheList(AppConstants.SESSION);
		}
		return (List<Session>)entityListMap.get(AppConstants.SESSION);
	}
	
	@Override
	public List<MasterDataBean> getSessionAsMasterdata() {
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Session session : getSession()){
			MasterDataBean bean = new MasterDataBean(session.getSessionId().toString(), session.getSession(), session.getCourseId().toString());
			masterData.add(bean);
		}
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Centre> getCentre(){
		if(entityListMap.get(AppConstants.CENTRE) == null || entityListMap.get(AppConstants.CENTRE).size() == 0){
			refreshCacheList(AppConstants.CENTRE);
		}
	return (List<Centre>)entityListMap.get(AppConstants.CENTRE);
	}
	
	@Override
	public List<MasterDataBean> getCentreAsMasterdata() {
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Centre centre : getCentre()){
			MasterDataBean bean = new MasterDataBean(centre.getCentreId().toString(), centre.getCentreName());
			masterData.add(bean);
		}
		return masterData;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Shift> getShift(){
		if(entityListMap.get(AppConstants.SHIFT) == null || entityListMap.get(AppConstants.SHIFT).size() == 0){
			refreshCacheList(AppConstants.SHIFT);
		}
	return (List<Shift>)entityListMap.get(AppConstants.SHIFT);
	}
	
	@Override
	public List<MasterDataBean> getShiftAsMasterdata() {
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Shift shift : getShift()){
			MasterDataBean bean = new MasterDataBean(shift.getShiftId().toString(), shift.getShiftName());
			masterData.add(bean);
		}
		return masterData;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Section> getSection(){
		if(entityListMap.get(AppConstants.SECTION) == null || entityListMap.get(AppConstants.SECTION).size() == 0){
			refreshCacheList(AppConstants.SECTION);
		}
	return (List<Section>)entityListMap.get(AppConstants.SECTION);
	}
	
	@Override
	public List<MasterDataBean> getSectionAsMasterdata() {
		List<MasterDataBean> masterData = new ArrayList<MasterDataBean>();
		for(Section section : getSection()){
			MasterDataBean bean = new MasterDataBean(section.getSectionId().toString(), section.getSection(), section.getBranchId().toString());
			masterData.add(bean);
		}
		return masterData;
	}

	
	public void builtEntityListCache(){

		List<Branch> branchs =new ArrayList<Branch>();
		branchs=cacheDao.getBranch();
		entityListMap.put(AppConstants.BRANCH, branchs);


		List<Course> courses=new ArrayList<Course>();
		courses=cacheDao.getCourse();
		entityListMap.put(AppConstants.COURSE, courses);

		List<CasteCategory> categories=new ArrayList<CasteCategory>();
		categories=cacheDao.getCatagory();
		entityListMap.put(AppConstants.CATEGORY, categories);

		List<CounsellingBody> counsellingBodies=new ArrayList<CounsellingBody>();
		counsellingBodies=cacheDao.getCounsellingBody();
		entityListMap.put(AppConstants.COUNSELLING, counsellingBodies);

		List<Qualification> qualifications=new ArrayList<Qualification>();
		qualifications=cacheDao.getQualification();
		entityListMap.put(AppConstants.QUALIFICATION, qualifications);

		List<QuotaCode> quotaCodes=new ArrayList<QuotaCode>();
		quotaCodes=cacheDao.getQuotaCode();
		entityListMap.put(AppConstants.QUOTACODE, quotaCodes);

		List<State> states=new ArrayList<State>();
		states=cacheDao.getState();
		entityListMap.put(AppConstants.STATE, states);

		List<Consultant> consultants=new ArrayList<Consultant>();
		consultants=cacheDao.getConsultant();
		entityListMap.put(AppConstants.CONSULTANT, consultants);

		List<Subject> subjectes=new ArrayList<Subject>();
		subjectes=cacheDao.getSubject();
		entityListMap.put(AppConstants.SUBJECT, subjectes);

		List<FeeDiscountHead> feeDiscountHeads=new ArrayList<FeeDiscountHead>();
		feeDiscountHeads=cacheDao.getFeeDiscountHeadMaster();
		entityListMap.put(AppConstants.FEEHEAD, feeDiscountHeads);

		List<Semester> semesters=new ArrayList<Semester>();
		semesters=cacheDao.getSemester();
		entityListMap.put(AppConstants.SEMESTER, semesters);
		
		List<CodeMapping> codeMappings=new ArrayList<CodeMapping>();
		codeMappings=cacheDao.getCodeMapping();
		entityListMap.put(AppConstants.CODE_MAP, codeMappings);

		List<Batch> batchs = new ArrayList<Batch>();
		batchs = cacheDao.getBatch();
		entityListMap.put(AppConstants.BATCH, batchs);
		
		List<Session> sessions = new ArrayList<Session>();
		sessions = cacheDao.getSession();
		entityListMap.put(AppConstants.SESSION, sessions);
		
		List<Centre> centres = new ArrayList<Centre>();
		centres = cacheDao.getCentre();
		entityListMap.put(AppConstants.CENTRE, centres);
		
		List<Shift> shifts= new ArrayList<Shift>();
		shifts = cacheDao.getShift();
		entityListMap.put(AppConstants.SHIFT, shifts);
		
		List<Section> sections = new ArrayList<Section>();
		sections = cacheDao.getSection();
		entityListMap.put(AppConstants.SECTION, sections);
		
		buildEntityMap();

	}

	@Override
	public void refreshCacheList(final String entity){
		switch (entity) {
		case AppConstants.BRANCH:
			List<Branch> branchs =new ArrayList<Branch>();
			branchs=cacheDao.getBranch();
			entityListMap.put(AppConstants.BRANCH, branchs);
			break;

		case AppConstants.COURSE:	
			List<Course> courses=new ArrayList<Course>();
			courses=cacheDao.getCourse();
			entityListMap.put(AppConstants.COURSE, courses);
			break;

		case AppConstants.CATEGORY:
			List<CasteCategory> categories=new ArrayList<CasteCategory>();
			categories=cacheDao.getCatagory();
			entityListMap.put(AppConstants.CATEGORY, categories);
			break;

		case AppConstants.COUNSELLING:
			List<CounsellingBody> counsellingBodies=new ArrayList<CounsellingBody>();
			counsellingBodies=cacheDao.getCounsellingBody();
			entityListMap.put(AppConstants.COUNSELLING, counsellingBodies);
			break;

		case AppConstants.QUALIFICATION:
			List<Qualification> qualifications=new ArrayList<Qualification>();
			qualifications=cacheDao.getQualification();
			entityListMap.put(AppConstants.QUALIFICATION, qualifications);

		case AppConstants.QUOTACODE:
			List<QuotaCode> quotaCodes=new ArrayList<QuotaCode>();
			quotaCodes=cacheDao.getQuotaCode();
			entityListMap.put(AppConstants.QUOTACODE, quotaCodes);
			break;

		case AppConstants.STATE:
			List<State> states=new ArrayList<State>();
			states=cacheDao.getState();
			entityListMap.put(AppConstants.STATE, states);
			break;

		case AppConstants.CONSULTANT:
			List<Consultant> consultants=new ArrayList<Consultant>();
			consultants=cacheDao.getConsultant();
			entityListMap.put(AppConstants.CONSULTANT, consultants);
			break;

		case AppConstants.SUBJECT:
			List<Subject> subjectes=new ArrayList<Subject>();
			subjectes=cacheDao.getSubject();
			entityListMap.put(AppConstants.SUBJECT, subjectes);
			break;

		case AppConstants.FEEHEAD:
			List<FeeDiscountHead> feeDiscountHeads=new ArrayList<FeeDiscountHead>();
			feeDiscountHeads=cacheDao.getFeeDiscountHeadMaster();
			entityListMap.put(AppConstants.FEEHEAD, feeDiscountHeads);
			break;

		case AppConstants.SEMESTER:
			List<Semester> semesters=new ArrayList<Semester>();
			semesters=cacheDao.getSemester();
			entityListMap.put(AppConstants.SEMESTER, semesters);
			break;
			
		case AppConstants.BATCH:
			List<Batch> batchs = new ArrayList<Batch>();
			batchs = cacheDao.getBatch();
			entityListMap.put(AppConstants.BATCH, batchs);
			break;
			
		case AppConstants.SESSION:
			List<Session> sessions = new ArrayList<Session>();
			sessions = cacheDao.getSession();
			entityListMap.put(AppConstants.SESSION, sessions);
			break;
		
		case AppConstants.CENTRE:
			List<Centre> centres = new ArrayList<Centre>();
			centres = cacheDao.getCentre();
			entityListMap.put(AppConstants.CENTRE, centres);
			break;
		
		case AppConstants.SHIFT:
			List<Shift> shifts = new ArrayList<Shift>();
			shifts = cacheDao.getShift();
			entityListMap.put(AppConstants.SHIFT, shifts);
			break;
			
		case AppConstants.SECTION:
			List<Section> sections = new ArrayList<Section>();
			sections = cacheDao.getSection();
			entityListMap.put(AppConstants.SECTION, sections);
			
		default:

		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntityList(String entity){
		return (List<T>)entityListMap.get(entity);
	}

	private void buildEntityMap(){

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
	}
    
	@Override
	public FeeDiscountHead getFeeDiscountById(Long headId){

		return feeDetailMap.get(headId);

	}

	@Override
	public Course getCourseById(Long courseId){
	
		return courseMap.get(courseId);
	}
	
	@Override
	public Branch getBranchById(Long branchId){

		return branchMap.get(branchId);
		
	}
	
	@Override
	public String getCodeMappingByName(String name){
		
		return codeMap.get(name);
	}

	
	@Override
	public Batch getBatchByBatchId(Long batchId){
		return batchMap.get(batchId);
	}
	
	@Override
	public Centre getCentreByCentreId(Long centreId){
		return centreMap.get(centreId);
	}
	
	@Override
	public Section getSectionBySectionId(Long sectionId){
		return sectionMap.get(sectionId);
	}
	
	@Override
	public Session getSessionBySessionId(Long sessionId){
		return sessionMap.get(sessionId);
	}
	
	@Override
	public Shift getShiftByShiftId(Long shiftId){
		return shiftMap.get(shiftId);
	}
	
	@Override
	public CasteCategory getCategoryId(Long categoryId){
		return categoryMap.get(categoryId);
	}
}

