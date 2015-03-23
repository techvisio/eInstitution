package com.techvisio.einstitution.beans;

import java.util.Date;

public class TaskAndFollowUp {
 private Long taskId;
 private String user;
 private String role;
 private String module;
 private Long entityId;
 private Long parentTaskId;
 private Date taskDate;
 private String remark;
 private String status;
 
public Long getTaskId() {
	return taskId;
}
public void setTaskId(Long taskId) {
	this.taskId = taskId;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public Long getEntityId() {
	return entityId;
}
public void setEntityId(Long entityId) {
	this.entityId = entityId;
}
public Long getParentTaskId() {
	return parentTaskId;
}
public void setParentTaskId(Long parentTaskId) {
	this.parentTaskId = parentTaskId;
}
public Date getTaskDate() {
	return taskDate;
}
public void setTaskDate(Date taskDate) {
	this.taskDate = taskDate;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "TaskAndFollowUp [taskId=" + taskId + ", user=" + user + ", role="
			+ role + ", module=" + module + ", entityId=" + entityId
			+ ", parentTaskId=" + parentTaskId + ", taskDate=" + taskDate
			+ ", remark=" + remark + ", status=" + status + "]";
}

 
 
}
