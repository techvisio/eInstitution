package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;

public class TaskFlowDaoImpl extends BaseDao implements TaskFollowDao {
		
	private Properties  taskFollowQueryProps;


	public void setTaskFollowQueryProps(Properties taskFollowQueryProps) {
		this.taskFollowQueryProps = taskFollowQueryProps;
	}

	
	public TaskAndFollowUp getTaskAndFollowUpByTaskId(int taskId) {
		String getQuery = taskFollowQueryProps.getProperty("getTaskAndFollowUpByTaskId");
		SqlParameterSource namedParameter = new MapSqlParameterSource("TASK_ID", taskId);
		
		List<TaskAndFollowUp> taskAndFollowUps = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<TaskAndFollowUp>(){

			public TaskAndFollowUp mapRow(ResultSet rs, int rowNum)throws SQLException {
				TaskAndFollowUp followUp = new TaskAndFollowUp();
				followUp.setUserId(rs.getString("USER_ID"));
				followUp.setTaskId(rs.getInt("TASK_ID"));
				followUp.setTaskEntry(rs.getString("TASK_ENTRY"));
				followUp.setStatus(rs.getString("STATUS"));
				followUp.setRole(rs.getString("ROLE"));
				followUp.setRemark(rs.getString("REMARK"));
				followUp.setParentTaskId(rs.getInt("PARENTTASK_ID"));
				followUp.setDueDate(rs.getDate("DUE_DATE"));
				
				return followUp;

			}
			
			
		});
		TaskAndFollowUp taskAndFollowUp = null;
		if(taskAndFollowUps != null && taskAndFollowUps.size()>0){
			taskAndFollowUp = taskAndFollowUps.get(0);
		}
		
		
		return taskAndFollowUp;
	}

	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(int parentTaskId) {
		String getQuery = taskFollowQueryProps.getProperty("getTaskAndFollowUpByParentTaskId");
		SqlParameterSource namedParameter = new MapSqlParameterSource("PARENTTASK_ID", parentTaskId);
		
		List<TaskAndFollowUp> taskAndFollowUps = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<TaskAndFollowUp>(){

			public TaskAndFollowUp mapRow(ResultSet rs, int rowNum)throws SQLException {
				TaskAndFollowUp followUp = new TaskAndFollowUp();
				followUp.setUserId(rs.getString("USER_ID"));
				followUp.setTaskId(rs.getInt("TASK_ID"));
				followUp.setTaskEntry(rs.getString("TASK_ENTRY"));
				followUp.setStatus(rs.getString("STATUS"));
				followUp.setRole(rs.getString("ROLE"));
				followUp.setRemark(rs.getString("REMARK"));
				followUp.setParentTaskId(rs.getInt("PARENTTASK_ID"));
				followUp.setDueDate(rs.getDate("DUE_DATE"));
				
				return followUp;

			}
			
			
		});
		TaskAndFollowUp taskAndFollowUp = null;
		if(taskAndFollowUps != null && taskAndFollowUps.size()>0){
			taskAndFollowUp = taskAndFollowUps.get(0);
		}
		
		
		return taskAndFollowUp;

		
	}
	
	
	
	public void addTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		String addQuery = taskFollowQueryProps.getProperty("addTaskAndFollowUp");
		SqlParameterSource namedParameter = new MapSqlParameterSource("TASK_ID", taskAndFollowUp.getTaskId())
											.addValue("PARENTTASK_ID", taskAndFollowUp.getParentTaskId())
											.addValue("TASK_ENTRY", taskAndFollowUp.getTaskEntry())
											.addValue("ROLE", taskAndFollowUp.getRole())
											.addValue("USER_ID", taskAndFollowUp.getUserId())
											.addValue("DUE_DATE",taskAndFollowUp.getDueDate())
											.addValue("STATUS", taskAndFollowUp.getStatus())
											.addValue("REMARK", taskAndFollowUp.getRemark());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		
	}
	
	
	public void updateTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		String addQuery = taskFollowQueryProps.getProperty("updateTaskAndFollowUp");
		SqlParameterSource namedParameter = new MapSqlParameterSource("TASK_ID", taskAndFollowUp.getTaskId())
											.addValue("PARENTTASK_ID", taskAndFollowUp.getParentTaskId())
											.addValue("TASK_ENTRY", taskAndFollowUp.getTaskEntry())
											.addValue("ROLE", taskAndFollowUp.getRole())
											.addValue("USER_ID", taskAndFollowUp.getUserId())
											.addValue("DUE_DATE",taskAndFollowUp.getDueDate())
											.addValue("STATUS", taskAndFollowUp.getStatus())
											.addValue("REMARK", taskAndFollowUp.getRemark());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		
	}

	public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		String addQuery = taskFollowQueryProps.getProperty("deleteTaskAndFollowUp");
		SqlParameterSource namedParameter = new MapSqlParameterSource("TASK_ID", taskAndFollowUp.getTaskId())
											.addValue("STATUS", taskAndFollowUp.getStatus());
											
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);

		
	}


	
	
}
