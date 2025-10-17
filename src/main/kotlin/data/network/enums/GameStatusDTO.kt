package com.br.tictactoe.data.network.enums

import kotlinx.serialization.Serializable

@Serializable
enum class GameStatusDTO {
    WAITING_FOR_PLAYER,
    IN_PROGRESS,
    FINISHED,
    DRAW,
    UNKNOWN
}