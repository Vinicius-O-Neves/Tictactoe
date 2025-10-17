package com.br.tictactoe.domain.usecases

import com.br.tictactoe.data.local.repository.InMemoryGameRepository

class DeleteGameUseCase(
    private val repository: InMemoryGameRepository
) {

    operator fun invoke(gameId: String) = deleteGame(gameId = gameId)

    private fun deleteGame(gameId: String) {
        repository.deleteGame(gameId)
    }

}