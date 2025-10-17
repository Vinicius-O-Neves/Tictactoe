package com.br.tictactoe.domain.models

import com.br.tictactoe.domain.enums.GameStatus
import com.br.tictactoe.domain.enums.PlayerSymbol
import java.util.UUID

data class Game(
    val id: String = UUID.randomUUID().toString(),
    val board: MutableList<PlayerSymbol?> = MutableList(9) { null },
    var turn: PlayerSymbol = PlayerSymbol.X,
    val players: MutableList<Player> = mutableListOf(),
    var status: GameStatus = GameStatus.WAITING_FOR_PLAYER
) {
    fun checkWinner(): PlayerSymbol? {
        val lines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )
        return lines.firstNotNullOfOrNull { (a, b, c) ->
            if (board[a] != null && board[a] == board[b] && board[a] == board[c]) board[a] else null
        }
    }
}