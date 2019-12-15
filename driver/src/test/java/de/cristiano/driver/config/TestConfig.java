package de.cristiano.driver.config;

import io.restassured.RestAssured;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig
{

    @PostConstruct
    public void configTestEnvironment()
    {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
