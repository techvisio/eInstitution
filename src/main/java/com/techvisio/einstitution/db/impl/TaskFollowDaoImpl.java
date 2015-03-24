package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;
import com.techvisio.einstitution.util.CommonUtil;

public class TaskFollowDaoImpl extends BaseDao implements TaskFollowDao {
		
	private Properties  taskFollowQueryProps;


	public void setTaskFollowQueryProps(Properties taskFollowQueryProps) {
		this.taskFollowQueryProps = taskFollowQueryProps;
	}

	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module) {
		String getQuery = taskFollowQueryProps.getProperty("getTaskAndFollowUpByModuleAndEntityId");
		SqlParameterSource namedParameter = new MapSqlParameterSource("Entity_Id", entityId)
											.addValue("Module", module);
		
		List<TaskAndFollowUp> taskAndFollowUps = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<TaskAndFollowUp>(){

			public TaskAndFollowUp mapRow(ResultSet rs, int rowNum)throws SQLException {
				TaskAndFollowUp followUp = new TaskAndFollowUp();
				followUp.setEntityId(CommonUtil.getLongValue(rs.getLong("Entity_Id")));
				followUp.setModule(rs.getString("Module"));
				followUp.setParentTaskId(CommonUtil.getLongValue(rs.getLong("Parent_Task_Id")));
				followUp.setRemark(rs.getString("Remark"));
				followUp.setRole(rs.getString("Role"));
				followUp.setStatus(rs.getString("Status"));
				followUp.setTaskDate(rs.getDate("Task_Date"));
				followUp.setTaskId(CommonUtil.getLongValue(rs.getLong("Task_Id")));
				followUp.setUser(rs.getString("User"));
				return followUp;

			}
			
			
		});
				
		return taskAndFollowUps;
	}

	
	public void saveTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		String addQuery = taskFollowQueryProps.getProperty("upsertTaskAndFollowUp");
		if(taskAndFollowUp.getTaskId() != null)
		{
		SqlParameterSource namedParameter = new MapSqlParameterSource("Entity_Id", taskAndFollowUp.getEntityId())
											.addValue("Module", taskAndFollowUp.getModule())
											.addValue("Parent_Task_Id", taskAndFollowUp.getParentTaskId())
											.addValue("Remark", taskAndFollowUp.getRemark())
											.addValue("Role", taskAndFollowUp.getRole())
											.addValue("Status",taskAndFollowUp.getStatus())
											.addValue("Task_Date", taskAndFollowUp.getTaskDate())
											.addValue("Task_Id", taskAndFollowUp.getTaskId())
											.addValue("User", taskAndFollowUp.getUser());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
		}
	}
	
	
}
