package de.cristiano.api.websocket

import de.cristiano.api.service.repository.QuoteRepository
import de.cristiano.api.util.BaseIT
import de.cristiano.api.util.WebSocketUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class WebSocketQuotesHandlerIT : BaseIT() {

    @Autowired
    private lateinit var handler: WebSocketQuotesHandler

    @Autowired
    private lateinit var repository: QuoteRepository


    // Quote can be received after the Instrument is DELETED, but can not before first Instrument.
    // Another solution would be to delay Instrument allocation
    @Test
    fun `Should reject QUOTE when Instrument unknown`() {
        // When
        WebSocketUtils.stream(handler, webSocketSession, "/websocket/quotes/HV26074381K2.stream")

        // Then
        assertThat(repository.findAll()).isEmpty()
    }
}
