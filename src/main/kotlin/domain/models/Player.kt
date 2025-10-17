package com.br.tictactoe.domain.models

import com.br.tictactoe.domain.enums.PlayerSymbol
import io.ktor.server.websocket.*

data class Player(
    val session: DefaultWebSocketServerSession,
    var symbol: PlayerSymbol? = null
)