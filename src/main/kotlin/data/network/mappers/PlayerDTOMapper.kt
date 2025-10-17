package com.br.tictactoe.data.network.mappers

import Mapper
import com.br.tictactoe.domain.models.Player
import com.br.tictactoe.data.network.models.PlayerDTO

class PlayerDTOMapper : Mapper<Player, PlayerDTO> {

    override fun toObject(fromObject: Player): PlayerDTO {
        return PlayerDTO(
            symbol = fromObject.symbol
        )
    }

}