package com.merkana.redis.config;

import com.merkana.cache.config.CacheConfiguration;
import com.merkana.cache.config.props.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnProperty(name = "app.cache.type", havingValue = "redis")
@ConditionalOnProperty(name = "app.redis.enabled", havingValue = "true")
public class RedisCacheConfiguration {

    private final CacheProperties properties;

    public RedisCacheConfiguration(CacheProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(name = "app.cache.type", havingValue = "redis")
    @ConditionalOnProperty(name = "app.redis.enabled", havingValue = "true")
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        org.springframework.data.redis.cache.RedisCacheConfiguration defaultConfig = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(properties.getPermissionTtl())
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withCacheConfiguration(CacheConfiguration.PERMISSION_CACHE,
                        defaultConfig.entryTtl(properties.getPermissionTtl()))
                .withCacheConfiguration(CacheConfiguration.BULK_PERMISSION_CACHE,
                        defaultConfig.entryTtl(properties.getPermissionTtl()))
                .withCacheConfiguration(CacheConfiguration.USER_ROLES_CACHE,
                        defaultConfig.entryTtl(properties.getUserRolesTtl()))
                .withCacheConfiguration(CacheConfiguration.RESOURCE_CACHE,
                        defaultConfig.entryTtl(properties.getResourceTtl()))
                .build();
    }

}
