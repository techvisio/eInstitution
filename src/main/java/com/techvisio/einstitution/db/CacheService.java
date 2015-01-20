package com.techvisio.einstitution.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.CasteCategory;
import com.techvisio.einstitution.beans.CounsellingBody;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeHead;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
import com.techvisio.einstitution.beans.State;
import com.techvisio.einstitution.beans.Subject;
import com.techvisio.einstitution.db.impl.CacheDaoImpl;
import com.techvisio.einstitution.util.MasterDataConstants;

public class CacheService {

	private static CacheService instance;

	public static CacheService getInstance() {
		if (instance != null) {
			instance = new CacheService();
		}
		return instance;
	}
	
	@Autowired
    CacheDaoImpl cacheDao;
	
	private static Map<String,Object> cacheMap=new HashMap<String, Object>();

	
	@SuppressWarnings("unchecked")
	public synchronized List<Branch> getBranchs(){
		
		if(cacheMap.get(MasterDataConstants.BRANCH) == null){
			
			List<Branch> branchs = cacheDao.getBranch();
			cacheMap.put(MasterDataConstants.BRANCH, branchs);
		}
		return (List<Branch>) cacheMap.get(MasterDataConstants.BRANCH);
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
		if(cacheMap.get(MasterDataConstants.COURSE) == null){
			List<Course> courses=cacheDao.getCourse();
			cacheMap.put(MasterDataConstants.COURSE, courses);
		}
		
		return (List<Course>)cacheMap.get(MasterDataConstants.COURSE);
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
		if(cacheMap.get(MasterDataConstants.CATEGORY) == null){
			List<CasteCategory> categories=cacheDao.getCatagory();
			cacheMap.put(MasterDataConstants.CATEGORY, categories);
		}
		
		return (List<CasteCategory>)cacheMap.get(MasterDataConstants.CATEGORY);
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
		if(cacheMap.get(MasterDataConstants.COUNSELLING) == null){
			List<CounsellingBody> courses=cacheDao.getCounsellingBody();
			cacheMap.put(MasterDataConstants.COUNSELLING, courses);
		}
		
		return (List<CounsellingBody>)cacheMap.get(MasterDataConstants.COUNSELLING);
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
	public synchronized  List<FeeHead> getFeeHeads() {
		if(cacheMap.get(MasterDataConstants.FEEHEAD) == null){
			List<FeeHead> feeHeads=cacheDao.getFeeHead();
			cacheMap.put(MasterDataConstants.FEEHEAD, feeHeads);
		}
		
		return (List<FeeHead>)cacheMap.get(MasterDataConstants.FEEHEAD);
	}
	
	public List<MasterDataBean> getFeeHeadAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(FeeHead feeHead:getFeeHeads()){
			MasterDataBean bean=new MasterDataBean(feeHead.getId().toString(), feeHead.getFeeHead());
			masterData.add(bean);
		}
		return masterData;
	}
	
	
	@SuppressWarnings("unchecked")
	public synchronized  List<Qualification> getQualifications() {
		if(cacheMap.get(MasterDataConstants.QUALIFICATION) == null){
			List<Qualification> qualifications=cacheDao.getQualification();
			cacheMap.put(MasterDataConstants.QUALIFICATION, qualifications);
		}
		
		return (List<Qualification>)cacheMap.get(MasterDataConstants.QUALIFICATION);
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
		if(cacheMap.get(MasterDataConstants.QUOTACODE) == null){
			List<QuotaCode> quotaCodes=cacheDao.getQuotaCode();
			cacheMap.put(MasterDataConstants.QUOTACODE, quotaCodes);
		}
		
		return (List<QuotaCode>)cacheMap.get(MasterDataConstants.QUOTACODE);
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
		if(cacheMap.get(MasterDataConstants.STATE) == null){
			List<State> states=cacheDao.getState();
			cacheMap.put(MasterDataConstants.STATE, states);
		}
		
		return (List<State>)cacheMap.get(MasterDataConstants.STATE);
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
	public synchronized  List<Subject> getsSubjects() {
		if(cacheMap.get(MasterDataConstants.SUBJECT) == null){
			List<Subject> subjectes=cacheDao.getSubject();
			cacheMap.put(MasterDataConstants.STATE, subjectes);
		}
		
		return (List<Subject>)cacheMap.get(MasterDataConstants.SUBJECT);
	}
	
	public List<MasterDataBean> getSubjectAsMasterdata(){
		List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Subject subject : getsSubjects()){
			MasterDataBean bean=new MasterDataBean(subject.getId().toString(), subject.getSubjectName());
			masterData.add(bean);
		}
		return masterData;
	}	
//	public static void setCourseMap(Map<String, Course> courseMap) {
//		CacheService.courseMap = courseMap;
//	}
//
//	public static Map<String, Branch> getBranchMap() {
//		return branchMap;
//	}
//
//	public static void setBranchMap(Map<String, Branch> branchMap) {
//		CacheService.branchMap = branchMap;
//	}
//
//	public static Map<String, State> getStateMap() {
//		return stateMap;
//	}
//
//	public static void setStateMap(Map<String, State> stateMap) {
//		CacheService.stateMap = stateMap;
//	}
//
//	public static Map<String, CasteCategory> getCasteCategoryMap() {
//		return casteCategoryMap;
//	}
//
//	public static void setCasteCategoryMap(
//			Map<String, CasteCategory> casteCategoryMap) {
//		CacheService.casteCategoryMap = casteCategoryMap;
//	}
//
//	public static Map<String, Qualification> getQualificationMap() {
//		return qualificationMap;
//	}
//
//	public static void setQualificationMap(
//			Map<String, Qualification> qualificationMap) {
//		CacheService.qualificationMap = qualificationMap;
//	}
//
//	public static Map<String, CounsellingBody> getCounsellingMap() {
//		return counsellingMap;
//	}
//
//	public static void setCounsellingMap(
//			Map<String, CounsellingBody> counsellingMap) {
//		CacheService.counsellingMap = counsellingMap;
//	}
//
//	public static Map<String, FeeHead> getFeeHeadMap() {
//		return feeHeadMap;
//	}
//
//	public static void setFeeHeadMap(Map<String, FeeHead> feeHeadMap) {
//		CacheService.feeHeadMap = feeHeadMap;
//	}
//
//	public static Map<String, Subject> getSubjectMap() {
//		return SubjectMap;
//	}
//
//	public static void setSubjectMap(Map<String, Subject> subjectMap) {
//		SubjectMap = subjectMap;
//	}
//
//	public static Map<String, QuotaCode> getQuotaMap() {
//		return QuotaMap;
//	}
//
//	public static void setQuotaMap(Map<String, QuotaCode> quotaMap) {
//		QuotaMap = quotaMap;
//	}
//
//	public static void setInstance(CacheService instance) {
//		CacheService.instance = instance;
//	}

}
