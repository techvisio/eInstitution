package com.techvisio.einstitution.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ModuleLog;
import com.techvisio.einstitution.db.ModuleLogDao;
@Component
public class ModuleLogDaoImpl extends BaseDao implements ModuleLogDao {
	
	@Autowired
	private Properties  moduleLogQueryProps;
	

	public void setModuleLogQueryProps(Properties moduleLogQueryProps) {
		this.moduleLogQueryProps = moduleLogQueryProps;
	}

	
// GET DATA FROM ModuleLog TABLE	
	
	public ModuleLog getModuleLog(int entityId) {
		String getQuery = moduleLogQueryProps.getProperty("getModuleLog");
		SqlParameterSource namedParameter = new MapSqlParameterSource("ENTITY_ID", entityId);
		
		List<ModuleLog> moduleLogs = getNamedParamJdbcTemplate().query(getQuery, namedParameter,new RowMapper<ModuleLog>(){

			public ModuleLog mapRow(ResultSet rs, int rowNum)throws SQLException {
				ModuleLog log = new ModuleLog();
				log.setDate(rs.getDate("DATE"));
				log.setEntityId(rs.getInt("ENTITY_ID"));
				log.setOperation(rs.getString("OPERATION"));
				log.setUserId(rs.getInt("USER_ID"));
				log.setWorkFlowOperation(rs.getString("WORK_FLOW_OPERATION"));
				log.setErrorMessage(rs.getString("ERROR_MESSAGE"));
				return log;

			}
			
			
		});
		ModuleLog moduleLog = null;
		if(moduleLogs != null && moduleLogs.size()>0){
			moduleLog = moduleLogs.get(0);
		}
		
		
		return moduleLog;

	}
	
	
	
// INSERT DATA IN ModuleLog TABLE	

	public void addModuleLog(ModuleLog moduleLog) {
		String addQuery = moduleLogQueryProps.getProperty("addModuleLog");
		SqlParameterSource namedParameter = new MapSqlParameterSource("ENTITY_ID", moduleLog.getEntityId())
											.addValue("WORK_FLOW_OPERATION", moduleLog.getWorkFlowOperation())
											.addValue("ERROR_MESSAGE", moduleLog.getErrorMessage())
											.addValue("DATE", moduleLog.getDate())
											.addValue("USER_ID", moduleLog.getUserId())
											.addValue("OPERATION", moduleLog.getOperation());
		getNamedParamJdbcTemplate().update(addQuery, namedParameter);
	}
	
	
// UPDATE DATA IN ModuleLog TABLE	

	public void updateModuleLog(ModuleLog moduleLog) {
		String updateQuery = moduleLogQueryProps.getProperty("updateModuleLog");
		SqlParameterSource namedParameter = new MapSqlParameterSource("ENTITY_ID", moduleLog.getEntityId())
											.addValue("WORK_FLOW_OPERATION", moduleLog.getWorkFlowOperation())
											.addValue("ERROR_MESSAGE", moduleLog.getErrorMessage())
											.addValue("DATE", moduleLog.getDate())
											.addValue("USER_ID", moduleLog.getUserId())
											.addValue("OPERATION", moduleLog.getOperation());
		getNamedParamJdbcTemplate().update(updateQuery, namedParameter);
		
	}
	
// DELETE DATA FROM ModuleLog TABLE

	public void deleteModuleLog(int entityId) {
		String deleteQuery = moduleLogQueryProps.getProperty("deleteModuleLog");
		SqlParameterSource namedParameter = new MapSqlParameterSource("ENTITY_ID", entityId);
											
											
		getNamedParamJdbcTemplate().update(deleteQuery, namedParameter);

		
	}

	
}
