package de.cristiano.driver.util;

import de.cristiano.driver.DriverServerApplicantTestApplication;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Inherited
@Documented
@Target(TYPE)
@Retention(RUNTIME)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = DriverServerApplicantTestApplication.class)
@Sql(scripts = "classpath:db/003_clean_data.sql", executionPhase = AFTER_TEST_METHOD)
@Sql(scripts = "classpath:db/002_insert_data.sql", executionPhase = BEFORE_TEST_METHOD)
public @interface IntegrationTest
{
}