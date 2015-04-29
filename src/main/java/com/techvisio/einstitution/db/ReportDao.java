package com.techvisio.einstitution.db;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantReport;

@Component
public interface ReportDao {

	List<ConsultantReport> getConsultantReport();
}
