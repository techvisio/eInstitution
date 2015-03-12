package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.Semester;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.db.impl.CacheDaoImpl;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;

public class CacheManagerImpl implements CacheManager {

	@Autowired
	CacheDao cacheDao;


	public void setCacheDao(CacheDaoImpl cacheDao) {
		this.cacheDao = cacheDao;
	}

	public CacheManagerImpl(){
		builtEntityListCache();
	}
	
	private static Map<String,List> entityListMap=new HashMap<String, List>();
	private static Map<Long,FeeDiscountHead> feeDetailMap = new HashMap<Long, FeeDiscountHead>();

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
	public synchronized  List<FeeDiscountHead> getsFeeDiscounts() {
		if(entityListMap.get(AppConstants.FEEHEAD) == null||entityListMap.get(AppConstants.FEEHEAD).size()==0){
			refreshCacheList(AppConstants.FEEHEAD);
		}
		return (List<FeeDiscountHead>)entityListMap.get(AppConstants.FEEHEAD);
	}


	public List<MasterDataBean> getFeeDiscountAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(FeeDiscountHead feeDiscountHead : feeDetailList){
			MasterDataBean bean=new MasterDataBean(feeDiscountHead.getHeadId().toString(), feeDiscountHead.getHead());
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



	public static List<FeeDiscountHead> feeDetailList=new ArrayList<FeeDiscountHead>();

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

		buildEntityMap();

	}

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

		default:

		}
	}

	private void buildEntityMap(){

		for(FeeDiscountHead feeDiscountHead:getsFeeDiscounts()){
			feeDetailMap.put(feeDiscountHead.getHeadId(), feeDiscountHead);
		}
	}

	@Override
	public FeeDiscountHead getFeeDiscountById(Long headId){

		return feeDetailMap.get(headId);

	}


}

