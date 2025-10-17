package com.br.tictactoe.data.local.repository

import com.br.tictactoe.domain.enums.GameStatus
import com.br.tictactoe.domain.enums.PlayerSymbol
import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.domain.models.Player
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class InMemoryGameRepositoryImpl : InMemoryGameRepository {
    private val games = ConcurrentHashMap<String, Game>()
    private val waitingPlayers = LinkedList<Player>()

    override fun createGame(player: Player): Game {
        player.symbol = PlayerSymbol.X

        val game = Game()
        game.players.add(player)

        games[game.id] = game

        waitingPlayers.add(player)

        return game
    }

    override fun joinGame(player: Player, game: Game): Game {
        if (waitingPlayers.isEmpty()) {
            return createGame(player = player)
        }

        player.symbol = PlayerSymbol.O

        game.players.add(player)
        game.status = GameStatus.IN_PROGRESS

        return game
    }

    override fun findAvailableGame(): Game? {
        return games.filterValues { game ->
            game.players.size == 1
        }.values.firstOrNull()
    }

    override fun deleteGame(id: String) {
        games.remove(id)
    }
}