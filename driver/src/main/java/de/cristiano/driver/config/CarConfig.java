package de.cristiano.driver.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Getter
@Configuration
@ConfigurationProperties(prefix = "com.driver.car")
public class CarConfig
{
    private final Set<Integer> validSeatCounts = new HashSet<>();
}
