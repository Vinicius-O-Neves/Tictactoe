package com.br.tictactoe

import com.br.tictactoe.di.injectGameModules
import com.br.tictactoe.domain.GameController
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import java.time.Duration

class Main {

    init {
        run()
    }

    private fun run() {
        val port = System.getenv("PORT")?.toInt() ?: 8080

        embeddedServer(Netty, port = port, host = "0.0.0.0") {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)

                pingPeriod = Duration.ofSeconds(15)
                timeout = Duration.ofSeconds(30)
            }

            install(Koin) {
                modules(injectGameModules)
            }

            routing {
                val controller: GameController by inject()

                webSocket(getPath(path = "/tictactoe")) {
                    controller.handleConnection(session = this)
                }
            }
        }.start(wait = true)
    }

    private fun getPath(path: String): String {
        val baseUrl = "wss://tictactoe-2yck.onrender.com"

        return "$baseUrl$path"
    }
}

fun main() {
    Main()
}