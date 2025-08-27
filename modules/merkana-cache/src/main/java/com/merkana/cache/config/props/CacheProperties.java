package com.merkana.cache.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.cache")
public class CacheProperties {

    /**
     * Tipo de cache: memory, redis
     */
    private CacheType type = CacheType.MEMORY;

    /**
     * Configurações de TTL para diferentes caches
     */
    private Duration permissionTtl = Duration.ofMinutes(30);

    private Duration userRolesTtl = Duration.ofMinutes(15);

    private Duration resourceTtl = Duration.ofHours(1);

    /**
     * Configurações de tamanho máximo (para cache em memória)
     */
    private int maxSizePermissions = 10000;

    private int maxSizeUserRoles = 5000;

    private int maxSizeResources = 1000;

    /**
     * Habilita/desabilita o cache
     */
    private boolean enabled = true;

    /**
     * Habilita logs de debug do cache
     */
    private boolean debugEnabled = false;

    /**
     * Configurações de warming do cache
     */
    private Warming warming = new Warming();

    @Getter
    @Setter
    public static class Warming {

        /**
         * Habilita aquecimento automático do cache na inicialização
         */
        private boolean enabled = false;

        /**
         * Delay antes de iniciar o aquecimento
         */
        private Duration delay = Duration.ofSeconds(30);

        /**
         * Intervalo para re-aquecimento periódico
         */
        private Duration interval = Duration.ofHours(6);

    }

    public enum CacheType {

        MEMORY,
        REDIS

    }

}
