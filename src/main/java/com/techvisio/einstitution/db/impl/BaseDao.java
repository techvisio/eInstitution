package com.techvisio.einstitution.db.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public abstract class BaseDao {

private DataSource dataSource;
private JdbcTemplate jdbcTemplate;
private NamedParameterJdbcTemplate namedParamJdbcTemplate;

@Autowired
public void setDatasource(DataSource dataSource) {
	this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
}


public JdbcTemplate getJdbcTemplate() {
	return jdbcTemplate;
}




public NamedParameterJdbcTemplate getNamedParamJdbcTemplate() {
	return namedParamJdbcTemplate;
}


public DataSource getDataSource() {
	return dataSource;
}




}
