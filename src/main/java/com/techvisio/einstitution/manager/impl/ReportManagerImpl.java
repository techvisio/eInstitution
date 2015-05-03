package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.db.ReportDao;
import com.techvisio.einstitution.manager.ReportManager;
import com.techvisio.einstitution.util.ContextProvider;

@Component
public class ReportManagerImpl implements ReportManager {
	
	@Autowired
	ReportDao reportDao;

	private static ReportManagerImpl instance = null;
	public static synchronized ReportManagerImpl getInstance()
	{
		if(instance == null){
			instance=new ReportManagerImpl();
		}
		
		return instance;
	}
	@Override
	public List<ConsultantReport> getConsultantReport() {
		List<ConsultantReport> reports = reportDao.getConsultantReport();
		return reports;
	}

}
