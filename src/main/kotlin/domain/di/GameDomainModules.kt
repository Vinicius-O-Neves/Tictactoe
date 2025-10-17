package com.br.tictactoe.domain.di

import com.br.tictactoe.domain.GameController
import com.br.tictactoe.domain.GameService
import org.koin.dsl.module

fun injectGameDomainModules() = gameDomainModules

val gameDomainModules = module {
    single { GameService() }
    single { GameController(get(), get(), get(), get()) }
}