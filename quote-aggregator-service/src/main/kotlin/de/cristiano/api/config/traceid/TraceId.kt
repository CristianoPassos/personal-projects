package de.cristiano.api.config.traceid

import org.slf4j.MDC
import java.util.UUID

object TraceId {

    const val TRACE_HEADER = "X-Trace-Id"

    fun unset() = MDC.remove(TRACE_HEADER)

    fun get(): String = MDC.get(TRACE_HEADER)

    fun set(flowId: String): String = flowId.also { MDC.put(TRACE_HEADER, it) }

    fun generateAndSet(): String = set(UUID.randomUUID().toString())
}
