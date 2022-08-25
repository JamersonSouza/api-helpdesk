package com.api.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.helpdesk.services.DBservice;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBservice dBservice;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

     @Bean
    public boolean instanciaDB(){
        if(value.equals("create")){
            this.dBservice.instanciaDB();
        }
        return false;   
    }


}
