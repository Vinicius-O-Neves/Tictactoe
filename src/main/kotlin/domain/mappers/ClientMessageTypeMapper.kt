package com.br.tictactoe.domain.mappers

import Mapper
import com.br.tictactoe.data.network.enums.ClientMessageTypeDTO
import com.br.tictactoe.domain.enums.ClientMessageType

class ClientMessageTypeMapper : Mapper<ClientMessageTypeDTO, ClientMessageType> {

    override fun toObject(fromObject: ClientMessageTypeDTO): ClientMessageType {
        return ClientMessageType.valueOf(fromObject.name)
    }

}