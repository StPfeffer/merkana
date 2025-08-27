package com.merkana.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedisAutoConfigurationManager {

    @Bean
    @ConditionalOnProperty(name = "app.redis.enabled", havingValue = "true")
    public EnableRedisAutoConfiguration enableRedisAutoConfiguration() {
        log.info("Redis auto-configuration enabled!");
        return new EnableRedisAutoConfiguration();
    }

    @Bean
    @ConditionalOnProperty(name = "app.redis.enabled", havingValue = "false", matchIfMissing = true)
    public DisableRedisAutoConfiguration disableRedisAutoConfiguration() {
        log.info("Redis auto-configuration disabled!");
        return new DisableRedisAutoConfiguration();
    }

    @Configuration
    @ImportAutoConfiguration({RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class, RedisProperties.class})
    public static class EnableRedisAutoConfiguration {

    }

    @Configuration
    @ImportAutoConfiguration(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
    public static class DisableRedisAutoConfiguration {

    }

}
