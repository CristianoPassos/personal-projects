package de.cristiano.ecommerce.voucher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class VoucherApplication

fun main(args: Array<String>) {
	runApplication<VoucherApplication>(*args)
}
