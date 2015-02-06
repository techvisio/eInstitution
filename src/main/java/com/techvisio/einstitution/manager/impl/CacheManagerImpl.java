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
import com.techvisio.einstitution.beans.FeeHead;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Qualification;
import com.techvisio.einstitution.beans.QuotaCode;
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

	private  Map<String,Object> cacheMap=new HashMap<String, Object>();

	
	@SuppressWarnings("unchecked")
	public synchronized List<Branch> getBranchs(){
		
		if(cacheMap.get(AppConstants.BRANCH) == null){
			
			List<Branch> branchs =null;
			branchs=cacheDao.getBranch();
			cacheMap.put(AppConstants.BRANCH, branchs);
		}
		return (List<Branch>) cacheMap.get(AppConstants.BRANCH);
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
		if(cacheMap.get(AppConstants.COURSE) == null){
			List<Course> courses=null;
			courses=cacheDao.getCourse();
			cacheMap.put(AppConstants.COURSE, courses);
		}
		
		return (List<Course>)cacheMap.get(AppConstants.COURSE);
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
		if(cacheMap.get(AppConstants.CATEGORY) == null){
			List<CasteCategory> categories=null;
			categories=cacheDao.getCatagory();
			cacheMap.put(AppConstants.CATEGORY, categories);
		}
		
		return (List<CasteCategory>)cacheMap.get(AppConstants.CATEGORY);
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
		if(cacheMap.get(AppConstants.COUNSELLING) == null){
			List<CounsellingBody> counsellingBodies=null;
			counsellingBodies=cacheDao.getCounsellingBody();
			cacheMap.put(AppConstants.COUNSELLING, counsellingBodies);
		}
		
		return (List<CounsellingBody>)cacheMap.get(AppConstants.COUNSELLING);
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
		if(cacheMap.get(AppConstants.FEEHEAD) == null){
			List<FeeHead> feeHeads=null;
			feeHeads=cacheDao.getFeeHead();
			cacheMap.put(AppConstants.FEEHEAD, feeHeads);
		}
		
		return (List<FeeHead>)cacheMap.get(AppConstants.FEEHEAD);
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
		if(cacheMap.get(AppConstants.QUALIFICATION) == null){
			List<Qualification> qualifications=null;
			qualifications=cacheDao.getQualification();
			cacheMap.put(AppConstants.QUALIFICATION, qualifications);
		}
		
		return (List<Qualification>)cacheMap.get(AppConstants.QUALIFICATION);
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
		if(cacheMap.get(AppConstants.QUOTACODE) == null){
			List<QuotaCode> quotaCodes=null;
			quotaCodes=cacheDao.getQuotaCode();
			cacheMap.put(AppConstants.QUOTACODE, quotaCodes);
		}
		
		return (List<QuotaCode>)cacheMap.get(AppConstants.QUOTACODE);
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
		if(cacheMap.get(AppConstants.STATE) == null){
			List<State> states=null;
			states=cacheDao.getState();
			cacheMap.put(AppConstants.STATE, states);
		}
		
		return (List<State>)cacheMap.get(AppConstants.STATE);
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
		if(cacheMap.get(AppConstants.CONSULTANT) == null){
			List<Consultant> consultants=null;
			consultants=cacheDao.getConsultant();
			cacheMap.put(AppConstants.CONSULTANT, consultants);
		}
		
		return (List<Consultant>)cacheMap.get(AppConstants.CONSULTANT);
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
	public synchronized  List<Subject> getsSubjects() {
		if(cacheMap.get(AppConstants.SUBJECT) == null){
			List<Subject> subjectes=null;
			subjectes=cacheDao.getSubject();
			cacheMap.put(AppConstants.SUBJECT, subjectes);
		}
		
		return (List<Subject>)cacheMap.get(AppConstants.SUBJECT);
	}
	
	
	public List<MasterDataBean> getSubjectAsMasterdata(){
     	List<MasterDataBean> masterData=new ArrayList<MasterDataBean>();
		for(Subject subject : getsSubjects()){
			MasterDataBean bean=new MasterDataBean(subject.getId().toString(), subject.getSubjectName());
			masterData.add(bean);
		}
		return masterData;
	}	


}
