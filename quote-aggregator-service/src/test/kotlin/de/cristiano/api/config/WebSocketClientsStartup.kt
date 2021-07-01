package de.cristiano.api.config

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.socket.client.WebSocketConnectionManager


@Configuration
class WebSocketClientsStartup {

    @MockBean
    private lateinit var connectionManager: WebSocketConnectionManager

    @Bean
    @Primary
    fun webSocketConnectionManagerMock() = connectionManager
}
