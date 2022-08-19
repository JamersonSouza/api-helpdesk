package com.api.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.helpdesk.services.DBservice;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dBservice;

    @Bean
    public void instanciaDB(){
        this.dBservice.instanciaDB();
    }
    
}
