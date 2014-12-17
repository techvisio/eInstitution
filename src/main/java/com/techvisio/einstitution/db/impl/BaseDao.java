package com.techvisio.einstitution.db.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class BaseDao {

private DataSource dataSource;
private JdbcTemplate jdbcTemp;
private NamedParameterJdbcTemplate namedParamJdbc;

@Autowired
public void setDatasource(DataSource dataSource) {
	this.dataSource = dataSource;
    this.jdbcTemp = new JdbcTemplate(dataSource);
    this.namedParamJdbc = new NamedParameterJdbcTemplate(dataSource);
}


public JdbcTemplate getJdbcTemp() {
	return jdbcTemp;
}




public NamedParameterJdbcTemplate getNamedParamJdbc() {
	return namedParamJdbc;
}






}
