package com.techvisio.einstitution.manager;

import java.util.List;

import com.techvisio.einstitution.beans.MasterDataBean;

public interface CacheManager {

	List<MasterDataBean> getBranchAsMasterdata();

	List<MasterDataBean> getCategoryAsMasterdata();

	List<MasterDataBean> getCourseAsMasterdata();

	List<MasterDataBean> getStateAsMasterdata();

	List<MasterDataBean> getQualificationAsMasterdata();

}
