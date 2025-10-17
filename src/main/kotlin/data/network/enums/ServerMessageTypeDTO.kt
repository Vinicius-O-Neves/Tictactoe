package com.br.tictactoe.data.network.enums

import kotlinx.serialization.Serializable

@Serializable
enum class ServerMessageTypeDTO {
    ASSIGN,
    START,
    FINISHED,
    DRAW,
    ERROR,
    STATUS
}