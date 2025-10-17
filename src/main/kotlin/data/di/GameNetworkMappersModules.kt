package com.br.tictactoe.data.di

import com.br.tictactoe.data.network.mappers.GameDTOMapper
import com.br.tictactoe.data.network.mappers.GameStatusDTOMapper
import com.br.tictactoe.data.network.mappers.PlayerDTOMapper
import com.br.tictactoe.data.network.mappers.PlayerSymbolDTOMapper
import org.koin.dsl.module

fun injectGameNetworkMappersModules() = gameNetworkMappersModules

val gameNetworkMappersModules = module {
    factory { GameStatusDTOMapper() }
    factory { PlayerSymbolDTOMapper() }
    factory { PlayerDTOMapper() }
    factory { GameDTOMapper(get(), get(), get()) }
}