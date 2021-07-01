package de.cristiano.api.config

import de.cristiano.api.util.MutableClock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ClockConfig {

    @Bean
    @Primary
    fun clock(): MutableClock = MutableClock.epochUTC()

}
