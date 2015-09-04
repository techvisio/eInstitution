package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKFLOW")    
public class Workflow extends BasicEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Step_Id")
	private Long stepId;
	@Column(name = "Step")
	private String step;
	@Column(name = "Workflow")
	private String workflow;
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="Step_Id")
	private List<Activity> activities;
	@ManyToMany(cascade={CascadeType.ALL} , fetch=FetchType.EAGER)
	@JoinTable(name="WORKFLOW_DEPENDENCY")
	private List<Workflow> childWorkflow;
	
	
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
	
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activity) {
		this.activities = activity;
	}
	public List<Workflow> getChildWorkflow() {
		return childWorkflow;
	}
	public void setChildWorkflow(List<Workflow> childWorkflow) {
		this.childWorkflow = childWorkflow;
	}
	
	
}
