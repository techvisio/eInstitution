package com.techvisio.einstitution.beans;

import java.util.Date;

public class EnquiryReportCriteria {
	private Date dateFrom;
	private Date dateTo;
	public Date getDateFrom(Date date) {
		return dateFrom ;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo(Date date) {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
}
