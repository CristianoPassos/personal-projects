package de.cristiano.api.util

import com.fasterxml.jackson.databind.ObjectMapper
import de.cristiano.api.service.repository.InstrumentRepository
import de.cristiano.api.service.repository.QuoteRepository
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.socket.WebSocketSession
import javax.annotation.PostConstruct

// Loading just one Spring Context makes the tests execution faster
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class BaseIT {

    @LocalServerPort
    protected val serverPort: Int = 0

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var webSocketSession: WebSocketSession

    @PostConstruct
    fun postConstruct() {
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setPort(serverPort)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.ANY)
            .build()

        RestAssured.config = RestAssuredConfig.config()
            .objectMapperConfig(ObjectMapperConfig().jackson2ObjectMapperFactory { _, _ -> objectMapper })

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    @BeforeEach
    fun beforeEach() {
        InstrumentRepository.INSTRUMENTS.clear()
        QuoteRepository.QUOTES.clear()
    }

}
