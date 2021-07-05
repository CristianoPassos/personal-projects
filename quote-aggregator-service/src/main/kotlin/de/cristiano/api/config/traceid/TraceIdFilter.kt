package de.cristiano.api.config.traceid

import de.cristiano.api.config.traceid.TraceId.TRACE_HEADER
import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(HIGHEST_PRECEDENCE)
class TraceIdFilter : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) =
        try {
            response.setHeader(TRACE_HEADER, retrieveOrNew(request))
        } finally {
            chain.doFilter(request, response)

            TraceId.unset()
        }

    private fun retrieveOrNew(request: HttpServletRequest): String {
        val traceId = request.getHeader(TRACE_HEADER) ?: UUID.randomUUID().toString()

        return traceId.also { TraceId.set(it) }
    }

}
