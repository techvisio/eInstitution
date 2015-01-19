package com.techvisio.einstitution.db;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.db.impl.BaseDao;

public class BaseDaoTest {

	@Test
	public void testDatasource(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		DataSource ds=(DataSource)ctx.getBean("dataSource");
		System.out.println(ds);
		System.out.println(BaseDao.class.getName());
	}
}
