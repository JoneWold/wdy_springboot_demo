package com.ibeetl.admin.core.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {	
	@Bean(name = "baseDataSource")
	public DataSource datasource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.baseDataSource.url"));
		ds.setUsername(env.getProperty("spring.datasource.baseDataSource.username"));
		ds.setPassword(env.getProperty("spring.datasource.baseDataSource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.baseDataSource.driver-class-name"));
		return ds;
	}
}



