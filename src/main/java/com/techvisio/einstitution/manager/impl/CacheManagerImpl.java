package com.techvisio.einstitution.manager.impl;

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
import com.techvisio.einstitution.db.CacheDao;
import com.techvisio.einstitution.db.impl.CacheDaoImpl;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.MasterDataConstants;

public class CacheManagerImpl implements CacheManager {

	@Autowired
	CacheDao cacheDao;
	
	
	public void setCacheDao(CacheDaoImpl cacheDao) {
		this.cacheDao = cacheDao;
	}

	private  Map<String,Object> cacheMap=new HashMap<String, Object>();

	
	@SuppressWarnings("unchecked")
	public synchronized List<Branch> getBranchs(){
		
		if(cacheMap.get(MasterDataConstants.BRANCH) == null){
			
			List<Branch> branchs =null;
			branchs=cacheDao.getBranch();
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
			List<Course> courses=null;
			courses=cacheDao.getCourse();
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
			List<CasteCategory> categories=null;
			categories=cacheDao.getCatagory();
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
			List<CounsellingBody> counsellingBodies=null;
			counsellingBodies=cacheDao.getCounsellingBody();
			cacheMap.put(MasterDataConstants.COUNSELLING, counsellingBodies);
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
			List<FeeHead> feeHeads=null;
			feeHeads=cacheDao.getFeeHead();
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
			List<Qualification> qualifications=null;
			qualifications=cacheDao.getQualification();
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
			List<QuotaCode> quotaCodes=null;
			quotaCodes=cacheDao.getQuotaCode();
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
			List<State> states=null;
			states=cacheDao.getState();
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
			List<Subject> subjectes=null;
			subjectes=cacheDao.getSubject();
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


}
