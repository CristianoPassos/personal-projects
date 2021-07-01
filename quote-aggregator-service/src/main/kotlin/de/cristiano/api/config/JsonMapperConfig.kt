package de.cristiano.api.config

import com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JsonMapperConfig {

    @Bean
    @Primary
    fun objectMapper() = ObjectMapper().also {
        it.propertyNamingStrategy = SNAKE_CASE
        it.registerKotlinModule()
        it.registerModule(Jdk8Module())
        it.registerModule(JavaTimeModule())
        it.enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
        it.configure(WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
