package com.techvisio.einstitution.manager.impl;

import java.util.Calendar;

import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.ContextProvider;

public class DefaultManagerImpl implements DefaultManager {

	public Long getDefaultCentre(){
		CacheManager cacheManager=ContextProvider.getContext().getBean(CacheManager.class);
		return 1L;
	}
	public Long getDefaultShift(){
		CacheManager cacheManager=ContextProvider.getContext().getBean(CacheManager.class);
		return 1L;
	}
	public Long getDefaultBatch(Long courseId){
		return 1L;
	}
	public Long getDefaultAcademicYear(){
		return new Long(Calendar.getInstance().YEAR);
	}
	public Long getDefaultSession(Long courseId){
		return 1L;
	}
}
