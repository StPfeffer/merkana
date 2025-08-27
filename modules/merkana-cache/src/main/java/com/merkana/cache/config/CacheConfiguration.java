package com.merkana.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfiguration {

    public static final String PERMISSION_CACHE = "permissions";

    public static final String BULK_PERMISSION_CACHE = "bulk-permissions";

    public static final String USER_ROLES_CACHE = "user-roles";

    public static final String RESOURCE_CACHE = "resources";

    @Bean
    @Primary
    @ConditionalOnProperty(name = "app.cache.type", havingValue = "memory", matchIfMissing = true)
    public CacheManager memoryCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(Arrays.asList(
                PERMISSION_CACHE,
                BULK_PERMISSION_CACHE,
                USER_ROLES_CACHE,
                RESOURCE_CACHE
        ));
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

}
