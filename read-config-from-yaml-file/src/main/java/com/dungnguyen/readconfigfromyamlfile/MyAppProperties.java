package com.dungnguyen.readconfigfromyamlfile;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;

@Data
@Configuration
@PropertySource(value = "classpath:log.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "log")
public class MyAppProperties {
    private HashMap<ActionEnum, String> serviceFirst;
    private HashMap<ActionEnum, String> serviceSecond;
}

