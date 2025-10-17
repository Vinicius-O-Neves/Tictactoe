package com.br.tictactoe

import com.br.tictactoe.di.injectGameModules
import com.br.tictactoe.domain.GameController
import com.br.tictactoe.domain.models.Game
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(30)
        }

        install(Koin) {
            modules(injectGameModules())
        }

        routing {
            val controller: GameController by inject()

            webSocket("/tictactoe") {

            }
        }
    }.start(wait = true)
}