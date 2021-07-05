package de.cristiano.api.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import de.cristiano.api.config.traceid.TraceId
import de.cristiano.api.domain.Quote
import de.cristiano.api.service.QuoteService
import de.cristiano.api.service.repository.QuoteRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

@Component
class WebSocketQuotesHandler(
    private val service: QuoteService,
    private val objectMapper: ObjectMapper
) : WebSocketHandler {

    private val log = KotlinLogging.logger {}

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.debug("Started WebSocket client for Quotes")

        log.info("Cleaning Quotes")
        QuoteRepository.QUOTES.clear()
        TimeUnit.SECONDS.sleep(5) // Hack, allows Instruments be loaded.
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        TraceId.generateAndSet()
        val payload = message.payload

        try {
            val response = objectMapper.readValue(payload.toString(), QuoteResponse::class.java)
            val quote = response.data.toQuote()

            when (response.type) {
                "QUOTE" -> service.save(quote)
                else -> error("Invalid instrument type: ${response.type}")
            }

        } catch (ex: Exception) {
            log.error("Failed to process Quote: $payload", ex)
        } finally {
            TraceId.unset()
        }
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        log.error(exception.message, exception)
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        log.info("Closed WebSocket client for Quotes")
    }

    override fun supportsPartialMessages() = false
}

data class QuoteResponse(val data: QuoteDto, val type: String)
data class QuoteDto(val price: BigDecimal, val isin: String)

fun QuoteDto.toQuote() = Quote(price = price, isin = isin)
