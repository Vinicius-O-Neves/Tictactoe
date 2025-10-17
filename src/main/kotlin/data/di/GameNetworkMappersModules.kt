package com.br.tictactoe.data.di

import com.br.tictactoe.data.network.mappers.*
import org.koin.dsl.module

fun injectGameNetworkMappersModules() = gameNetworkMappersModules

val gameNetworkMappersModules = module {
    factory { GameStatusDTOMapper() }
    factory { PlayerSymbolDTOMapper() }
    factory { PlayerDTOMapper(get()) }
    factory { GameDTOMapper(get(), get(), get()) }
    factory { ServerMessageTypeDTOMapper() }
}