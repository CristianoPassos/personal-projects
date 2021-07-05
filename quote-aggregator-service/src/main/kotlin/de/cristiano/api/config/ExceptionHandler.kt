package de.cristiano.api.config

import de.cristiano.api.config.traceid.TraceId
import mu.KotlinLogging
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = KotlinLogging.logger {}

    @ResponseBody
    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException) =
        Problem(NOT_FOUND.value(), NOT_FOUND.reasonPhrase, ex.localizedMessage)

    @ResponseBody
    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    fun handleUncaughtException(ex: Exception): Problem {
        log.error(ex.message, ex)

        return Problem(INTERNAL_SERVER_ERROR.value(), NOT_FOUND.reasonPhrase, ERROR_MESSAGE.format(TraceId.get()))
    }

    companion object {
        private const val ERROR_MESSAGE =
            "Ups... our server could not process the request. Please try again or report with this ID: %s"
    }
}

data class Problem(
    val status: Int,
    val title: String,
    val detail: String
)

class NotFoundException(message: String) : RuntimeException(message)
