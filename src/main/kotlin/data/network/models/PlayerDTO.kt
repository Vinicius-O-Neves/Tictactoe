package com.br.tictactoe.data.network.models

import com.br.tictactoe.data.network.enums.PlayerSymbolDTO
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(
    var symbol: PlayerSymbolDTO? = null
)