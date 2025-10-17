package com.br.tictactoe.domain.di

import com.br.tictactoe.domain.GameController
import com.br.tictactoe.domain.GameService
import com.br.tictactoe.domain.mappers.ClientMessageMapper
import com.br.tictactoe.domain.mappers.ClientMessageTypeMapper
import org.koin.dsl.module

fun injectGameDomainModules() = gameDomainModules

val gameDomainModules = module {
    factory { ClientMessageTypeMapper() }
    factory { ClientMessageMapper(get()) }

    factory { GameService() }
    factory { GameController(get(), get(), get(), get(), get()) }
}