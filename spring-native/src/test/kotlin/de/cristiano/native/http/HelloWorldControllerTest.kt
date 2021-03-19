package de.cristiano.native.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort


@SpringBootTest(webEnvironment = RANDOM_PORT)
class HelloWorldControllerTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `Please say Hello!`() {
        // When
        val response = restTemplate.getForObject("http://localhost:$port/hello", String::class.java)

        // Then
        assertThat(response).isEqualTo("Hello!")
    }

}
