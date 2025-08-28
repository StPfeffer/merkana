package com.merkana.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.merkana")
@ConfigurationPropertiesScan(basePackages = "com.merkana")
public class MerkanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerkanaApplication.class, args);
    }

}
