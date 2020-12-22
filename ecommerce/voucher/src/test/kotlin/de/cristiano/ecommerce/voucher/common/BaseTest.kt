package de.cristiano.ecommerce.voucher.common;

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BaseTest {

    @LocalServerPort
    private lateinit var serverPort: Integer

    @BeforeEach
    fun beforeEach() {
        RestAssured.port = serverPort.toInt()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }
}
