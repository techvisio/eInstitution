package com.techvisio.einstitution.util;

import javax.sql.DataSource;

import org.springframework.jdbc.object.StoredProcedure;

public class SqlFunction extends StoredProcedure {

	public SqlFunction(DataSource ds,String sql) {
		super(ds,sql);
		setFunction(true);
	}
	
	
	
}
