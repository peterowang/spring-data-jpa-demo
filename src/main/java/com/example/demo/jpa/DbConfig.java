package com.example.demo.jpa;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

@Configuration
public class DbConfig {
    @Autowired
    private Environment env;

    @Bean(name="dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUser(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("spring.datasource.max-active")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("spring.datasource.min-active")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("spring.datasource.initial-size")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("spring.datasource.max-idle")));
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);
        return dataSource;
    }

}