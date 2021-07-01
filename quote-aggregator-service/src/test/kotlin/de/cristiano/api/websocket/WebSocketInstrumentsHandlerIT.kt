package de.cristiano.api.websocket

import de.cristiano.api.service.repository.InstrumentRepository
import de.cristiano.api.util.BaseIT
import de.cristiano.api.util.WebSocketUtils.stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class WebSocketInstrumentsHandlerIT : BaseIT(){

    @Autowired
    private lateinit var handler : WebSocketInstrumentsHandler

    @Autowired
    private lateinit var repository: InstrumentRepository

    @Test
    fun `Should soft delete Instrument`() {
        // Given
        stream(handler, webSocketSession, "/websocket/instruments/on_connect/instruments.stream")

        // When
        stream(handler, webSocketSession, "/websocket/instruments/delete/HV26074381K2.stream")

        // Then
        val instruments = repository.findByIsin("HV26074381K2")

        assertThat(instruments).hasSize(1)
        assertThat(instruments.first().deletedAt).isNotNull
    }
}
