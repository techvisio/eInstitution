package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Branch;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Course;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.Section;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;

public interface CacheManager {

	public List<MasterDataBean> getBranchAsMasterdata();

	public List<MasterDataBean> getCategoryAsMasterdata();

	public List<MasterDataBean> getCourseAsMasterdata();

	public List<MasterDataBean> getStateAsMasterdata();

	public List<MasterDataBean> getQualificationAsMasterdata();

	public List<MasterDataBean> getCounsellingBodyAsMasterdata();
	
	public List<MasterDataBean> getQuotaCodeAsMasterdata();
	
	public List<MasterDataBean> getSubjectAsMasterdata();
	
	public List<MasterDataBean> getConsultantAsMasterdata();
	
	public List<MasterDataBean> getFeeDiscountAsMasterdata();
	
	public List<MasterDataBean> getSemesterAsMasterdata();

	FeeDiscountHead getFeeDiscountById(Long headId);

	void refreshCacheList(String entity);
	
	public List<MasterDataBean> getCodeMappingAsMasterdata();

	Course getCourseById(Long courseId);

	Branch getBranchById(Long branchId);

	String getCodeMappingByName(String name);

	List<MasterDataBean> getBatchAsMasterdata();
	
	public List<MasterDataBean> getSessionAsMasterdata();

	public List<MasterDataBean> getCentreAsMasterdata();

	public List<MasterDataBean> getShiftAsMasterdata();
	
	public List<MasterDataBean> getSectionAsMasterdata();

	public <T> List<T> getEntityList(String entity);

	Batch getBatchByBatchId(Long batchId);

	Centre getCentreByCentreId(Long centreId);

	Section getSectionBySectionId(Long sectionId);

	Session getSessionBySessionId(Long sessionId);

	Shift getShiftByShiftId(Long shiftId);
}
