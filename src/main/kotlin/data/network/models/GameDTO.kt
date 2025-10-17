package com.br.tictactoe.data.network.models

import com.br.tictactoe.data.network.enums.GameStatusDTO
import com.br.tictactoe.data.network.enums.PlayerSymbolDTO
import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(
    val id: String,
    val board: List<PlayerSymbolDTO?>,
    val turn: PlayerSymbolDTO,
    val players: List<PlayerDTO>,
    val status: GameStatusDTO
) {
    fun checkWinner(): PlayerSymbolDTO? {
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