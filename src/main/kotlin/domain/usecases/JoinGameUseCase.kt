package com.br.tictactoe.domain.usecases

import com.br.tictactoe.data.local.repository.InMemoryGameRepository
import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.domain.models.Player

class JoinGameUseCase(
    private val repository: InMemoryGameRepository
) {

    operator fun invoke(player: Player) = joinGame(player = player)

    private fun joinGame(player: Player): Game {
       return synchronized(repository) {
            repository.findAvailableGame()?.let { availableGame ->
                repository.joinGame(player = player, game = availableGame)
            } ?: repository.createGame(player = player)
        }
    }

}