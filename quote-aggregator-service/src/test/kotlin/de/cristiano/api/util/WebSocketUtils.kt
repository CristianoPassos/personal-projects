package de.cristiano.api.util

import de.cristiano.api.util.FileUtils.readLines
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.TimeUnit

object WebSocketUtils {

    fun stream(handler: WebSocketHandler, session: WebSocketSession, streamFilePath: String) = readLines(streamFilePath)
        .map { TextMessage(it) }
        .forEach {
            handler.handleMessage(session, it)
            TimeUnit.MILLISECONDS.sleep(1) // Even Instant is not enough to provide unique Timestamps.
        }
}
