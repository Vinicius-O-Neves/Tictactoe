package com.br.tictactoe.data.network.mappers

import Mapper
import com.br.tictactoe.data.network.enums.GameStatusDTO
import com.br.tictactoe.domain.enums.GameStatus

class GameStatusDTOMapper : Mapper<GameStatus, GameStatusDTO> {

    override fun toObject(fromObject: GameStatus): GameStatusDTO {
        return GameStatusDTO.valueOf(fromObject.name)
    }

}