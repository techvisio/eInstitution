package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.ContextProvider;

public class DefaultManagerImpl implements DefaultManager {

	public Long getDefaultCentre(){
		CacheManager cacheManager=ContextProvider.getContext().getBean(CacheManager.class);
		return null;
	}
}
