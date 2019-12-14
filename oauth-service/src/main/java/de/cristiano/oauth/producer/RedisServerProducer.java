package de.cristiano.oauth.producer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Slf4j
@Component
public class RedisServerProducer {
    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @SneakyThrows
    @Scope(SCOPE_SINGLETON)
    @Bean(destroyMethod = "stop")
    public RedisServer redisServer() {
        if (redisServer == null) {
            log.info("Starting Redis server on port: [{}}", redisPort);
            redisServer = new RedisServer(redisPort);
        }
        return redisServer;
    }
}
