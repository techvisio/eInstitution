package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKFLOW")    
public class Workflow extends BasicEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Step_Id")
	private Long stepId;
	@Column(name = "Parent_Id")
	private Long parentId;
	@Column(name = "Step")
	private String step;
	@Column(name = "Workflow")
	private String workflow;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="Activity_Id")
	private List<Activity> activity;
	@OneToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Workflow_Dependency")
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
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<Activity> getActivity() {
		return activity;
	}
	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
	public List<Workflow> getChildWorkflow() {
		return childWorkflow;
	}
	public void setChildWorkflow(List<Workflow> childWorkflow) {
		this.childWorkflow = childWorkflow;
	}
	
	
}
