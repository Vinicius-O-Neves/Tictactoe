package com.br.tictactoe.domain

import LoggerExtensions
import com.br.tictactoe.domain.enums.GameStatus
import com.br.tictactoe.domain.enums.MoveResultException
import com.br.tictactoe.domain.enums.getOtherPlayer
import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.domain.models.Player

class GameService {

    fun makeMove(
        game: Game, player: Player, position: Int
    ): GameStatus {
        LoggerExtensions.i("Making move of player ${player.symbol} at position $position")

        when {
            position !in 0..8 -> throw MoveResultException.OutOfRange
            game.turn != player.symbol -> throw MoveResultException.InvalidTurn
            game.board[position] != null -> throw MoveResultException.InvalidPosition
            else -> {}
        }

        game.board[position] = player.symbol

        player.symbol?.getOtherPlayer()?.let { nextPlayer ->
            game.turn = nextPlayer
        }

        return when (game.checkWinner()) {
            null -> if (game.board.all { it != null }) GameStatus.DRAW else GameStatus.IN_PROGRESS
            else -> GameStatus.FINISHED
        }
    }

}