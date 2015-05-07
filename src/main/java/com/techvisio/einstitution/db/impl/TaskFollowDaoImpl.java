package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.QualificationSubjectDtl;
import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class TaskFollowDaoImpl extends BaseDao implements TaskFollowDao {
	private static CustomLogger logger = CustomLogger.getLogger(TaskFollowDaoImpl.class);
	@Autowired @Qualifier(value="taskFollowQueryProps")
	private Properties  taskFollowQueryProps;


	public void setTaskFollowQueryProps(Properties taskFollowQueryProps) {
		this.taskFollowQueryProps = taskFollowQueryProps;
	}

	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module) {
		logger.info("{} : Get task and followup by module and entity id: module:{} , entity id:{}",this.getClass().getName(), module,entityId);
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

	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps){
		logger.info("{} :Calling saveTaskAndFollowUp method",this.getClass().getName());		
		if(taskAndFollowUps != null){
			
			for(TaskAndFollowUp taskAndFollowUp : taskAndFollowUps){
				
				saveTaskAndFollowUp(taskAndFollowUp);
			}
		}
	}
	
	private void saveTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		logger.info("{} : Save task and followup for entity id:{}",this.getClass().getName(), taskAndFollowUp.getEntityId());
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
