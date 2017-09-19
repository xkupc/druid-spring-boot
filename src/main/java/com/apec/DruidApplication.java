package com.apec;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DruidApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DruidApplication.class).web(true).run(args);
    }
}
