package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

public class StudentActivity {

	private Long activityId;
	private String activity;
	private Long fileNo;
	private Date date;

	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
