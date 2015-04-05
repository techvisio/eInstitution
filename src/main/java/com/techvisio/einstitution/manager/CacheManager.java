package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.MasterDataBean;

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
	
	public List<MasterDataBean> getBatchAsMasterdata();

	public List<MasterDataBean> getCodeMappingAsMasterdata();
	public List<MasterDataBean> getBatchAsMasterdata();


}
