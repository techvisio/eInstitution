package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
import com.techvisio.einstitution.db.WorkFlowDao;

public class WorkFlowDaoImpl extends BaseDao implements WorkFlowDao{
	private Properties workFlowQueryProps;
	
	
	public void setWorkFlowQueryProps(Properties workFlowQueryProps) {
		this.workFlowQueryProps = workFlowQueryProps;
	}
	

//Select data from WorkFlowFieldMapping table by WorkFlowId	
	
	
public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId) {
	String getQuery = workFlowQueryProps.getProperty("getWorkFlowFieldMappingByWorkFlowId");
	SqlParameterSource namedParameter = new MapSqlParameterSource("Work_Flow_Id", workFlowId);
										
										
	List<WorkFlowFieldMapping> workFlowFieldMappings = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new  RowMapper<WorkFlowFieldMapping>(){

		public WorkFlowFieldMapping mapRow(ResultSet rs, int rowNum)throws SQLException {
		
		FieldDesc f = new FieldDesc();
		
		//workFlow.setValidValue(rs.getString("Valid_Value"))
		//int i =0;
		
		f.setId(rs.getString("Field_Desc_Id"));
		f.setVisible(rs.getBoolean("Visible"));
		String validValues=rs.getString("Valid_value");
		f.setValidValue(validValues.split(","));
		f.setType(rs.getString("Type"));
		f.setTitle(rs.getString("Title"));
		f.setMasterDataCode(rs.getString("Master_Data_Code"));
		f.setMandatoryInd(rs.getBoolean("Mandatory_Ind"));
		
		List<FieldDesc> fieldDescs = new ArrayList<FieldDesc>();
		fieldDescs.add(f);
		
		WorkFlowFieldMapping workFlow = new WorkFlowFieldMapping();
		
		workFlow.setWorkFlowStepId(rs.getString("Work_Flow_Step_Id"));
		workFlow.setWorkFlowId(rs.getString("Work_Flow_Id"));
		workFlow.setFieldDesc(fieldDescs);
		
		
		return workFlow;
			
		}
		
	});
	WorkFlowFieldMapping workFlowFieldMapping = null;
	if(workFlowFieldMappings != null && workFlowFieldMappings.size()>0){
		workFlowFieldMapping = workFlowFieldMappings.get(0);
	}
	
	return workFlowFieldMapping;
}


	
// Select data from WorkFlowFieldMapping table by WorkFlowStepId	
	

public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId) {
	String getQuery = workFlowQueryProps.getProperty("getWorkFlowFieldMappingByWorkFlowStepId");
	SqlParameterSource namedParameter = new MapSqlParameterSource("Work_Flow_Step_Id",workFlowStepId);
										//.addValue("Work_Flow_Id", workFlowId);
										
	List<WorkFlowFieldMapping> workFlowFieldMappings = getNamedParamJdbcTemplate().query(getQuery, namedParameter, new  RowMapper<WorkFlowFieldMapping>(){

		public WorkFlowFieldMapping mapRow(ResultSet rs, int rowNum)throws SQLException {
		
		FieldDesc f = new FieldDesc();
		
		//workFlow.setValidValue(rs.getString("Valid_Value"))
		//int i =0;
		
		f.setId(rs.getString("Field_Desc_Id"));
		f.setVisible(rs.getBoolean("Visible"));
		String validValues=rs.getString("Valid_value");
		f.setValidValue(validValues.split(","));
		f.setType(rs.getString("Type"));
		f.setTitle(rs.getString("Title"));
		f.setMasterDataCode(rs.getString("Master_Data_Code"));
		f.setMandatoryInd(rs.getBoolean("Mandatory_Ind"));
		
		List<FieldDesc> fieldDescs = new ArrayList<FieldDesc>();
		fieldDescs.add(f);
		
		WorkFlowFieldMapping workFlow = new WorkFlowFieldMapping();
		
		workFlow.setWorkFlowStepId(rs.getString("Work_Flow_Step_Id"));
		workFlow.setWorkFlowId(rs.getString("Work_Flow_Id"));
		workFlow.setFieldDesc(fieldDescs);
		
		
		return workFlow;
			
		}
		
	});
	WorkFlowFieldMapping workFlowFieldMapping = null;
	if(workFlowFieldMappings != null && workFlowFieldMappings.size()>0){
		workFlowFieldMapping = workFlowFieldMappings.get(0);
	}
	
	return workFlowFieldMapping;
}





	
// Insert data in WorkFlowFieldMapping table	
	
	
	

	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		String addQuery = workFlowQueryProps.getProperty("addWorkFlowFieldMapping");
		if(workFlowFieldMapping.getFieldDesc() !=null){
		for(FieldDesc fieldDesc:workFlowFieldMapping.getFieldDesc()){
		SqlParameterSource namedParameter = new MapSqlParameterSource("Work_Flow_Step_Id",workFlowFieldMapping.getWorkFlowStepId())
											.addValue("Work_Flow_Id",workFlowFieldMapping.getWorkFlowId())
											.addValue("Field_Desc_Id", fieldDesc.getId())
											.addValue("Type",fieldDesc.getType())
											.addValue("Title", fieldDesc.getTitle())
											.addValue("Master_Data_Code", fieldDesc.getMasterDataCode())
											.addValue("Valid_Value", Arrays.toString(fieldDesc.getValidValue()).replaceAll("\\[", "").replaceAll("\\]", ""))
											.addValue("Mandatory_Ind", fieldDesc.isMandatoryInd())
											.addValue("Visible", fieldDesc.isVisible());
		
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
			}
		}
		
	}


	
	
	
// Update data in WorkFlowFieldMapping table	
	
public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		String updateQuery = workFlowQueryProps.getProperty("updateWorkFlowFieldMapping");
		
		if(workFlowFieldMapping.getFieldDesc() != null ){
			for(FieldDesc fieldDesc:workFlowFieldMapping.getFieldDesc()){
		SqlParameterSource namedParameter = new MapSqlParameterSource("Work_Flow_Step_Id",workFlowFieldMapping.getWorkFlowStepId())
											.addValue("Work_Flow_Id",workFlowFieldMapping.getWorkFlowId())
											.addValue("Field_Desc_Id", fieldDesc.getId())
											.addValue("Type",fieldDesc.getType())
											.addValue("Title", fieldDesc.getTitle())
											.addValue("Master_Data_Code", fieldDesc.getMasterDataCode())
											.addValue("Valid_Value",Arrays.toString(fieldDesc.getValidValue()).replaceAll("\\[", "").replaceAll("\\]", ""))
											.addValue("Mandatory_Ind", fieldDesc.isMandatoryInd())
											.addValue("Visible", fieldDesc.isVisible());
		
		
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
			}
		}
	}
		




//Delete data from WorkFlowFieldMapping table


public void deleteWorkFlowFieldMapping(String workFlowStepId) {
		String delQuery = workFlowQueryProps.getProperty("deleteWorkFlowFieldMapping");
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("Work_Flow_Step_Id",workFlowStepId);
											
											
		
		getNamedParamJdbcTemplate().update(delQuery,namedParameter);
	}

}
