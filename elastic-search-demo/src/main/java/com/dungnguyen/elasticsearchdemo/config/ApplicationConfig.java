package com.dungnguyen.elasticsearchdemo.config;

import com.dungnguyen.utils.SequenceGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public SequenceGenerator sequenceGenerator(){
        return new SequenceGenerator();
    }
}
