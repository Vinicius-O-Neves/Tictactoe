package com.br.tictactoe.domain.models

import com.br.tictactoe.domain.enums.ClientMessageType

data class ClientMessage(
    val type: ClientMessageType,
    val position: Int? = null
)