package com.techvisio.einstitution.beans;

import java.util.Date;

public class ModuleLog {
	private int entityId;
	private String workFlowOperation;
	private Date date;
	private int userId;
	private String operation;
	private String errorMessage;
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getWorkFlowOperation() {
		return workFlowOperation;
	}
	public void setWorkFlowOperation(String workFlowOperation) {
		this.workFlowOperation = workFlowOperation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "ModuleLog [entityId=" + entityId + ", workFlowOperation="
				+ workFlowOperation + ", date=" + date + ", userId=" + userId
				+ ", operation=" + operation + ", errorMessage=" + errorMessage
				+ "]";
	}
	
	

}

