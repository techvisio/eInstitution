package com.techvisio.einstitution.beans;

import java.util.ArrayList;
import java.util.List;

public class WorkFlowFieldMapping  {
	private String workFlowStepId;
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
	@Override
	public String toString() {
		return "WorkFlowFieldMapping [workFlowStepId=" + workFlowStepId
				+ ", workFlowId=" + workFlowId + ", fieldDesc=" + fieldDesc
				+ "]";
	}
	public void setFieldDesc(List<FieldDesc> fieldDesc) {
		this.fieldDesc = fieldDesc;
	
	}
	
	
}
