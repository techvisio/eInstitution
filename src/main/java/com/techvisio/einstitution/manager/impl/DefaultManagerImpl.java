package com.techvisio.einstitution.manager.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Batch;
import com.techvisio.einstitution.beans.Centre;
import com.techvisio.einstitution.beans.Session;
import com.techvisio.einstitution.beans.Shift;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.DefaultManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.ContextProvider;
@Component
public class DefaultManagerImpl implements DefaultManager {
	@Autowired
	CacheManager cacheManager;
	@Override
	public Long getDefaultCentre(){
		
		List<Centre> centres=cacheManager.getEntityList(AppConstants.CENTRE);
		if(centres != null && centres.size()>0){
		return centres.get(0).getCentreId();
		};
		return null;
	}
	
	@Override
	public Long getDefaultShift(){
		
		List<Shift> shifts = cacheManager.getEntityList(AppConstants.SHIFT);
		if(shifts !=null && shifts.size()>0){
			return shifts.get(0).getShiftId();
		}
		
		return null;
	}
	
	@Override
	public Long getDefaultBatch(Long courseId){
		
		List<Batch> batchs = cacheManager.getEntityList(AppConstants.BATCH);
		
		for(Batch batch : batchs){
			
			if(courseId==batch.getCourseId()){
				
				return batch.getBatchId();
			}
		}
		return null;
	
	}
	
	@Override
	public Long getDefaultAcademicYear(){
		return new Long(Calendar.getInstance().get(Calendar.YEAR));
	}
	
	@Override
	public Long getDefaultSession(Long courseId){
	
		List<Session> sessions = cacheManager.getEntityList(AppConstants.SESSION);
		
		for(Session session : sessions){
			
			if(courseId == session.getCourseId()){
				
				return session.getSessionId();
			}
		}
		
		return null;
	}
}
