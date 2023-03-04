package com.dungnguyen.kafkademo.config;


import com.dungnguyen.kafkademo.SequenceGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }


    @Bean
    public SequenceGenerator createSequenceGenerator(){
        return new SequenceGenerator();
    }
}
