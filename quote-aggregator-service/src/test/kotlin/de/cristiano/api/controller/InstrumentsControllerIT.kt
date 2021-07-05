package de.cristiano.api.controller

import de.cristiano.api.controller.InstrumentsController.Companion.RESOURCE
import de.cristiano.api.util.BaseIT
import de.cristiano.api.util.WebSocketUtils.stream
import de.cristiano.api.websocket.WebSocketInstrumentsHandler
import de.cristiano.api.websocket.WebSocketQuotesHandler
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class InstrumentsControllerIT : BaseIT() {

    @Autowired
    private lateinit var quotesHandler: WebSocketQuotesHandler

    @Autowired
    private lateinit var instrumentsHandler: WebSocketInstrumentsHandler

    @Test
    fun `GET instrument should return empty when no instruments`() {
        When {
            get(RESOURCE)
        } Then {
            statusCode(SC_OK)

            body("instruments.size()", equalTo(0))
        }
    }

    @Test
    fun `GET instrument should return null price if no price received`() {
        // Given
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/on_connect/instruments.stream")

        When {
            get(RESOURCE)
        } Then {
            statusCode(SC_OK)

            body("instruments[0].lastPrice", nullValue())
            body("instruments.size()", equalTo(3))
        }
    }

    @Test
    fun `GET instrument should not return DELETED instruments`() {
        // Given
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/on_connect/instruments.stream")
        stream(quotesHandler, webSocketSession, "/websocket/quotes/HV26074381K2.stream")
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/delete/HV26074381K2.stream")

        When {
            get(RESOURCE)
        } Then {
            statusCode(SC_OK)

            body("instruments.isin", containsInAnyOrder("DS446O2S2837", "CMH137228473"))
            body("instruments.size()", equalTo(2))
        }
    }

    @Test
    fun `GET DELETED instrument should return if ADDED again`() {
        // Given
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/on_connect/instruments.stream")
        stream(quotesHandler, webSocketSession, "/websocket/quotes/HV26074381K2.stream")
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/delete/HV26074381K2.stream")
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/add/HV26074381K2_REUSE_ISIN.stream")

        When {
            get(RESOURCE)
        } Then {
            statusCode(SC_OK)

            body("instruments.isin", hasItem( "HV26074381K2"))
            body("instruments.description", hasItem("REUSING SAME ISIN"))
            body("instruments.size()", equalTo(3))
        }
    }


    @Test
    fun `GET instrument should return latest price`() {
        // Given
        stream(instrumentsHandler, webSocketSession, "/websocket/instruments/add/HV26074381K2_REUSE_ISIN.stream")
        stream(quotesHandler, webSocketSession, "/websocket/quotes/HV26074381K2.stream")

        When {
            get(RESOURCE)
        } Then {
            statusCode(SC_OK)

            body("instruments[0].description", equalTo("REUSING SAME ISIN"))
            body("instruments[0].isin", equalTo("HV26074381K2"))
            body("instruments[0].last_price", equalTo(230.3166F))

            body("instruments.size()", equalTo(1))
        }
    }
}
