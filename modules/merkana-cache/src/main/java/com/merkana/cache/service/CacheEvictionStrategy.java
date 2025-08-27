package com.merkana.cache.service;

import org.springframework.cache.Cache;
import org.springframework.lang.NonNull;

public interface CacheEvictionStrategy {

    boolean supports(Object nativeCache);

    int evictByPattern(@NonNull Cache cache, @NonNull Object nativeCache, @NonNull String pattern);

    default int getOrder() {
        return 100;
    }

}
