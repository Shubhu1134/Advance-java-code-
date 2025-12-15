package com.info.firstSpringMvc.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JpaConfig {

	 public DriverManagerDataSource getDataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		  dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcdb");
		  dataSource.setUsername("root");
		  dataSource.setPassword("root");
		  return dataSource;
	  }
}
