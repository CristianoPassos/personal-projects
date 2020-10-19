package de.cristiano.ecommerce.voucher.api

import de.cristiano.ecommerce.voucher.common.BaseTest
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus.OK
import java.time.LocalDateTime.now

class VoucherControllerTest : BaseTest() {

    private val path = "/vouchers"

    @Test
    fun `Post vouchers works`() {
        Given {
            contentType(JSON)
            body(NewVoucherRequest(2.2, now()))
        } When {
            post(path)
        } Then {
            statusCode(equalTo(OK.value()))
        }
    }

    @Test
    fun `Get voucher works`() {
        When {
            get("$path/hash")
        } Then {
            statusCode(equalTo(OK.value()))
        }
    }
}
