package com.br.tictactoe.domain.mappers

import Mapper
import com.br.tictactoe.data.network.enums.ServerMessageTypeDTO
import com.br.tictactoe.domain.enums.ServerMessageType

class ServerMessageTypeDTOMapper : Mapper<ServerMessageType, ServerMessageTypeDTO> {

    override fun toObject(fromObject: ServerMessageType): ServerMessageTypeDTO {
       return try {
           ServerMessageTypeDTO.valueOf(fromObject.name)
       } catch (e: Exception) {
              ServerMessageTypeDTO.ERROR
       }
    }

}