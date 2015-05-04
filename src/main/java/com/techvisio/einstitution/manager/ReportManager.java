package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantReport;

@Component
public interface ReportManager {
	List<ConsultantReport> getConsultantReport();
}
