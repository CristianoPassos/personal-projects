package de.cristiano.api.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import de.cristiano.api.config.traceid.TraceId
import de.cristiano.api.domain.Instrument
import de.cristiano.api.service.InstrumentService
import de.cristiano.api.service.repository.InstrumentRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession

@Component
class WebSocketInstrumentsHandler(
    private val objectMapper: ObjectMapper,
    private val service: InstrumentService
) : WebSocketHandler {

    private val log = KotlinLogging.logger {}

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.debug("Started WebSocket client for Instruments")

        log.info("Cleaning Instruments")
        InstrumentRepository.INSTRUMENTS.clear()
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        TraceId.generateAndSet()

        val payload = message.payload

        try {
            val response = objectMapper.readValue(payload.toString(), InstrumentResponse::class.java)
            val instrument = response.data.toInstrument()

            when (response.type) {
                "ADD" -> service.save(instrument)
                "DELETE" -> service.deleteByIsin(instrument.isin)
                else -> error("Invalid instrument type: ${response.type}")
            }

        } catch (ex: Exception) {
            log.error("Failed to process Instrument: $payload", ex)
        } finally {
            TraceId.unset()
        }
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        log.error(exception.localizedMessage, exception)
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        log.debug("Closed WebSocket client for Instruments")
    }

    override fun supportsPartialMessages() = false
}

data class InstrumentResponse(val data: InstrumentDto, val type: String)
data class InstrumentDto(val description: String, val isin: String)

fun InstrumentDto.toInstrument() = Instrument(description = description, isin = isin)
