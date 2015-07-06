package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Workflow {

	private String workflow;
	private Long stepId;
	private String step;
	private String activity;
	private Long parentId;
	
	public String getWorkflow() {
		return workflow;
	}
	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	public Long getStepId() {
		return stepId;
	}
	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
