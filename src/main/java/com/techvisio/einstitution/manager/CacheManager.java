package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.MasterDataBean;

public interface CacheManager {

	public List<MasterDataBean> getBranchAsMasterdata();

	public List<MasterDataBean> getCategoryAsMasterdata();

	public List<MasterDataBean> getCourseAsMasterdata();

	public List<MasterDataBean> getStateAsMasterdata();

	public List<MasterDataBean> getQualificationAsMasterdata();

	public List<MasterDataBean> getCounsellingBodyAsMasterdata();
	
	public List<MasterDataBean> getFeeHeadAsMasterdata();
	
	public List<MasterDataBean> getQuotaCodeAsMasterdata();
	
	public List<MasterDataBean> getSubjectAsMasterdata();
}
