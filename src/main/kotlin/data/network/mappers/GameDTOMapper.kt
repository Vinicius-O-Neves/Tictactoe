package com.br.tictactoe.data.network.mappers

import Mapper
import com.br.tictactoe.domain.models.Game
import com.br.tictactoe.data.network.models.GameDTO

class GameDTOMapper(
    private val playerDTOMapper: PlayerDTOMapper,
    private val playerSymbolDTOMapper: PlayerSymbolDTOMapper,
    private val gameStatusDTOMapper: GameStatusDTOMapper
) : Mapper<Game, GameDTO> {

    override fun toObject(fromObject: Game): GameDTO {
        val mappedPlayersDTO = playerDTOMapper.toObjectList(fromObject.players)
        val mappedTurnDTO = playerSymbolDTOMapper.toObject(fromObject.turn)
        val mappedBoardDTO = playerSymbolDTOMapper.toObjectNullableList(fromObject.board)
        val mappedGameStatusDTO = gameStatusDTOMapper.toObject(fromObject.status)

        return GameDTO(
            id = fromObject.id,
            board = mappedBoardDTO,
            turn = mappedTurnDTO,
            players = mappedPlayersDTO,
            status = mappedGameStatusDTO
        )
    }

}