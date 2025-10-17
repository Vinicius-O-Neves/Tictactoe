package com.br.tictactoe.domain.mappers

import Mapper
import com.br.tictactoe.data.network.models.ClientMessageDTO
import com.br.tictactoe.domain.models.ClientMessage

class ClientMessageMapper(
    private val clientMessageTypeMapper: ClientMessageTypeMapper
) : Mapper<ClientMessageDTO, ClientMessage> {

    override fun toObject(fromObject: ClientMessageDTO): ClientMessage {
        val mappedClientMessageType = clientMessageTypeMapper.toObject(fromObject.type)

        return ClientMessage(
            type = mappedClientMessageType,
            position = fromObject.position
        )
    }

}