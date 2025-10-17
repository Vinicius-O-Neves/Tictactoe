package com.br.tictactoe.domain.usecases

import com.br.tictactoe.data.network.mappers.GameDTOMapper
import com.br.tictactoe.data.network.mappers.PlayerDTOMapper
import com.br.tictactoe.domain.enums.ServerMessageType
import com.br.tictactoe.data.network.mappers.ServerMessageTypeDTOMapper
import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.domain.models.Player
import io.ktor.server.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SendServerMessageUseCase(
    private val playerDTOMapper: PlayerDTOMapper,
    private val gameDTOMapper: GameDTOMapper,
    private val serverMessageTypeDTOMapper: ServerMessageTypeDTOMapper,
    private val getServerMessageUseCase: GetServerMessageUseCase
) {

    suspend operator fun invoke(
        game: Game,
        player: Player,
        serverMessageType: ServerMessageType,
        exception: Exception? = null
    ) {
        sendServerMessage(
            game = game,
            player = player,
            serverMessageType = serverMessageType,
            exception = exception
        )
    }

    private suspend fun sendServerMessage(
        game: Game,
        player: Player,
        serverMessageType: ServerMessageType,
        exception: Exception? = null
    ) {
        val mappedGameDTO = gameDTOMapper.toObject(game)
        val mappedPlayerDTO = playerDTOMapper.toObject(player)
        val mappedServerMessageTypeDTO = serverMessageTypeDTOMapper.toObject(serverMessageType)

        val serverMessageDTO = getServerMessageUseCase(
            game = mappedGameDTO,
            player = mappedPlayerDTO,
            serverMessageTypeDTO = mappedServerMessageTypeDTO,
            exception = exception
        )

        val json = Json {
            classDiscriminator = "messageType"
            prettyPrint = true
            encodeDefaults = true
        }

        if (serverMessageType == ServerMessageType.ERROR) {
            player.session.sendSerialized(json.encodeToString(serverMessageDTO))
        } else {
            game.players.forEach {
                it.session.sendSerialized(json.encodeToString(serverMessageDTO))
            }
        }
    }

}