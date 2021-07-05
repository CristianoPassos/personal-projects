package de.cristiano.api.config

import de.cristiano.api.websocket.WebSocketInstrumentsHandler
import de.cristiano.api.websocket.WebSocketQuotesHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient


@Configuration
class WebSocketClientsStartup {

    // WebSocket connection is not monitored
    @Bean
    fun instruments(
        handler: WebSocketInstrumentsHandler,
        @Value("\${websocket.instruments.url}") url: String,
    ): WebSocketConnectionManager {
        val client = StandardWebSocketClient()
            .also { it.taskExecutor = ConcurrentTaskExecutor() }
        val manager = WebSocketConnectionManager(client, handler, url)

        manager.isAutoStartup = true

        return manager
    }

    @Bean
    @DependsOn("instruments")
    fun quotes(
        handler: WebSocketQuotesHandler,
        @Value("\${websocket.quotes.url}") url: String
    ): WebSocketConnectionManager {
        val client = StandardWebSocketClient()
            .also { it.taskExecutor = ConcurrentTaskExecutor() }
        val manager = WebSocketConnectionManager(client, handler, url)

        manager.isAutoStartup = true

        return manager
    }
}
