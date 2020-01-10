package de.cristiano.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

@Component
public class RedisServerStartup implements CommandLineRunner {

    @Autowired
    private RedisServer redisServer;

    @Override
    public void run(final String... args) throws Exception {
        if (!redisServer.isActive()) {
            redisServer.start();
        }
    }
}
