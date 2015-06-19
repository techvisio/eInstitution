package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskandfollowup")

public class TaskAndFollowUp {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Task_Id")
	private Long taskId;
	@Column(name = "User_Id")
	private String user;
	@Column(name = "Role")
	private String role;
	@Column(name = "Module")
	private String module;
	@Column(name = "Entity_Id")
	private Long entityId;
	@Column(name = "Parent_Task_Id")
	private Long parentTaskId;
	@Column(name = "Task_Date")
	private String taskDate;
	@Column(name = "Remark")
	private String remark;
	@Column(name = "Status")
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
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
