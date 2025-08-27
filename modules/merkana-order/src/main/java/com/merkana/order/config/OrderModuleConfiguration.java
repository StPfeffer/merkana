package com.merkana.order.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.merkana.order.repository"
})
@EntityScan(basePackages = "com.merkana.order.entity")
@ComponentScan(basePackages = "com.merkana.order")
public class OrderModuleConfiguration {

}

