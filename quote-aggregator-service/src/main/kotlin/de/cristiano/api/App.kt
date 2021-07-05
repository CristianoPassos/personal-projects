package de.cristiano.api

import de.cristiano.api.config.traceid.EnableTraceId
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableTraceId
@SpringBootApplication
class App

fun main(args: Array<String>) {
	runApplication<App>(*args)
}
