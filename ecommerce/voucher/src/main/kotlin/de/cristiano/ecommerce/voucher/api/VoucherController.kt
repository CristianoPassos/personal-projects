package de.cristiano.ecommerce.voucher.api

import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("vouchers")
class VoucherController {
    private val log = LoggerFactory.getLogger(VoucherController::class.java)

    @PostMapping
    fun newVoucher(@RequestBody @Validated newVoucherRequest: NewVoucherRequest): NewVoucherResponse {
        log.info("Request received: $newVoucherRequest")

        val newVoucherResponse = NewVoucherResponse(2.2, LocalDateTime.now())
        log.info("New Voucher created: $newVoucherResponse")

        return newVoucherResponse
    }

    @GetMapping("/{id}")
    fun getVoucher(@PathVariable id: String): VoucherDto {
        return VoucherDto(12.2)
    }
}

data class NewVoucherRequest(
    val value: Double,
    @DateTimeFormat
    val validUntil: LocalDateTime
)

data class NewVoucherResponse(val value: Double, val validUntil: LocalDateTime)

data class VoucherDto(val value: Double)