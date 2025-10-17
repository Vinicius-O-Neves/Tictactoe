package com.br.tictactoe.domain.usecases

import com.br.tictactoe.data.network.mappers.GameDTOMapper
import com.br.tictactoe.data.network.mappers.PlayerDTOMapper
import com.br.tictactoe.data.network.models.ServerMessageDTO
import com.br.tictactoe.data.network.enums.ServerMessageTypeDTO
import com.br.tictactoe.data.network.models.GameDTO
import com.br.tictactoe.data.network.models.PlayerDTO

class GetServerMessageUseCase(
    private val playerDTOMapper: PlayerDTOMapper,
    private val gameDTOMapper: GameDTOMapper
) {

    operator fun invoke(
        game: GameDTO,
        player: PlayerDTO? = null,
        serverMessageTypeDTO: ServerMessageTypeDTO,
        exception: Exception? = null
    ) =
        serverMessageResolver(
            game = game,
            player = player,
            serverMessageTypeDTO = serverMessageTypeDTO,
            exception = exception
        )

    private fun serverMessageResolver(
        game: GameDTO,
        player: PlayerDTO?,
        serverMessageTypeDTO: ServerMessageTypeDTO,
        exception: Exception?
    ): ServerMessageDTO? {
        return when (serverMessageTypeDTO) {
            ServerMessageTypeDTO.ASSIGN -> {
                player?.let {
                    ServerMessageDTO.ServerMessageDTOPlayerAssign(
                        player = player
                    )
                }
            }

            ServerMessageTypeDTO.START -> {
                ServerMessageDTO.ServerMessageDTOGameStart(
                    game = game
                )
            }

            ServerMessageTypeDTO.DRAW -> {
                ServerMessageDTO.ServerMessageDTOGameDraw(
                    game = game
                )
            }

            ServerMessageTypeDTO.FINISHED -> {
                ServerMessageDTO.ServerMessageDTOGameFinished(
                    game = game,
                    winner = game.checkWinner()
                )
            }

            ServerMessageTypeDTO.ERROR -> {
                exception?.let {
                    ServerMessageDTO.ServerMessageDTOError(
                        exception = exception
                    )
                }
            }

            else -> {
                ServerMessageDTO.ServerMessageDTOGameStatus(
                    game = game
                )
            }
        }
    }

}