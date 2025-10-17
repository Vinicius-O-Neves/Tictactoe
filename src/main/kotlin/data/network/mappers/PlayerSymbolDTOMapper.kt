package com.br.tictactoe.data.network.mappers

import Mapper
import MapperNullable
import com.br.tictactoe.data.network.enums.PlayerSymbolDTO
import com.br.tictactoe.domain.enums.PlayerSymbol

class PlayerSymbolDTOMapper : Mapper<PlayerSymbol, PlayerSymbolDTO>,
    MapperNullable<PlayerSymbol?, PlayerSymbolDTO?> {

    override fun toObject(fromObject: PlayerSymbol): PlayerSymbolDTO {
        return PlayerSymbolDTO.valueOf(fromObject.name)
    }

    override fun toObjectNullable(fromObject: PlayerSymbol?): PlayerSymbolDTO? {
        return try {
            fromObject?.let {
                PlayerSymbolDTO.valueOf(fromObject.name)
            }
        } catch (e: Exception) {
            null
        }
    }

}