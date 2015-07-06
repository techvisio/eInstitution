package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class AdmissionWorkflow {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Admission_workflow_Id")
	private Long admissionWorkflowId;
	
	private Long fileNo;
	private Workflow workflow;
	private User user;
	private Date date;
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
