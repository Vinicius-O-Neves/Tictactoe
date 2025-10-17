package com.br.tictactoe.data.network.models

import com.br.tictactoe.data.network.enums.PlayerSymbolDTO
import com.br.tictactoe.data.network.enums.ServerMessageTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ServerMessageDTO {

    @Serializable
    @SerialName("STATUS")
    data class ServerMessageDTOGameStatus(
        val game: GameDTO
    ) : ServerMessageDTO()

    @Serializable
    @SerialName("ASSIGN")
    data class ServerMessageDTOPlayerAssign(
        val player: PlayerDTO
    ) : ServerMessageDTO()

    @Serializable
    @SerialName("START")
    data class ServerMessageDTOGameStart(
        val game: GameDTO
    ) : ServerMessageDTO()

    @Serializable
    @SerialName("DRAW")
    data class ServerMessageDTOGameDraw(
        val game: GameDTO
    ) : ServerMessageDTO()

    @Serializable
    @SerialName("FINISHED")
    data class ServerMessageDTOGameFinished(
        val game: GameDTO,
        val winner: PlayerSymbolDTO?
    ) : ServerMessageDTO()

    @Serializable
    @SerialName("ERROR")
    data class ServerMessageDTOError(
        val exception: String
    ) : ServerMessageDTO()

}