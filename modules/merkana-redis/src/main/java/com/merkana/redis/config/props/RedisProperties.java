package com.merkana.redis.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.redis")
public class RedisProperties {

    /**
     * Determina se o Redis está ativo.
     * <p>
     * O valor padrão é <codde>false</codde>.
     */
    private boolean enabled = false;

}
