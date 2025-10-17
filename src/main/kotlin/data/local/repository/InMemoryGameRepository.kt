package com.br.tictactoe.data.local.repository

import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.domain.models.Player

interface InMemoryGameRepository {
    fun createGame(player: Player): Game
    fun joinGame(player: Player, game: Game): Game
    fun findAvailableGame(): Game?
    fun deleteGame(id: String)
}