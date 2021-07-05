package de.cristiano.api.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import de.cristiano.api.domain.Quote
import java.io.InputStream

object FileUtils {
    fun readAsStream(filePath: String): InputStream? = this::class.java.getResourceAsStream(filePath)

    fun readLines(filePath: String): List<String> {
        val stream = readAsStream(filePath) ?: error("File not found: $filePath")

        return stream.bufferedReader().readLines()
    }

    fun <T> readAsObject(filePath: String, typeRef: TypeReference<T>, objectMapper: ObjectMapper): T =
        objectMapper.readValue(readAsStream(filePath), typeRef)

    val LIST_QUOTE_TYPE_REF = object : TypeReference<List<Quote>>() {}
}
