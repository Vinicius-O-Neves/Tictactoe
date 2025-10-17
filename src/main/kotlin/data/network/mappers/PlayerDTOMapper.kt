package com.br.tictactoe.data.network.mappers

import Mapper
import com.br.tictactoe.domain.models.Player
import com.br.tictactoe.data.network.models.PlayerDTO

class PlayerDTOMapper(
    private val playerSymbolDTOMapper: PlayerSymbolDTOMapper
) : Mapper<Player, PlayerDTO> {

    override fun toObject(fromObject: Player): PlayerDTO {
        val mappedPlayerSymbolDTOMapper = fromObject.symbol?.let { symbol ->
            playerSymbolDTOMapper.toObject(symbol)
        }

        return PlayerDTO(
            symbol = mappedPlayerSymbolDTOMapper
        )
    }

}