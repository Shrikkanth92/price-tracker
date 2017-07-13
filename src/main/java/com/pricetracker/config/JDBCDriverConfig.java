package com.pricetracker.config;

import org.springframework.beans.factory.annotation.Value;

public class JDBCDriverConfig {
	

    @Value("${database.driver}")
    private String driver;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;
    

}
