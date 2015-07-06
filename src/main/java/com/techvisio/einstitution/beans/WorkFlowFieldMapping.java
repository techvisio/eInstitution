package com.techvisio.einstitution.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class WorkFlowFieldMapping extends BasicEntity

{
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "Workflow_Step_Id")
	private String workFlowStepId;
	@Column(name = "Workflow_Id")
	private String workFlowId;
	
	private List<FieldDesc> fieldDesc = new ArrayList<FieldDesc>();
	public String getWorkFlowStepId() {
		return workFlowStepId;
	}
	public void setWorkFlowStepId(String workFlowStepId) {
		this.workFlowStepId = workFlowStepId;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public List<FieldDesc> getFieldDesc() {
		return fieldDesc;
	}
	
	public void setFieldDesc(List<FieldDesc> fieldDesc) {
		this.fieldDesc = fieldDesc;
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
