package com.br.tictactoe.data.network.models

import com.br.tictactoe.data.network.enums.PlayerSymbolDTO
import com.br.tictactoe.data.network.enums.ServerMessageTypeDTO
import kotlinx.serialization.Serializable

@Serializable
sealed class ServerMessageDTO(val type: ServerMessageTypeDTO) {

    data class ServerMessageDTOGameStatus(
        val game: GameDTO
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.STATUS)

    data class ServerMessageDTOPlayerAssign(
        val player: PlayerDTO
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.ASSIGN)

    data class ServerMessageDTOGameStart(
        val game: GameDTO
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.START)

    data class ServerMessageDTOGameDraw(
        val game: GameDTO
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.DRAW)

    data class ServerMessageDTOGameFinished(
        val game: GameDTO,
        val winner: PlayerSymbolDTO?
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.FINISHED)

    data class ServerMessageDTOError(
        val exception: Exception
    ) : ServerMessageDTO(type = ServerMessageTypeDTO.ERROR)

}