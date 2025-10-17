package com.br.tictactoe.domain

import com.br.tictactoe.data.network.models.ClientMessageDTO
import com.br.tictactoe.domain.enums.GameStatus
import com.br.tictactoe.domain.models.Player
import com.br.tictactoe.domain.enums.ClientMessageType
import com.br.tictactoe.domain.enums.ServerMessageType
import com.br.tictactoe.domain.mappers.ClientMessageMapper
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
    private val deleteGameUseCase: DeleteGameUseCase,
    private val clientMessageMapper: ClientMessageMapper
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
                    val msg = json.decodeFromString<ClientMessageDTO>(frame.readText())

                    val mappedClientMessage = clientMessageMapper.toObject(msg)

                    if (mappedClientMessage.type == ClientMessageType.MOVE && mappedClientMessage.position != null) {
                        val gameStatus = try {
                            service.makeMove(game, player, mappedClientMessage.position)
                        } catch (exception: Exception) {
                            sendServerMessageUseCase(
                                game = game,
                                player = player,
                                serverMessageType = ServerMessageType.ERROR,
                                exception = exception
                            )

                            null
                        }

                        if (gameStatus == null) return@consumeEach

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
        } catch (exception: Exception) {
            sendServerMessageUseCase(
                game = game,
                player = player,
                serverMessageType = ServerMessageType.ERROR,
                exception = exception
            )

            return@handleConnection
        } finally {
            println("Client disconnected")
        }
    }

}