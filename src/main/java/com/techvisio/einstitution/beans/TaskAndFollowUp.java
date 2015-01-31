package com.techvisio.einstitution.beans;

import java.util.Date;

public class TaskAndFollowUp {
 private int taskId;
 private int parentTaskId;
 private String taskEntry;
 private String role;
 private String userId;
 private Date dueDate;
 private String status;
 private String remark;
 
 
public int getTaskId() {
	return taskId;
}
public void setTaskId(int taskId) {
	this.taskId = taskId;
}
public int getParentTaskId() {
	return parentTaskId;
}
public void setParentTaskId(int parentTaskId) {
	this.parentTaskId = parentTaskId;
}
public String getTaskEntry() {
	return taskEntry;
}
public void setTaskEntry(String taskEntry) {
	this.taskEntry = taskEntry;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public Date getDueDate() {
	return dueDate;
}
public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
@Override
public String toString() {
	return "TaskAndFollowUp [taskId=" + taskId + ", parentTaskId="
			+ parentTaskId + ", taskEntry=" + taskEntry + ", role=" + role
			+ ", userId=" + userId + ", dueDate=" + dueDate + ", status="
			+ status + ", remark=" + remark + "]";
}
}
