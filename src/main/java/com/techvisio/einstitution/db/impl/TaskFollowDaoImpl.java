package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.QualificationSubject;
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
		String queryString="FROM TaskAndFollowUp tf WHERE tf.entityId = "+entityId +" and tf.module = '" + module +"'";
		Query query=getCurrentSession().createQuery(queryString);
		List<TaskAndFollowUp> result= query.list();
		return result;	
		
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
		if(taskAndFollowUp.getTaskId()==null){
			getCurrentSession().persist(taskAndFollowUp);
		}
		else{
			getCurrentSession().update(taskAndFollowUp);
		}
	}
}
