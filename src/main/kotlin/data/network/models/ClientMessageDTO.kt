package com.br.tictactoe.data.network.models

import com.br.tictactoe.data.network.enums.ClientMessageTypeDTO
import kotlinx.serialization.*

@Serializable
data class ClientMessageDTO(
    val type: ClientMessageTypeDTO,
    val position: Int? = null
)