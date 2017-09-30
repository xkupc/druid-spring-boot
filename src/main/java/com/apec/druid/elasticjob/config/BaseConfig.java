package com.apec.druid.elasticjob.config;

import org.springframework.context.annotation.Bean;;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;

/**
 * @author xk
 * @createTime 2017/9/29 0029 下午 4:15
 * @description
 */
@Configuration
public class BaseConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
