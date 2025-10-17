package com.br.tictactoe.domain

import com.br.tictactoe.domain.enums.GameStatus
import com.br.tictactoe.domain.enums.MoveResultException
import com.br.tictactoe.domain.models.Player
import com.br.tictactoe.domain.enums.ClientMessageType
import com.br.tictactoe.domain.enums.ServerMessageType
import com.br.tictactoe.domain.models.ClientMessage
import com.br.tictactoe.domain.usecases.DeleteGameUseCase
import com.br.tictactoe.domain.usecases.JoinGameUseCase
import com.br.tictactoe.domain.usecases.SendServerMessageUseCase
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.json.Json

class GameController(
    private val service: GameService,
    private val sendServerMessageUseCase: SendServerMessageUseCase,
    private val joinGameUseCase: JoinGameUseCase,
    private val deleteGameUseCase: DeleteGameUseCase
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun handleConnection(session: DefaultWebSocketServerSession) {
        val player = Player(session)

        val game = joinGameUseCase(player)

        sendServerMessageUseCase(
            game = game,
            player = player,
            serverMessageType = ServerMessageType.ASSIGN
        )

        if (game.status == GameStatus.IN_PROGRESS) {
            sendServerMessageUseCase(
                game = game,
                player = player,
                serverMessageType = ServerMessageType.START
            )
        }

        try {
            session.incoming.consumeEach { frame ->
                if (frame is Frame.Text) {
                    val msg = json.decodeFromString<ClientMessage>(frame.readText())

                    if (msg.type == ClientMessageType.MOVE && msg.position != null) {
                        val gameStatus = service.makeMove(game, player, msg.position)

                        when (gameStatus) {
                            GameStatus.IN_PROGRESS -> {
                                sendServerMessageUseCase(
                                    game = game,
                                    player = player,
                                    serverMessageType = ServerMessageType.STATUS
                                )
                            }

                            GameStatus.DRAW -> {
                                sendServerMessageUseCase(
                                    game = game,
                                    player = player,
                                    serverMessageType = ServerMessageType.DRAW
                                )

                                deleteGameUseCase(game.id)
                            }

                            GameStatus.FINISHED -> {
                                sendServerMessageUseCase(
                                    game = game,
                                    player = player,
                                    serverMessageType = ServerMessageType.FINISHED
                                )

                                deleteGameUseCase(game.id)
                            }

                            else -> {
                                sendServerMessageUseCase(
                                    game = game,
                                    player = player,
                                    serverMessageType = ServerMessageType.STATUS
                                )
                            }
                        }
                    }
                }
            }
        } catch (moveResultException: MoveResultException) {
            sendServerMessageUseCase(
                game = game,
                player = player,
                serverMessageType = ServerMessageType.ERROR,
                exception = moveResultException
            )
        } finally {
            println("Client disconnected")
        }
    }

}